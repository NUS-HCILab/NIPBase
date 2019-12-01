package com.hci.nip.base.util;

import com.hci.nip.base.actuator.Actuator;
import com.hci.nip.base.actuator.ActuatorType;
import com.hci.nip.base.sensor.Sensor;
import com.hci.nip.base.sensor.SensorType;

import java.util.ArrayList;
import java.util.List;

public final class DeviceManagerUtil {

    public static List<Sensor> getFilteredSensorsByType(List<Sensor> sensorList, List<SensorType> sensorTypeList) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            if (sensorTypeList.contains(sensor.getType())) {
                list.add(sensor);
            }
        }
        return list;
    }

    public static List<Sensor> getFilteredSensorsByType(List<Sensor> sensorList, SensorType sensorType) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            if (sensor.getType() == sensorType) {
                list.add(sensor);
            }
        }
        return list;
    }

    public static List<Sensor> getFilteredSensorsById(List<Sensor> sensorList, List<String> idList) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            if (idList.contains(sensor.getId())) {
                list.add(sensor);
            }
        }
        return list;
    }

    public static List<Sensor> getFilteredSensorsById(List<Sensor> sensorList, String id) {
        List<Sensor> list = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            if (sensor.getId().equals(id)) {
                list.add(sensor);
            }
        }
        return list;
    }

    public static List<Actuator> getFilteredActuatorsByType(List<Actuator> actuatorList, List<ActuatorType> actuatorTypeList) {
        List<Actuator> list = new ArrayList<>();
        for (Actuator actuator : actuatorList) {
            if (actuatorTypeList.contains(actuator.getType())) {
                list.add(actuator);
            }
        }
        return list;
    }

    public static List<Actuator> getFilteredActuatorsByType(List<Actuator> actuatorList, ActuatorType actuatorType) {
        List<Actuator> list = new ArrayList<>();
        for (Actuator actuator : actuatorList) {
            if (actuator.getType() == actuatorType) {
                list.add(actuator);
            }
        }
        return list;
    }

    public static List<Actuator> getFilteredActuatorsById(List<Actuator> actuatorList, List<String> idList) {
        List<Actuator> list = new ArrayList<>();
        for (Actuator actuator : actuatorList) {
            if (idList.contains(actuator.getId())) {
                list.add(actuator);
            }
        }
        return list;
    }

    public static List<Actuator> getFilteredActuatorsById(List<Actuator> actuatorList, String id) {
        List<Actuator> list = new ArrayList<>();
        for (Actuator actuator : actuatorList) {
            if (actuator.getId().equals(id)) {
                list.add(actuator);
            }
        }
        return list;
    }


}
