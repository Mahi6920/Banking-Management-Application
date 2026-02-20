package com.bank.service;

import com.bank.DAO.UserDAO;
import com.bank.model.User;

public class UserService {

	private UserDAO userDao = new UserDAO();

	// login
	public boolean login(User user) {

		if (user == null) {
			return false;
		} else if (user.getMail() == null) {
			System.out.println("Enter E-mail: ");
			return false;
		} else if (user.getPassword().length() < 6) {
			System.out.println("Enter valid password");
			return false;
		}


		return userDao.login(user);
	}

	// registration 
	public boolean registration(User user) {
		if (user == null) {
			return false;
		} else if (user.getMail() == null) {
			System.err.println("Enter E-mail");
			return false;
		} else if (user.getPassword().length() < 6) {
			System.err.println("Password must be greater than 6 charecters.");
		}

		return userDao.registration(user);
	}

	// view balance
	public double viewBalance(User user) {

		return userDao.viewBalance(user);
	}

	// transfer amount 
	public boolean amountTransfer(User user) {

		if (user == null) {
			System.out.println("Enter Details");
			return false;
		} else if(user.getAmount() < 1) {
			System.out.println("Invalid amount");
			return false;
		} else if (user.getAcountNumber() == 0) {
			return false;
		} else if (user.getMail() == null) {
			return false;
		}

		return userDao.amountTransfered(user);
	}

}
