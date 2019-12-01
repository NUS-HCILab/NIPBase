package com.hci.nip.base.sensor.model;

import com.hci.nip.base.model.BaseData;
import com.hci.nip.base.model.DeviceStatus;

/**
 * DO NOT MODIFY DEFAULT values
 */
public class AudioRecordInfo implements BaseData {
    private String id;
    private String dest;
    private boolean stream = false;
    private long sampleRate;
    private long bufferSize;
    private String resolution;
    private DeviceStatus status = DeviceStatus.IDLE;

    public AudioRecordInfo(String id) {
        this.id = id;
    }

    public AudioRecordInfo(AudioRecordInfo audioInfo) {
        this.id = audioInfo.getId();
        this.dest = audioInfo.getDest();
        this.stream = audioInfo.isStream();
        this.sampleRate = audioInfo.getSampleRate();
        this.bufferSize = audioInfo.getBufferSize();
        this.resolution = audioInfo.getResolution();
        this.status = audioInfo.getStatus();
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

    public long getSampleRate() {
        return sampleRate;
    }

    public long getBufferSize() {
        return bufferSize;
    }

    public String getResolution() {
        return resolution;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public AudioRecordInfo setId(String id) {
        this.id = id;
        return this;
    }

    public AudioRecordInfo setDest(String dest) {
        this.dest = dest;
        return this;
    }

    public AudioRecordInfo setStream(boolean stream) {
        this.stream = stream;
        return this;
    }

    public AudioRecordInfo setSampleRate(long sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public AudioRecordInfo setBufferSize(long bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public AudioRecordInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public AudioRecordInfo setStatus(DeviceStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "AudioRecordInfo{" +
                "id='" + id + '\'' +
                ", dest='" + dest + '\'' +
                ", stream=" + stream +
                ", sampleRate=" + sampleRate +
                ", bufferSize=" + bufferSize +
                ", resolution='" + resolution + '\'' +
                ", status=" + status +
                '}';
    }
}
