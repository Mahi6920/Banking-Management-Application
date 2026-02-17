package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.model.Admin;
import com.bank.util.DBConnection;

public class AdminDAO {
	
	public boolean registration(Admin admin) {
		String sql = "INSERT INTO admin(name, mail, password) VALUES (?, ?, ?);";
		
		try(Connection connection = DBConnection.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin.getName());
			preparedStatement.setString(2, admin.getMail());
			preparedStatement.setString(3, admin.getPassword());
			
			return preparedStatement.executeUpdate() > 0;
			
		} catch (SQLException e) {			
			// duplicate mails
			if(e.getSQLState().equals("23000")) {
				System.out.println("Email Already Occured.");
			} else {
				System.out.println("Database Error Occured.");
			}
		}
		
		return false;
	}
	
}
