package org.glassfish.jersey.archetypes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by akhilakishore on 29/04/16.
 */
public class DatabaseConnectionProvider {
    private static DatabaseConnectionProvider dataBaseConnectionProvider;

    private Connection connection;

    public DatabaseConnectionProvider() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/circuittree?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
                "root", "root");
    }

    public static DatabaseConnectionProvider getDatabaseProvider() throws SQLException, ClassNotFoundException {
        if (dataBaseConnectionProvider == null) {
            dataBaseConnectionProvider = new DatabaseConnectionProvider();
        }
        return dataBaseConnectionProvider;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet select(String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    public int insertUpdateDelete(String query) throws SQLException {
        return connection.createStatement().executeUpdate(query);
    }

    public static void main(String args[]) throws Exception {
        DatabaseConnectionProvider db = getDatabaseProvider();
        ResultSet rs = db.select("select * from admin");
        System.out.print("Connection done");

    }
}
