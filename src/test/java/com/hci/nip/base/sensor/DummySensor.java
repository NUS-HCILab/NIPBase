package com.hci.nip.base.sensor;


import java.util.Collections;
import java.util.List;

public class DummySensor implements Sensor {

    private final String id;
    private final SensorType sensorType;
    private final SensorLocation location;
    private boolean active;

    public DummySensor(String id, SensorType sensorType, SensorLocation location) {
        this.id = id;
        this.sensorType = sensorType;
        this.location = location;
        this.active = false;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public SensorType getType() {
        return sensorType;
    }

    @Override
    public String getModel() {
        return null;
    }

    @Override
    public SensorLocation getLocation() {
        return location;
    }

    @Override
    public long getSampleRate() {
        return 0;
    }

    @Override
    public long getBufferSize() {
        return 0;
    }

    @Override
    public String getResolution() {
        return "";
    }

    @Override
    public String getDataFormat() {
        return "";
    }

    @Override
    public void open() {
        // DO NOTHING
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void activate() {
        active = true;
    }

    @Override
    public void deactivate() {
        active = false;
    }

    @Override
    public void close() {
        deactivate();
    }

    @Override
    public List<?> readData() {
        return Collections.EMPTY_LIST;
    }
}
