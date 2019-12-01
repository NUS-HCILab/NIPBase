package com.hci.nip.base.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import fi.iki.elonen.NanoHTTPD;

public class RestServer {

    private static final Logger LOG = Logger.getLogger(RestServer.class.getName());

    public static final int DEFAULT_REST_SERVER_PORT = 8080;

    // Ref: https://github.com/NanoHttpd/nanohttpd/blob/master/nanolets/src/test/java/org/nanohttpd/junit/router/AppNanolets.java
    // Ref: https://github.com/NanoHttpd/nanohttpd/blob/master/nanolets/src/main/java/org/nanohttpd/router/RouterNanoHTTPD.java
    private final RouterNanoHTTPD nanoHTTPDServer;

    public RestServer(int port) {
        this.nanoHTTPDServer = new RouterNanoHTTPD(port);
        configureNanoHTTPDServer();
    }

    private void configureNanoHTTPDServer() {
        nanoHTTPDServer.addMappings();
        // remove the routes added by default
        nanoHTTPDServer.removeRoute("/");
        nanoHTTPDServer.removeRoute("/index.html");
    }

    public void startServer() throws IOException {
        try {
            nanoHTTPDServer.start();
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Add a route to execute
     *
     * @param handler {@link BaseUrlHandler}
     */
    public void addUrlHandler(BaseUrlHandler handler) {
        for (String url : handler.getStaticUrls()) {
            nanoHTTPDServer.addRoute(url, handler.getHandler());
        }
    }

    public void stopService() {
        nanoHTTPDServer.stop();
    }

    public boolean isAlive() {
        return nanoHTTPDServer.isAlive();
    }

    /**
     * BaseUrlHandler class
     */
    public static abstract class BaseUrlHandler {
        protected static final String EMPTY_JSON_STRING = "{}";

        final RouterNanoHTTPD.UriResponder getHandler() {
            return this.handler;
        }

        /**
         * NOTE: This will be processed at the start (so dynamic changes will not take effect)
         *
         * @return the url to process
         */
        public abstract List<String> getStaticUrls();

        /**
         * @param request {@link Request} GET request
         * @return Response
         */
        public abstract Response get(Request request);

        /**
         * @param request {@link Request} POST request
         * @return Response
         */
        public abstract Response post(Request request);

        /**
         * @param request {@link Request} PUT request
         * @return Response
         */
        public abstract Response put(Request request);


        /**
         * inner class to handle
         */
        private final RouterNanoHTTPD.UriResponder handler = new RouterNanoHTTPD.DefaultStreamHandler() {

            private static final String SYSTEM_ACCESS_CONTROL_ALLOW_HEADER_PROPERTY_NAME = "AccessControlAllowHeader";

            private static final String ACCESS_CONTROL_ALLOW_ORIGIN_PROPERTY_NAME = "Access-Control-Allow-Origin";
            private static final String ACCESS_CONTROL_ALLOW_HEADERS_PROPERTY_NAME = "Access-Control-Allow-Headers";
            private static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_PROPERTY_NAME = "Access-Control-Allow-Credentials";
            private static final String ACCESS_CONTROL_ALLOW_METHODS_PROPERTY_NAME = "Access-Control-Allow-Methods";

            private static final String DEFAULT_CONTENT_TYPE = "application/json";

            private static final String DEFAULT_ALLOWED_CORS = "*";
            private static final String DEFAULT_ALLOWED_HEADERS = "origin,accept,Content-Type";
            private static final String DEFAULT_ALLOWED_CREDENTIALS = "true";
            private static final String DEFAULT_ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS,HEAD";

            @Override
            public String getMimeType() {
                return DEFAULT_CONTENT_TYPE;
            }

            @Override
            public NanoHTTPD.Response.IStatus getStatus() {
                return NanoHTTPD.Response.Status.INTERNAL_ERROR;
            }

            private NanoHTTPD.Response.IStatus getMappedStatus(StatusCode statusCode) {
                return NanoHTTPD.Response.Status.lookup(statusCode.getCode());
            }

            /**
             * ref: https://github.com/NanoHttpd/nanohttpd/blob/master/webserver/src/main/java/org/nanohttpd/webserver/SimpleWebServer.java
             */
            private String getAllowedHeaders() {
                return System.getProperty(SYSTEM_ACCESS_CONTROL_ALLOW_HEADER_PROPERTY_NAME, DEFAULT_ALLOWED_HEADERS);
            }

            private NanoHTTPD.Response getFixedResponseWithCORS(Response response) {
                NanoHTTPD.Response res = NanoHTTPD.newFixedLengthResponse(getMappedStatus(response.getStatusCode()), response.getMimeType(), response.getResponseBody());
                res.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_PROPERTY_NAME, DEFAULT_ALLOWED_CORS);
                res.addHeader(ACCESS_CONTROL_ALLOW_HEADERS_PROPERTY_NAME, getAllowedHeaders());
                res.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS_PROPERTY_NAME, DEFAULT_ALLOWED_CREDENTIALS);
                res.addHeader(ACCESS_CONTROL_ALLOW_METHODS_PROPERTY_NAME, DEFAULT_ALLOWED_METHODS);
                return res;
            }

            private NanoHTTPD.Response getChunkedResponseWithCORS(Response response) {
                NanoHTTPD.Response res = NanoHTTPD.newChunkedResponse(getMappedStatus(response.getStatusCode()), response.getMimeType(), response.getData());
                res.addHeader(ACCESS_CONTROL_ALLOW_ORIGIN_PROPERTY_NAME, DEFAULT_ALLOWED_CORS);
                res.addHeader(ACCESS_CONTROL_ALLOW_HEADERS_PROPERTY_NAME, getAllowedHeaders());
                res.addHeader(ACCESS_CONTROL_ALLOW_CREDENTIALS_PROPERTY_NAME, DEFAULT_ALLOWED_CREDENTIALS);
                res.addHeader(ACCESS_CONTROL_ALLOW_METHODS_PROPERTY_NAME, DEFAULT_ALLOWED_METHODS);
                return res;
            }

            @Override
            public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
                Response response = BaseUrlHandler.this.get(new Request(urlParams, session, EMPTY_JSON_STRING));
                if (response.getData() == null) {
                    return getFixedResponseWithCORS(response);
                } else {
                    return getChunkedResponseWithCORS(response);
                }
            }

            @Override
            public NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
                Response response = BaseUrlHandler.this.post(new Request(urlParams, session, getJsonRequestBody(session)));
                if (response.getData() == null) {
                    return getFixedResponseWithCORS(response);
                } else {
                    return getChunkedResponseWithCORS(response);
                }
            }

            @Override
            public NanoHTTPD.Response put(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
                Response response = BaseUrlHandler.this.put(new Request(urlParams, session, getJsonRequestBody(session)));
                return getFixedResponseWithCORS(response);
            }

            private String getJsonRequestBody(NanoHTTPD.IHTTPSession session) {
                // Ref: https://stackoverflow.com/questions/22349772/retrieve-http-body-in-nanohttpd
                final HashMap<String, String> map = new HashMap<>();
                try {
                    session.parseBody(map);
                } catch (IOException | NanoHTTPD.ResponseException e) {
                    LOG.log(Level.SEVERE, e.getMessage(), e);
                    throw new IllegalStateException(e);
                }

                String req = map.get("postData");
                return req == null ? EMPTY_JSON_STRING : req;
            }

            @Override
            public InputStream getData() {
                throw new IllegalStateException("This should not called in JSON based nanolet");
            }
        };

    }

    public static class Response {
        private final StatusCode statusCode;
        private final String responseBody;
        private final InputStream data;
        private final String mimeType;

        public Response(StatusCode statusCode, String responseBody) {
            this.statusCode = statusCode;
            this.responseBody = responseBody;
            this.mimeType = "application/json";
            this.data = null;
        }

        /**
         * @param statusCode
         * @param mimeType   e.g. "application/json", "text/html"
         * @param data
         */
        public Response(StatusCode statusCode, String mimeType, InputStream data) {
            this.statusCode = statusCode;
            this.responseBody = null;
            this.mimeType = mimeType;
            this.data = data;
        }

        StatusCode getStatusCode() {
            return statusCode;
        }

        String getResponseBody() {
            return responseBody;
        }

        String getMimeType() {
            return mimeType;
        }

        InputStream getData() {
            return data;
        }

        public static Response getSuccessResponse(String responseBody) {
            return new Response(StatusCode.OK, responseBody);
        }

        public static Response getBadResponse(String responseBody) {
            return new Response(StatusCode.BAD_REQUEST, responseBody);
        }

        public static Response getSuccessResponse(String mimeType, InputStream data) {
            return new Response(StatusCode.OK, mimeType, data);
        }

        @Override
        public String toString() {
            return "Response{" +
                    "statusCode=" + statusCode +
                    ", responseBody='" + responseBody + '\'' +
                    ", mimeType='" + mimeType + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    public static class Request {
        private final Map<String, String> urlParams;
        private final NanoHTTPD.IHTTPSession session;
        private final String requestBody;

        Request(Map<String, String> urlParams, NanoHTTPD.IHTTPSession session, String requestBody) {
            this.urlParams = urlParams;
            this.session = session;
            this.requestBody = requestBody;
        }

        public String getUrl() {
            return session.getUri();
        }

        public String getRequestBody() {
            return requestBody;
        }

        public Map<String, String> getUrlParams() {
            return urlParams;
        }

        public String getIpAddress() {
            return session.getRemoteIpAddress();
        }

        public String getMethod() {
            return session.getMethod().name();
        }

        @Override
        public String toString() {
            return "Request{" +
                    "urlParams=" + urlParams +
                    ", url=`" + session.getUri() + '\'' +
                    ", ip=" + session.getRemoteIpAddress() + '\'' +
                    ", method=" + session.getMethod() + '\'' +
                    ", requestBody='" + requestBody + '\'' +
                    '}';
        }
    }

    /**
     * DO NOT modify.
     * If you want to modify please check the codes are available in {@link NanoHTTPD.Response.Status}
     */
    public enum StatusCode {
        OK(200),
        CREATED(201),
        ACCEPTED(202),
        NO_CONTENT(204),

        BAD_REQUEST(400),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        NOT_FOUND(404),
        METHOD_NOT_ALLOWED(405),
        NOT_ACCEPTABLE(406),
        REQUEST_TIMEOUT(408),

        UNSUPPORTED_MEDIA_TYPE(415),

        INTERNAL_ERROR(500),
        NOT_IMPLEMENTED(501),
        SERVICE_UNAVAILABLE(503);
        private final int code;

        StatusCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

}
