package com.hci.nip.base.actuator;


import com.hci.nip.base.actuator.model.DisplayData;
import com.hci.nip.base.error.BaseException;
import com.hci.nip.base.error.ErrorCode;

public interface Display extends Actuator {

    void changeDisplay(DisplayData displayData) throws DisplayException;

    void changeDisplayAsync(DisplayData displayData) throws DisplayException;

    class DisplayException extends BaseException {

        public DisplayException(ErrorCode errorCode, String message) {
            super(errorCode, message);
        }

        public DisplayException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }
}
