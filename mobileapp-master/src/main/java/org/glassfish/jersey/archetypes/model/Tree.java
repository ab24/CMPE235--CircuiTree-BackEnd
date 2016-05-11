package org.glassfish.jersey.archetypes.model;

import java.util.Date;

/**
 * Created by akhilakishore on 29/04/16.
 */
public class Tree {
    private int treeId;
    private String treeName;
    private Date treeInstallationDate;
    private Date treeMaintenanceDate;
    private int treeCount;
    private String treeLocation;
    private String adminId;

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

    public String getTreeLocation() {
        return treeLocation;
    }

    public void setTreeLocation(String treeLocation) {
        this.treeLocation = treeLocation;
    }

    public int getTreeCount() {
        return treeCount;
    }

    public void setTreeCount(int treeCount) {
        this.treeCount = treeCount;
    }

    public Date getTreeInstallationDate() {
        return treeInstallationDate;
    }

    public void setTreeInstallationDate(Date treeInstallationDate) {
        this.treeInstallationDate = treeInstallationDate;
    }

    public Date getTreeMaintenanceDate() {
        return treeMaintenanceDate;
    }

    public void setTreeMaintenanceDate(Date treeMaintenanceDate) {
        this.treeMaintenanceDate = treeMaintenanceDate;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }
}
