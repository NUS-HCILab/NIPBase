package com.hci.nip.base.sensor.model;

public class CameraPreviewInfo {

    private boolean display = true;
    private String status;

    public CameraPreviewInfo() {
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CameraPreviewInfo{" +
                "display=" + display +
                ", status='" + status + '\'' +
                '}';
    }
}
