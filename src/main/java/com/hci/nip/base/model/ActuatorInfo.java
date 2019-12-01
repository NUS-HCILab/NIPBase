package com.hci.nip.base.model;

import com.hci.nip.base.actuator.Actuator;
import com.hci.nip.base.actuator.ActuatorLocation;
import com.hci.nip.base.actuator.ActuatorType;

public class ActuatorInfo implements BaseData {

    private String id;
    private ActuatorType type;
    private String model;
    private ActuatorLocation location;
    private String resolution;
    private String dataFormat;
    private boolean active;

    public ActuatorInfo() {
    }

    public ActuatorInfo(Actuator actuator) {
        setProperties(actuator);
    }

    private void setProperties(Actuator actuator) {
        this.id = actuator.getId();
        this.type = actuator.getType();
        this.model = actuator.getModel();
        this.location = actuator.getLocation();
        this.resolution = actuator.getResolution();
        this.dataFormat = actuator.getDataFormat();
        this.active = actuator.isActive();
    }

    public ActuatorInfo(ActuatorInfo actuatorInfo) {
        cloneProperties(actuatorInfo);
    }

    private void cloneProperties(ActuatorInfo actuatorInfo) {
        this.id = actuatorInfo.id;
        this.type = actuatorInfo.type;
        this.model = actuatorInfo.model;
        this.location = actuatorInfo.location;
        this.resolution = actuatorInfo.resolution;
        this.dataFormat = actuatorInfo.dataFormat;
        this.active = actuatorInfo.active;
    }

    public String getId() {
        return id;
    }

    public ActuatorType getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public ActuatorLocation getLocation() {
        return location;
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

    public ActuatorInfo setId(String id) {
        this.id = id;
        return this;
    }

    public ActuatorInfo setType(ActuatorType type) {
        this.type = type;
        return this;
    }

    public ActuatorInfo setModel(String model) {
        this.model = model;
        return this;
    }

    public ActuatorInfo setLocation(ActuatorLocation location) {
        this.location = location;
        return this;
    }

    public ActuatorInfo setResolution(String resolution) {
        this.resolution = resolution;
        return this;
    }

    public ActuatorInfo setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
        return this;
    }

    public ActuatorInfo setActive(boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String toString() {
        return "ActuatorInfo{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", model='" + model + '\'' +
                ", location=" + location +
                ", resolution='" + resolution + '\'' +
                ", dataFormat='" + dataFormat + '\'' +
                ", active=" + active +
                '}';
    }
}
