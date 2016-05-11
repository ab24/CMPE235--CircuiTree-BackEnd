package org.glassfish.jersey.archetypes.services;

import org.glassfish.jersey.archetypes.DatabaseConnectionProvider;

import java.sql.SQLException;

/**
 * Created by akhilakishore on 29/04/16.
 */
    public class ServiceFactory {
    private static ServiceFactory serviceFactory;

        private AdminService adminService;
        private SensorService sensorService;
        private TreeService treeService;

        private ServiceFactory() throws Exception, ClassNotFoundException{
        final DatabaseConnectionProvider databaseConnectionProvider = DatabaseConnectionProvider.getDatabaseProvider();
        adminService = new AdminServiceImpl(databaseConnectionProvider);
        sensorService = new SensorServiceImpl(databaseConnectionProvider);
        treeService = new TreeServiceImpl(databaseConnectionProvider);
        }

    public synchronized static ServiceFactory getServiceFactory() throws Exception {
        if(serviceFactory==null){
            serviceFactory = new ServiceFactory();
        }
        return serviceFactory;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public SensorService getSensorService() {
        return sensorService;
    }

    public TreeService getTreeService() {
        return treeService;
    }
}



