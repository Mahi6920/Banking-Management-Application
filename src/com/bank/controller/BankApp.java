package com.bank.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.bank.model.Admin;
import com.bank.service.AdminService;

public class BankApp {

	public static void main(String[] args) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("** Welcome to Banking Application **");
		System.out.println();
		
		// creating objects
		AdminService adminService = new AdminService();
		
		
		boolean loop = true;
		while (loop) {
			System.out.println("1. Admin");
			System.out.println("2. User");
			System.out.println("3. Exit");
			System.out.print("Enter Your Option: ");
			int option = scanner.nextInt();
			System.out.println();
			
			switch(option) {
			case 1:
				// admin
				boolean adminLoop = true;
				while (adminLoop) {
					System.out.println("1. Login");
					System.out.println("2. Registration");
					System.out.println("3. Exit");
					System.out.print("Enter Your Option: ");
					int adminOption = scanner.nextInt();
					scanner.nextLine();
					System.out.println();
					
					switch(adminOption) {
					case 1:
						
						System.out.println("Enter Your E-mail: ");
						String loginMail = scanner.nextLine();
						System.out.println("Enter Your Password: ");
						String loginPassword = scanner.nextLine();
						
						// call admin model
						Admin adminLogin = new Admin(loginMail, loginPassword);
						
						// login
						if(adminService.login(adminLogin)) {
							// after login functions
							System.out.println("Login Success.");
							
							
						} else {
							System.out.println("Login Fail. Enter Valid Details");
						}
						
						break;
						
					case 2:
						// registration
						System.out.println("Enter Your Name: ");
						String name = scanner.nextLine();
						System.out.println("Enter Your E-mail: ");
						String mail = scanner.nextLine();
						System.out.println("Enter Your Password: ");
						String password = scanner.nextLine();
						
						Admin adminRegistration = new Admin(name, mail, password);
						
												
						if(adminService.registration(adminRegistration)) {
							System.out.println("Registration Successful.");
						} else {
							System.out.println("Registration Fail.");
						}
						
						break;
						
					case 3:
						adminLoop = false;
						break;
						
					default:
						System.out.println("Invalid option");
						break;
					}
				}
				break;
				
			case 2: 
				// for user
				break;
				
			case 3:
				loop = false;
				System.out.println("Thank You");
				break;
				
			default:
				System.out.println("Invalid Option");
				break;
				
			}
			
		}

	}

}
