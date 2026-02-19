package com.bank.service;

import com.bank.DAO.AdminDAO;
import com.bank.model.Admin;
import com.bank.model.User;

public class AdminService {
	
	private AdminDAO adminDao = new AdminDAO();
	
	// admin registration
	public boolean registration(Admin admin) {
		
		if (admin == null) {
			return false;
		} else if (admin.getMail() == null) {
			System.err.println("Enter E-mail");
			return false;
		} else if (admin.getPassword() == null || admin.getPassword().length() < 6) {
			System.err.println("Password Must Contain 6 Charectors");
			return false;
		}
		
		return adminDao.registration(admin);
	}
	
	// admin login
	public boolean login(Admin admin) {
				
		if (admin == null) {
			System.err.println("Enter Valid Details.");
			return false;
		} else if (admin.getMail() == null) {
			System.err.println("Enter Valid Mail.");
			return false;
		} else if (admin.getPassword().length() < 6) {
			System.err.println("Invalid Password");
			return false;
		}
		
		return adminDao.login(admin);
	}
	
	// account creation
	public boolean accountCreation(User user) {
		
		if (user == null) {
			System.err.println("Enter Details");
			return false;
		} else if (user.getName() == null) {
			System.err.println("Enter Name");
			return false;
		} else if (user.getMail() == null) {
			System.err.println("Enter E-mail");
			return false;
		} else if (user.getAmount() <= 0.0) {
			System.err.println("Enter Valid Amount");
			return false;
		}
		
		return adminDao.accountCreation(user);
	}
	
	// account deletion
	public boolean accountDeletion(User user) {
		
		if (user.getAcountNumber() == 0) {
			System.err.println("Enter Account Number");
			return false;
		} else if (user.getAcountNumber() < 0) {
			return false;
		}
		
		return adminDao.accountDeletion(user);
	}
	
	// view users
	public void viewUsers() {
		adminDao.viewUsers();
	}
}
