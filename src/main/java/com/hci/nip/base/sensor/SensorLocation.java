package com.hci.nip.base.sensor;

public enum SensorLocation {

    SENSOR_LOCATION_HEAD("sensor.location.head"),
    SENSOR_LOCATION_MOBILE("sensor.location.mobile"),
    ;

    private final String location;

    SensorLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
