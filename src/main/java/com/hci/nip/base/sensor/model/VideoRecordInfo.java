package com.hci.nip.base.sensor.model;

import com.hci.nip.base.model.BaseData;

/**
 * DO NOT MODIFY DEFAULT values
 */
public class VideoRecordInfo implements BaseData {

    private String id;
    private String dest;
    private boolean stream = false;
    private String resolution;
    private String status;

    public VideoRecordInfo(String id) {
        this.id = id;
    }

    public VideoRecordInfo(VideoRecordInfo videoRecordInfo) {
        this.id = videoRecordInfo.id;
        this.dest = videoRecordInfo.dest;
        this.stream = videoRecordInfo.stream;
        this.resolution = videoRecordInfo.resolution;
        this.status = videoRecordInfo.status;
    }

    public String getId() {
        return id;
    }

    public String getDest() {
        return dest;
    }

    public boolean isStream() {
        return stream;
    }

    public String getResolution() {
        return resolution;
    }

    public String getStatus() {
        return status;
    }

    public VideoRecordInfo setId(String id) {
        this.id = id;
        return this;
    }

    public VideoRecordInfo setDest(String dest) {
        this.dest = dest;
        return this;
    }

    public VideoRecordInfo setStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    public VideoRecordInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public VideoRecordInfo setStatus(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "VideoRecordInfo{" +
                "id='" + id + '\'' +
                ", dest='" + dest + '\'' +
                ", stream=" + stream +
                ", resolution='" + resolution + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
