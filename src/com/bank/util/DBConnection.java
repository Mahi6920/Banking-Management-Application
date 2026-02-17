package com.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
	
	private static final String URL = "jdbc:mysql://localhost:3306/Banking_Management";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "mahi";

	public static Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection(URL, USERNAME, PASSWORD); 
	}
	
}
