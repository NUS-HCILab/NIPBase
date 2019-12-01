package com.hci.nip.base.util;

import com.hci.nip.base.sensor.DummySensor;
import com.hci.nip.base.sensor.Sensor;
import com.hci.nip.base.sensor.SensorLocation;
import com.hci.nip.base.sensor.SensorType;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DeviceManagerUtilTest {

    @DataProvider
    public Object[][] getDataTestGetFilteredSensorsByType() {
        Sensor accelerometer1 = new DummySensor("1", SensorType.SENSOR_TYPE_ACCELEROMETER, SensorLocation.SENSOR_LOCATION_HEAD);
        Sensor accelerometer2 = new DummySensor("2", SensorType.SENSOR_TYPE_ACCELEROMETER, SensorLocation.SENSOR_LOCATION_MOBILE);

        return new Object[][]{
                {
                        new ArrayList<>(),
                        Collections.singletonList(SensorType.SENSOR_TYPE_ACCELEROMETER),
                        new ArrayList<>()
                },
                {
                        Arrays.asList(accelerometer1, accelerometer2),
                        Collections.singletonList(SensorType.SENSOR_TYPE_ACCELEROMETER),
                        Arrays.asList(accelerometer1, accelerometer2)
                },
        };
    }

    @Test(dataProvider = "getDataTestGetFilteredSensorsByType")
    public void getFilteredSensorsByType(List<Sensor> sensorList, List<SensorType> sensorTypeList, List<Sensor> expectList) {
        assertEquals(DeviceManagerUtil.getFilteredSensorsByType(sensorList, sensorTypeList), expectList);
    }

    @Test
    public void getFilteredSensorsById() {
        throw new RuntimeException("NEED TO IMPLEMENT");
    }

    @Test
    public void getFilteredActuatorsByType() {
        throw new RuntimeException("NEED TO IMPLEMENT");
    }

    @Test
    public void getFilteredActuatorsById() {
        throw new RuntimeException("NEED TO IMPLEMENT");
    }
}