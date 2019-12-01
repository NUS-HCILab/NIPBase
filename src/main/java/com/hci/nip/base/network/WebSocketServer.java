package com.hci.nip.base.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoWSD;

public class WebSocketServer {

    private static final Logger LOG = Logger.getLogger(WebSocketServer.class.getName());

    public static final int DEFAULT_WEB_SOCKET_SERVER_PORT = 9090;

    private final SocketServer socketServer;

    public WebSocketServer(int port) {
        this.socketServer = new SocketServer(port);
    }

    public void start() throws IOException {
        socketServer.start(-1);
    }

    public void stop() {
        socketServer.stop();
    }

    public boolean isActive() {
        return socketServer.isAlive();
    }

    public void addHandler(BaseMessageHandler handler) {
        socketServer.addHandler(handler);
    }

    private static class SocketServer extends NanoWSD {
        private final List<BaseMessageHandler> messageHandlers;

        SocketServer(int port) {
            super(port);
            this.messageHandlers = new ArrayList<>();
        }

        @Override
        protected WebSocket openWebSocket(IHTTPSession handshake) {
            return new WebSocketServer.WebSocket(this.messageHandlers, handshake);
        }

        void addHandler(BaseMessageHandler handler) {
            messageHandlers.add(handler);
        }

    }

    private static class WebSocket extends NanoWSD.WebSocket {

        private final List<BaseMessageHandler> messageHandlers;
        private final String url;

        WebSocket(List<BaseMessageHandler> handlers, NanoHTTPD.IHTTPSession handshakeRequest) {
            super(handshakeRequest);
            this.url = handshakeRequest.getUri();
            this.messageHandlers = handlers;
            LOG.log(Level.INFO, "[WEB SOCKET] create: " + url + ", " + Thread.currentThread().getId());
        }

        @Override
        protected void onOpen() {
            LOG.log(Level.FINE, "onOpen: " + url);
        }

        @Override
        protected void onClose(NanoWSD.WebSocketFrame.CloseCode code, String reason, boolean initiatedByRemote) {
            LOG.log(Level.FINE, "C [" + (initiatedByRemote ? "Remote" : "Self") + "] " + (code != null ? code : "UnknownCloseCode[null]")
                    + (reason != null && !reason.isEmpty() ? ": " + reason : ""));
        }

        @Override
        protected void onMessage(NanoWSD.WebSocketFrame message) {
            try {
                message.setUnmasked();
                LOG.log(Level.FINE, "[MSG]: " + url + ", " + message.getTextPayload());
                NanoWSD.WebSocketFrame response = informCorrespondingHandler(url, message);
                sendFrame(response);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private NanoWSD.WebSocketFrame informCorrespondingHandler(String url, NanoWSD.WebSocketFrame message) {
            String textPayload = message.getTextPayload();
            BaseMessageHandler handler = getCorrespondingHandler(url);

            Message response;
            try {
                if (handler != null) {
                    response = handler.onMessage(new Message(url, textPayload));
                } else {
                    response = new Message(url, "{\"code\": \"4000\", \"error\": \"Not supported\"}");
                }
            } catch (Exception e) {
                response = new Message(url, "{\"code\": \"4000\", \"error\": \"Failed: " + e.getMessage() + "\"}");
                LOG.log(Level.SEVERE, "informCorrespondingHandler", e);
            }

            return new NanoWSD.WebSocketFrame(message.getOpCode(), message.isFin(), getData(response));
        }

        private byte[] getData(Message response) {
            String data = response.getData();
            if (data != null) {
                return data.getBytes();
            }
            return new byte[]{};
        }

        private BaseMessageHandler getCorrespondingHandler(String url) {
            BaseMessageHandler correspondingHandler = null;
            for (BaseMessageHandler handler : messageHandlers) {
                if (url.startsWith(handler.getBaseUrl())) {
                    correspondingHandler = handler;
                    break;
                }
            }

            return correspondingHandler;
        }

        @Override
        protected void onPong(NanoWSD.WebSocketFrame pong) {
            LOG.log(Level.INFO, "P " + pong);
        }

        @Override
        protected void onException(IOException exception) {
            LOG.log(Level.SEVERE, "exception occurred", exception);
        }

//        @Override
//        protected void debugFrameReceived(NanoWSD.WebSocketFrame frame) {
//            LOG.log(Level.FINE, "R " + frame);
//        }
//
//        @Override
//        protected void debugFrameSent(NanoWSD.WebSocketFrame frame) {
//            LOG.log(Level.FINE, "S " + frame);
//        }

    }

    public interface BaseMessageHandler {

        String getBaseUrl();

        Message onMessage(Message message);
    }

    public static class Message {
        private final String url;
        private final String data;

        public Message(String url, String data) {
            this.url = url;
            this.data = data;
        }

        public String getUrl() {
            return url;
        }

        public String getData() {
            return data;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "url='" + url + '\'' +
                    ", data='" + data + '\'' +
                    '}';
        }
    }

}
