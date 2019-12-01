package com.hci.nip.base.actuator;

public enum ActuatorLocation {

    ACTUATOR_LOCATION_HEAD("actuator.location.head"),
    ACTUATOR_LOCATION_MOBILE("actuator.location.mobile"),
    ;

    private final String location;

    ActuatorLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
