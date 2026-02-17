package com.bank.service;

import com.bank.DAO.AdminDAO;
import com.bank.model.Admin;

public class AdminService {
	
	private AdminDAO adminDao = new AdminDAO();

	public AdminService() {
		
	}
	
	public boolean registration(Admin admin) {
		
		if (admin == null) {
			return false;
		} else if (admin.getMail() == null) {
			System.err.println("Enter E-mail");
			return false;
		} else if (admin.getPassword() == null || admin.getPassword().length() < 6) {
			System.err.println("Password Must Contain 8 Charectors");
			return false;
		}
		
		return adminDao.registration(admin);
	}
	
}
