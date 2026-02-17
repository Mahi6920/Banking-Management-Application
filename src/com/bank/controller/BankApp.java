package com.bank.controller;

import java.util.Scanner;

import com.bank.model.Admin;
import com.bank.service.AdminService;

public class BankApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("** Welcome to Banking Application **");
		System.out.println();
		
		// creating objects
		AdminService adminservice = new AdminService();
		
		
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
						// login
						break;
						
					case 2:
						// registration
						System.out.println("Enter Your Name: ");
						String name = scanner.nextLine();
						System.out.println("Enter Your E-mail: ");
						String mail = scanner.nextLine();
						System.out.println("Enter Your Password: ");
						String password = scanner.nextLine();
						
						Admin admin = new Admin(name, mail, password);
						
												
						if(adminservice.registration(admin)) {
							System.out.println("Registration Successfull.");
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
				// for admin
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
