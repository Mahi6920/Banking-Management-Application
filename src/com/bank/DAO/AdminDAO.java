package com.bank.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.bank.model.Admin;
import com.bank.model.User;
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
				System.out.println("Email already exists.");
			} else {
				System.out.println("Database Error Occured.");
			}
		}
		return false;
	}

	public boolean login(Admin admin) {

		String sql = "SELECT * FROM admin WHERE mail = ? AND password = ?;";

		try(Connection connection = DBConnection.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, admin.getMail());
			preparedStatement.setString(2, admin.getPassword());

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	// account creation
	public boolean accountCreation(User user) {
		String sql = "INSERT INTO user(name, mail, amount) VALUES (?, ?, ?);";

		long accountNumber = 0;

		boolean returnVal = false;

		try(Connection connection = DBConnection.getConnection()) {
			PreparedStatement prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getMail());
			prepareStatement.setDouble(3, user.getAmount());

			//			int execute = 
			prepareStatement.executeUpdate();
			//			if (execute > 0) {
			//				returnVal = true;
			//			}

			// get account number
			String accountSQL = "SELECT * FROM user WHERE mail = ?;";

			try {
				PreparedStatement prepareStatement1 = connection.prepareStatement(accountSQL);
				prepareStatement1.setString(1, user.getMail());

				ResultSet resultSet = prepareStatement1.executeQuery();

				while(resultSet.next()) {

					accountNumber = 1003441000 + resultSet.getInt(1);

					System.out.println("Account Number: " + accountNumber);					

					// storing account number by using mail with update query
					try {
						String accountNumberSQL = "UPDATE user SET accountNumber = ? WHERE mail = ?;";

						PreparedStatement prepareStatement2 = connection.prepareStatement(accountNumberSQL);
						prepareStatement2.setLong(1, accountNumber);
						prepareStatement2.setString(2, user.getMail());

						int execute = prepareStatement2.executeUpdate();
						if (execute > 0) {
							returnVal = true;
						}

					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}

				}
				//				returnVal = true;

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return returnVal;
	}

	// account deletion 
	public boolean accountDeletion(User user) {
		String sql = "DELETE FROM user WHERE accountNumber = ?;";

		try(Connection connection = DBConnection.getConnection()) {

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, user.getAcountNumber());

			return preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		return false;
	}

	// view users
	public void viewUsers() {
		Map<String, String> hs = new HashMap<>();

		String sql = "SELECT * FROM user;";

		try(Connection connection = DBConnection.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			System.out.println("+----+------------+----------------------+----------+--------------+");
			System.out.println("| Id | Name       | E-Mail               | Amount   | Account No   |");
			System.out.println("+----+------------+----------------------+----------+--------------+");

			while (resultSet.next()) {
			    System.out.printf("| %-2d | %-10s | %-20s | %-8.2f | %-12d |%n",
			            resultSet.getInt("id"),
			            resultSet.getString("name"),
			            resultSet.getString("mail"),
			            resultSet.getDouble("amount"),
			            resultSet.getLong("accountNumber"));
			}

			System.out.println("+----+------------+----------------------+----------+--------------+");


		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
