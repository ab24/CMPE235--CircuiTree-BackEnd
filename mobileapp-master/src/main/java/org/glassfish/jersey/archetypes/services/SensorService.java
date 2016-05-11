package org.glassfish.jersey.archetypes.services;

import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.model.Sensor;

/**
 * Created by akhilakishore on 29/04/16.
 */
public interface SensorService {
    public Sensor getSensorBySensorId(int sensorId, String sessionToken) throws Exception;
    public JSONArray getAllSensorInformation(String sessionToken) throws Exception;
    public int deleteSensor(int sensorId,  String sessionToken) throws Exception;
    public int updateSensor(int sensorId, String maintenanceDate, String sessionToken) throws Exception;
    public int addSensor(Sensor sensor, String sessionToken) throws Exception;
}
