package com.hci.nip.base.actuator;

import com.hci.nip.base.actuator.model.AudioPlayInfo;
import com.hci.nip.base.error.BaseException;
import com.hci.nip.base.error.ErrorCode;

public interface Speaker extends Actuator {

    void startPlaying(AudioPlayInfo audio) throws SpeakerException;

    void pausePlaying(AudioPlayInfo audio) throws SpeakerException;

    void stopPlaying(AudioPlayInfo audio) throws SpeakerException;

    /**
     * @return the current information if the speaker is active, else return the previous information
     */
    AudioPlayInfo getPlayingInfo();

    class SpeakerException extends BaseException {

        public SpeakerException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public SpeakerException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }
}
