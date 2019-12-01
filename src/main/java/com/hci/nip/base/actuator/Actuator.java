package com.hci.nip.base.actuator;

import java.util.List;

public interface Actuator {
    String getId();

    ActuatorType getType();

    String getModel();

    ActuatorLocation getLocation();

    String getResolution();

    String getDataFormat();

    /**
     * open the actuator and initialize it (no communication)
     * <p>
     * NOTE: To start the actuator use {@link #activate()}
     */
    void open();

    boolean isActive();

    void activate();

    void deactivate();

    /**
     * close the actuator and release any resources
     */
    void close();

    /**
     * @param data data to be actuated (e.g. for a display, data should be display data)
     * @return true if successful
     * @throws com.nus.hci.bladeheadpiece.base.error.BaseException
     */
    boolean processData(List<?> data);
}
