package com.virtusa.quiz.dao;


import java.io.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.virtusa.quiz.exception.UserDataException;

public abstract class BaseDao {
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		//	e.printStackTrace()
		}
	}

	protected Connection getConnection() throws UserDataException  {
		Connection conn = null;
		try {
			try(FileReader reader=new FileReader("Properties/db.properties")){

			Properties props = new Properties();

			props.load(reader);

			String username = props.getProperty("datasource.username");

			String url = props.getProperty("datasource.url");

			String pass = props.getProperty("datasource.password");
			conn = DriverManager.getConnection(url, username, pass);
			}
			
		} catch (SQLException e) {
			throw new UserDataException("Connection Problem");

		} catch (Exception e) {
			
			throw new UserDataException("Please Check Properties file");
		} 
		return conn;
	}

}
