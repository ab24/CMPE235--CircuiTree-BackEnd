package org.glassfish.jersey.archetypes.services;

import net.sf.json.JSONArray;
import org.glassfish.jersey.archetypes.DatabaseConnectionProvider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * Created by akhilakishore on 29/04/16.
 */
public class AdminServiceImpl implements AdminService {
    private final DatabaseConnectionProvider db;
    public AdminServiceImpl(DatabaseConnectionProvider db){ this.db = db;}

    public String adminLogIn(String adminId, String password) throws SQLException, ClassNotFoundException {
        String stoken = "";
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("select * from ADMIN where adminID=? and password =?");
        preparedStatement.setString(1,adminId);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            stoken = resultSet.getString(1);
            PreparedStatement updatePreparedStatement = db.getConnection().prepareStatement("Update ADMIN set sessionToken = ? where adminId =?");
            updatePreparedStatement.setString(1,stoken);
            updatePreparedStatement.setString(2,adminId);
            updatePreparedStatement.executeUpdate();
            updatePreparedStatement.close();
        }
        resultSet.close();
        return stoken;
    }

    public boolean isLogin(String sessionToken) throws SQLException {
        //String sToken =" ";
        PreparedStatement preparedStatement = db.getConnection().prepareStatement("SELECT * from ADMIN where sessionToken = ?");
        preparedStatement.setString(1,sessionToken);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }


    public static void main(String args[]) throws Exception {
        new AdminServiceImpl(DatabaseConnectionProvider.getDatabaseProvider()).adminLogIn("akhila@gmail.com", "5678");
    }
}
