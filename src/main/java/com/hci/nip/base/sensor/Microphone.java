package com.hci.nip.base.sensor;

import com.hci.nip.base.sensor.model.AudioRecordInfo;
import com.hci.nip.base.error.BaseException;
import com.hci.nip.base.error.ErrorCode;

public interface Microphone extends Sensor {

    void startRecording(AudioRecordInfo audio) throws MicrophoneException;

    void pauseRecording(AudioRecordInfo audio) throws MicrophoneException;

    /**
     * @return the current information if the recorder is active, else return the previous information
     */
    AudioRecordInfo getRecordingInfo();

    void stopRecording(AudioRecordInfo audio) throws MicrophoneException;

    void startLiveStreaming(AudioRecordInfo audio) throws MicrophoneException;

    void stopLiveStreaming(AudioRecordInfo audio) throws MicrophoneException;

    AudioRecordInfo getLiveStreamingInfo();

    class MicrophoneException extends BaseException {

        public MicrophoneException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public MicrophoneException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }

}
