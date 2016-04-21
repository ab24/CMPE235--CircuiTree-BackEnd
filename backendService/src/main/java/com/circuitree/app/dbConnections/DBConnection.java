package com.circuitree.app.dbConnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;



public class DBConnection {
	
	
	private final static String USER = "root"; 
	private final static String PASSWORD = "root123";
	private final static String URL="jdbc:mysql://localhost:3306/mydb";
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	
	/**
	 * Static method that returns the instance for the singleton
	 *
	 * @return {Connection} connection
	 **/
	public  Connection getConnection() {
		Connection connection=null;
		try {
			Class.forName(DRIVER);			
				connection = DriverManager.getConnection(URL, USER, PASSWORD);			 
		} catch (ClassNotFoundException e) {
			
		}catch (SQLException e) {
		}		
		return connection;
	}	
	
	public void closeConnection(Connection connection){
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				
			}
		}
	}
	
}
