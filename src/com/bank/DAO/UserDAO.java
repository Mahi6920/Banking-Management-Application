package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.midi.SysexMessage;

import com.bank.model.User;
import com.bank.util.DBConnection;

public class UserDAO {

	// login
	public boolean login(User user) {
		
		String sql = "SELECT * FROM user WHERE mail = ? AND password = ?;";
		
		try(Connection connection = DBConnection.getConnection()) {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getMail());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}	
				
		return false;
	}
	
	// registration
	public boolean registration(User user) {
		
		String sql = "UPDATE user SET password = ? WHERE mail = ?";
		
		try(Connection connection = DBConnection.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getMail());
			
			if (preparedStatement.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	// view balance
	public double viewBalance(User user) {
		
		String sql = "SELECT amount FROM user WHERE mail = ?;";
		
		try(Connection connection = DBConnection.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getMail());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				return resultSet.getDouble("amount");
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return 0;
	}
	
	// amount transfer
	public boolean amountTransfered(User user) {
		
		String withdrawSql = "UPDATE user SET amount = amount - ? WHERE mail = ?;";
		String depositSql = "UPDATE user SET amount = amount + ? WHERE accountNumber = ?;";
		
		try(Connection connection = DBConnection.getConnection()) {
			connection.setAutoCommit(false);
			
			PreparedStatement preparedStatement1 = connection.prepareStatement(withdrawSql);
			preparedStatement1.setDouble(1, user.getAmount());
			preparedStatement1.setString(2, user.getMail());
			
			if (preparedStatement1.executeUpdate() > 0) {
				
//				connection.setAutoCommit(true);
//				return true;
				
				PreparedStatement preparedStatement2 = connection.prepareStatement(depositSql);
				preparedStatement2.setDouble(1, user.getAmount());
				preparedStatement2.setLong(2, user.getAcountNumber());
				
				if (preparedStatement2.executeUpdate() > 0) {
					connection.setAutoCommit(true);
					return true;
				}
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
}
