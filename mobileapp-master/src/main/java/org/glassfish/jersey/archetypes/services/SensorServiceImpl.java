package org.glassfish.jersey.archetypes.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.DatabaseConnectionProvider;
import org.glassfish.jersey.archetypes.model.Sensor;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 * Created by akhilakishore on 29/04/16.
 */
public class SensorServiceImpl implements SensorService {

    private final DatabaseConnectionProvider db;

    public SensorServiceImpl( DatabaseConnectionProvider db){this.db=db;}

    public Sensor getSensorBySensorId(int sensorId, String sessionToken)throws Exception{
        if(!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)){
            return null;
        }
        ResultSet resultSet = db.select("select * from SENSOR where sensorId="+sensorId+";");
        Sensor sensor = new Sensor();
        if (resultSet.next()){
            sensor.setSensorId(resultSet.getInt(1));
            sensor.setSensorName(resultSet.getString(2));
            sensor.setSensorInstallationDate(resultSet.getDate(3));
            sensor.setSensorMaintenanceDate(resultSet.getDate(4));
            sensor.setTreeId(resultSet.getInt(5));
            sensor.setAdminId(resultSet.getString(6));
        }
        return sensor;
    }

    public JSONArray getAllSensorInformation(String sessionToken) throws Exception {
        if(!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)){
            return null;
        }
        ResultSet resultSet = db.select("select * from SENSOR");
        JSONArray array = new JSONArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        while(resultSet.next()){
            Sensor sensor = new Sensor();
            sensor.setSensorId(resultSet.getInt(1));
            sensor.setSensorName(resultSet.getString(2));
            sensor.setSensorInstallationDate(resultSet.getDate(3));
            sensor.setSensorMaintenanceDate(resultSet.getDate(4));
            sensor.setTreeId(resultSet.getInt(5));
            sensor.setAdminId(resultSet.getString(6));
            array.add(gson.toJson(sensor));
        }
            return array;
    }

    public int deleteSensor(int sensorId, String sessionToken) throws Exception {
        if(!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)){
            return -1;
        }
        return db.insertUpdateDelete("Delete from SENSOR where sensorId="+sensorId);
    }

    public int updateSensor(int sensorId, String maintenanceDate,String sessionToken)throws Exception{
        if(!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)){
            return -1;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf1.parse(maintenanceDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("Update SENSOR Set sensorMaintenanceDate=? where sensorID=?");
        preparedStatement.setDate(1, sqlDate);
        preparedStatement.setInt(2, sensorId);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;

    }
    public int addSensor(Sensor sensor, String sessionToken) throws Exception {
        if (!ServiceFactory.getServiceFactory().getAdminService().isLogin(sessionToken)) {
            return -1;
        }
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("Insert into SENSOR values(?,?,?,?,?,?)");
        preparedStatement.setInt(1, sensor.getSensorId());
        preparedStatement.setString(2, sensor.getSensorName());
        preparedStatement.setDate(3, new Date(sensor.getSensorInstallationDate().getTime()));
        preparedStatement.setDate(4, new Date(sensor.getSensorMaintenanceDate().getTime()));
        preparedStatement.setInt(5, sensor.getTreeId());
        preparedStatement.setString(6,sensor.getAdminId());
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public static void main(String args[]) throws Exception {
        new SensorServiceImpl(DatabaseConnectionProvider.getDatabaseProvider()).getAllSensorInformation("akhila@gmail.com");
    }
}
