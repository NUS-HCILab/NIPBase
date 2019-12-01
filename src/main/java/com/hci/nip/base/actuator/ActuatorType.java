package com.hci.nip.base.actuator;

public enum ActuatorType {
    ACTUATOR_TYPE_SPEAKER("actuator.type.speaker"),
    ACTUATOR_TYPE_VIBRATOR("actuator.type.vibrator"),
    ACTUATOR_TYPE_DISPLAY("actuator.type.display"),
    ACTUATOR_TYPE_NOTIFIER("actuator.type.notifier"),
    ACTUATOR_TYPE_TOUCH_BAR("actuator.type.touchbar"),
    ;

    private final String type;

    ActuatorType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
