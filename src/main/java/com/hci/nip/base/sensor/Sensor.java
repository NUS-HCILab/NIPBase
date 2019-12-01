package com.hci.nip.base.sensor;

import java.util.List;

public interface Sensor {
    String getId();

    SensorType getType();

    String getModel();

    SensorLocation getLocation();

    /**
     * @return the sample rate in samples per second
     */
    long getSampleRate();

    long getBufferSize();

    String getResolution();

    String getDataFormat();

    /**
     * open the sensor and initialize it (no communication)
     * <p>
     * NOTE: To start the actuator use {@link #activate()}
     */
    void open();

    boolean isActive();

    void activate();

    void deactivate();

    /**
     * close the sensor and release any resources
     */
    void close();


    /**
     * Return the read data
     *
     * @return the read data
     */
    List<?> readData();
}
