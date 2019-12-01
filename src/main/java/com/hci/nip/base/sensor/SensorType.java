package com.hci.nip.base.sensor;

public enum SensorType {

    SENSOR_TYPE_ACCELEROMETER("senor.type.accelerometer"),
    SENSOR_TYPE_MICROPHONE("senor.type.microphone"),
    SENSOR_TYPE_TOUCH_BAR("senor.type.touchbar"),
    SENSOR_TYPE_GYROSCOPE("senor.type.gyroscope"),
    SENSOR_TYPE_MAGNETOMETER("senor.type.magnetometer"),
    SENSOR_TYPE_LIGHT("senor.type.light"),
    SENSOR_TYPE_PRESSURE("senor.type.pressure"),
    SENSOR_TYPE_CAMERA("senor.type.camera"),
    ;

    private final String type;

    SensorType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
