package org.glassfish.jersey.archetypes.services;

import java.sql.SQLException;

/**
 * Created by akhilakishore on 29/04/16.
 */
public interface AdminService {
    public boolean isLogin(String sessionToken) throws SQLException;
    public String adminLogIn(String adminId, String password) throws SQLException, ClassNotFoundException;
}
