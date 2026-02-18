package com.bank.service;

import java.sql.SQLException;

import com.bank.DAO.AdminDAO;
import com.bank.model.Admin;

public class AdminService {
	
	private AdminDAO adminDao = new AdminDAO();
	
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
	
	public boolean login(Admin admin) throws SQLException {
				
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
	
}
