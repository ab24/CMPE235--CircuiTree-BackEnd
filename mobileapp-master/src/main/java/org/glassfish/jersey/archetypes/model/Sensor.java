package org.glassfish.jersey.archetypes.model;

import java.util.Date;

/**
 * Created by akhilakishore on 30/04/16.
 */
public class Sensor {
    private int sensorId;
    private String sensorName;
    private Date sensorInstallationDate;
    private Date sensorMaintenanceDate;
    private int treeId;
    private String adminId;

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public Date getSensorMaintenanceDate() {
        return sensorMaintenanceDate;
    }

    public void setSensorMaintenanceDate(Date sensorMaintenanceDate) {
        this.sensorMaintenanceDate = sensorMaintenanceDate;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Date getSensorInstallationDate() {
        return sensorInstallationDate;
    }

    public void setSensorInstallationDate(Date sensorInstallationDate) {
        this.sensorInstallationDate = sensorInstallationDate;
    }

    public int getTreeId() {
        return treeId;
    }

    public void setTreeId(int treeId) {
        this.treeId = treeId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
