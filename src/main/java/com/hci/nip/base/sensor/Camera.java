package com.hci.nip.base.sensor;

import com.hci.nip.base.error.BaseException;
import com.hci.nip.base.error.ErrorCode;
import com.hci.nip.base.sensor.model.CameraPreviewInfo;
import com.hci.nip.base.sensor.model.PhotoInfo;
import com.hci.nip.base.sensor.model.VideoRecordInfo;

public interface Camera extends Sensor {

    void enablePreview(CameraPreviewInfo cameraPreviewInfo);

    void disablePreview(CameraPreviewInfo cameraPreviewInfo);

    PhotoInfo takePicture(PhotoInfo photoInfo) throws CameraException;

    VideoRecordInfo startRecording(VideoRecordInfo videoRecordInfo) throws CameraException;

    VideoRecordInfo stopRecording(VideoRecordInfo videoRecordInfo) throws CameraException;

    /**
     * @return the current information if the recorder is active, else return the previous information
     */
    VideoRecordInfo getRecordingInfo();

    class CameraException extends BaseException {

        public CameraException(ErrorCode errorCode) {
            super(errorCode);
        }

        public CameraException(ErrorCode errorCode, Throwable cause) {
            super(errorCode, cause);
        }

        public CameraException(ErrorCode errorCode, String message, Throwable cause) {
            super(errorCode, message, cause);
        }
    }
}
