package com.hci.nip.base;

import com.hci.nip.base.actuator.Actuator;
import com.hci.nip.base.sensor.Sensor;

import java.util.List;

public interface DeviceManager {

    /**
     * This method should be called to initialize sensors and actuators
     */
    void initialize();

    /**
     * This method should be called to release any resources (sensors and actuators)
     */
    void release();

    String getId();

    String getName();

    List<Sensor> getSensors();

    List<Actuator> getActuators();
}
