package com.hci.nip.base.actuator.model;


import com.hci.nip.base.model.BaseData;
import com.hci.nip.base.model.DeviceStatus;

public class AudioPlayInfo implements BaseData {
    private String id;
    private String src;
    private float volume = 1;
    private DeviceStatus status = DeviceStatus.IDLE;

    public AudioPlayInfo(String id) {
        this.id = id;
    }

    public AudioPlayInfo(AudioPlayInfo audioPlayInfo) {
        this.id = audioPlayInfo.getId();
        this.src = audioPlayInfo.getSrc();
        this.status = audioPlayInfo.getStatus();
        this.volume = audioPlayInfo.getVolume();
    }

    public String getId() {
        return id;
    }

    public String getSrc() {
        return src;
    }

    public float getVolume() {
        return volume;
    }

    public DeviceStatus getStatus() {
        return status;
    }


    public AudioPlayInfo setId(String id) {
        this.id = id;
        return this;
    }

    public AudioPlayInfo setSrc(String src) {
        this.src = src;
        return this;
    }

    public AudioPlayInfo setVolume(float volume) {
        this.volume = volume;
        return this;
    }

    public AudioPlayInfo setStatus(DeviceStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return "AudioPlayInfo{" +
                "id='" + id + '\'' +
                ", src='" + src + '\'' +
                ", volume=" + volume +
                ", status=" + status +
                '}';
    }
}
