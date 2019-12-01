package com.hci.nip.base.model;

import java.util.List;

public class PlatformInfo implements BaseData {

    private String id;
    private String name;
    private List<SensorInfo> sensors;
    private List<ActuatorInfo> actuators;

    public PlatformInfo(String id) {
        this.id = id;
    }

    public PlatformInfo(String id, String name, List<SensorInfo> sensors, List<ActuatorInfo> actuators) {
        this.id = id;
        this.name = name;
        this.sensors = sensors;
        this.actuators = actuators;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<SensorInfo> getSensors() {
        return sensors;
    }

    public List<ActuatorInfo> getActuators() {
        return actuators;
    }

    @Override
    public String toString() {
        return "PlatformInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sensors=" + sensors +
                ", actuators=" + actuators +
                '}';
    }
}
