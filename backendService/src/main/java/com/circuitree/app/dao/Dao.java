package com.circuitree.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.circuitree.app.dbConnections.DBConnection;


public class Dao {
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	public JSONObject isLogin(String token) {

		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
        
		try {
			connection = dbConnection.getConnection();
			// getting the subscription list
			PreparedStatement p = connection.prepareStatement("SELECT id, stoken , type FROM circuitreedb.users WHERE " + "stoken= ?");

			p.setString(1, token);
			int id = 0;
			String sToken = "";
			ResultSet rs = p.executeQuery();
			while (rs.next()) {

				id = Integer.parseInt(rs.getString("id"));
				sToken = rs.getString("stoken");

			}
			rs.close();
			p.close();
			
			jsonObject.put("id", id);
			jsonObject.put("response", "ok ");
			jsonObject.put("status", "200");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at isLogin",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;

	}
	
	
	public JSONObject checkComments(String token) {

		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
        
		try {
			connection = dbConnection.getConnection();
			// getting the subscription list
			PreparedStatement p = connection.prepareStatement("SELECT firstName, LastName, comments FROM circuitreedb.users WHERE " + "stoken= ?");

			p.setString(1, token);
			String firstName = null;
			String lastName = null;
			String comments=null;
			
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				
				firstName=rs.getString("firstName");
				lastName=rs.getString("LastName");
				comments=rs.getString("comments");

			}
			rs.close();
			p.close();
			if(comments == null){
				jsonObject.put("response", "no");
			}else{
				String name= firstName+" "+lastName;
			jsonObject.put("name", name);
			jsonObject.put("comments", comments);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at commentsCheck",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;

	}
	
	
	public JSONObject logout(String token) {

		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
		try {
			connection = dbConnection.getConnection();

			PreparedStatement pu = connection.prepareStatement("update circuitreedb.users set stoken = NULL where stoken = ?");

			pu.setString(1, token);
			pu.executeUpdate();

			pu.close();
			jsonObject.put("response", "ok ");
			jsonObject.put("status", "200");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at logOut",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;
	}
	
	public JSONObject login(String userId, String userPwd) {

		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
		
		try {
			connection = dbConnection.getConnection();
			String id = "";
			String pwd = "";
			String token = "";
			PreparedStatement p = connection.prepareStatement("SELECT firstName FROM circuitreedb.users WHERE " + "email= ? and  password= ?");
			p.setString(1, userId);
			p.setString(2, userPwd);
			ResultSet rs = p.executeQuery();
			System.out.println(rs.getFetchSize());

			while (rs.next()) {
					
					id = rs.getString("firstName");			
					
					 token=userId;
					PreparedStatement pi = connection.prepareStatement("update circuitreedb.users set stoken = ? where email = ?");
					pi.setString(1, token);
					pi.setString(2, userId);
					pi.executeUpdate();
					pi.close();
					jsonObject.put("sessionToken", token);
					jsonObject.put("response", "ok ");
					jsonObject.put("status", "200");
						}
			
			rs.close();
			p.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at Login",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;
	}
	
	public JSONObject register(String firstname, String lastName, String email, String contactNo, String address1, String address2,String city, String state,String country, String postalCode,String password) {

		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
		String SToken="";
		
		try {
			connection = dbConnection.getConnection();
			PreparedStatement p = connection.prepareStatement("Insert into circuitreedb.users values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			p.setString(1, firstname);
			p.setString(3, lastName);
			p.setString(2, email);
			p.setString(4, contactNo);
			p.setString(5, address1);
			p.setString(6, address2);
			p.setString(7, city);
			p.setString(8, state);
			p.setString(9, postalCode);
			p.setString(10, country);
			p.setString(11, SToken);
			p.setString(12, password);
			p.setString(13,null);
			p.executeUpdate();
			{
					jsonObject.put("response", "ok ");
					jsonObject.put("status", "200");
						}
			
			p.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at register",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;
	}


	public JSONObject addComments(String token, String comments) {
		JSONObject jsonObject = new JSONObject();
		Connection connection = null;
		DBConnection dbConnection = new DBConnection();
        
		try {
			connection = dbConnection.getConnection();
			// getting the subscription list
			PreparedStatement p = connection.prepareStatement("update circuitreedb.users set comments = ? where sToken = ?");
			p.setString(1, comments);
			p.setString(2, token);
			
			 p.executeUpdate();
			
			p.close();
			jsonObject.put("response", "ok");
			
			

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception at commentsCheck",e);
		} finally {
			dbConnection.closeConnection(connection);
		}
		return jsonObject;
		
	}

}
