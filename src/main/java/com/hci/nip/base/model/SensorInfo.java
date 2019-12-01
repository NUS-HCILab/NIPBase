package com.hci.nip.base.model;

import com.hci.nip.base.sensor.Sensor;
import com.hci.nip.base.sensor.SensorLocation;
import com.hci.nip.base.sensor.SensorType;

public class SensorInfo implements BaseData {
    private String id;
    private SensorType type;
    private String model;
    private SensorLocation location;
    private long sampleRate;
    private long bufferSize;
    private String resolution;
    private String dataFormat;
    private boolean active;

    public SensorInfo() {
    }

    public SensorInfo(Sensor sensor) {
        setProperties(sensor);
    }

    private void setProperties(Sensor sensor) {
        this.id = sensor.getId();
        this.type = sensor.getType();
        this.model = sensor.getModel();
        this.location = sensor.getLocation();
        this.sampleRate = sensor.getSampleRate();
        this.bufferSize = sensor.getBufferSize();
        this.resolution = sensor.getResolution();
        this.dataFormat = sensor.getDataFormat();
        this.active = sensor.isActive();
    }

    public SensorInfo(SensorInfo sensorInfo) {
        cloneProperties(sensorInfo);
    }

    private void cloneProperties(SensorInfo sensorInfo) {
        this.id = sensorInfo.id;
        this.type = sensorInfo.type;
        this.model = sensorInfo.model;
        this.location = sensorInfo.location;
        this.sampleRate = sensorInfo.sampleRate;
        this.bufferSize = sensorInfo.bufferSize;
        this.resolution = sensorInfo.resolution;
        this.dataFormat = sensorInfo.dataFormat;
        this.active = sensorInfo.active;
    }

    public String getId() {
        return id;
    }

    public SensorType getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public SensorLocation getLocation() {
        return location;
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

    public String getDataFormat() {
        return dataFormat;
    }

    public boolean isActive() {
        return active;
    }

    public SensorInfo setId(String id) {
        this.id = id;
        return this;
    }

    public SensorInfo setType(SensorType type) {
        this.type = type;
        return this;
    }

    public SensorInfo setModel(String model) {
        this.model = model;
        return this;
    }

    public SensorInfo setLocation(SensorLocation location) {
        this.location = location;
        return this;
    }

    public SensorInfo setSampleRate(long sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public SensorInfo setBufferSize(long bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public SensorInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public SensorInfo setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
        return this;
    }

    public SensorInfo setActive(boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "SensorInfo{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", location=" + location +
                ", sampleRate=" + sampleRate +
                ", bufferSize=" + bufferSize +
                ", resolution='" + resolution + '\'' +
                ", dataFormat='" + dataFormat + '\'' +
                ", active=" + active +
                '}';
    }

}
