package com.bank.controller;

import java.util.Scanner;

import com.bank.model.Admin;
import com.bank.model.User;
import com.bank.service.AdminService;
import com.bank.service.UserService;

public class BankApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("** Welcome to Banking Application **");
		System.out.println();

		// creating objects
		AdminService adminService = new AdminService();
		UserService userService = new UserService();


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
							System.out.println();

							boolean adminLoginLoop = true;
							while(adminLoginLoop) {
								System.out.println("1. Account Creation");
								System.out.println("2. Account Deletion");
								System.out.println("3. View Users");
								System.out.println("4. Exit");
								System.out.print("Enter Your Option: ");
								int adminLoginOption = scanner.nextInt();
								scanner.nextLine();
								System.out.println();

								switch(adminLoginOption) {
								case 1:
									// account creation
									System.out.println("Enter Name: ");
									String userName = scanner.nextLine();
									System.out.println("Enter E-mail: ");
									String userMail = scanner.nextLine();
									System.out.println("Enter Initial Balance: ");
									double initialAmount = scanner.nextDouble();

									User user = new User(userName, userMail, initialAmount);

									if (adminService.accountCreation(user)) {
										System.out.println("Account Created Succesful.");
										System.out.println();
									} else {
										System.err.println("Account Creation Fail.");
										adminLoginLoop = true;
									}


									break;

								case 2:
									// account deletion
									System.out.println("Enter Account Number: ");
									long delitionAccNumber = scanner.nextLong();

									User delUser = new User(delitionAccNumber);

									if (adminService.accountDeletion(delUser)) {
										System.out.println("Account Deletion Succesfull.");
									} else {
										System.out.println("Account Deletion Fail.");
									}

									break;

								case 3:
									// view users
									adminService.viewUsers();
									break;

								case 4:
									adminLoginLoop = false;
									break;

								default:
									System.out.println("Invalid Option Entered");
									break;
								}
							}


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
				System.out.println("Login Section");
				System.out.println();

				boolean userLoop = true;
				while (userLoop) {
					System.out.println("1. Login");
					System.out.println("2. Registration");
					System.out.println("3. Exit");
					System.out.println("Enter Option: ");
					int userOption = scanner.nextInt();
					scanner.nextLine();

					switch(userOption) {
					case 1:
						// login
						System.out.println("Enter E-mail: ");
						String mail = scanner.nextLine();
						System.out.println("Enter Password: ");
						String password = scanner.nextLine();

						User userLogin = new User(mail, password);

						// login function						
						if (userService.login(userLogin)) {
							System.out.println("Login Successful.");
							System.out.println();

							// after login functions
							
							boolean loginLoop = true;
							while(loginLoop) {
								
								System.out.println("1. View Balance");
								System.out.println("2. Amount Transfer");
								System.out.println("3. Exit");
								System.out.println("Enter Option: ");
								int loginOption = scanner.nextInt();
								
								switch(loginOption) {
								case 1:
									// view balance
									System.out.println("View Balance");
									System.out.println();

									User user = new User(mail);
									
									double viewAmount = userService.viewBalance(user);
									System.out.println("Amount: " + viewAmount);
									
									break;

								case 2:
									// account transfer
									System.out.println("Account Transfer");
									System.out.println();
									
									System.out.println("Enter Amount: ");
									double transferAmount = scanner.nextDouble();
									System.out.println("Enter Account Number: ");
									long accountNumber = scanner.nextLong();
									
									User transferUser = new User(mail, transferAmount, accountNumber);
									
									if (userService.amountTransfer(transferUser)) {
										System.out.println("Amount Transfered Succesful.");
										System.out.println();
									} else {
										System.out.println("Amount Transfer Fail.");
									}
									
									break;
									
								case 3:
									loginLoop = false;
									break;

								default:
									System.out.println("Invalid Option");
									break;
								}
							}

						} else {
							System.out.println("Login Fail.");
						}

						break;

					case 2:
						// registration
						System.out.println("Enter E-mail: ");
						String regMail = scanner.nextLine();
						System.out.println("Enter Password: ");
						String regPassword = scanner.nextLine();

						User userRegistration = new User(regMail, regPassword);

						if (userService.registration(userRegistration)) {
							System.out.println("Registration Succesfull.");
						} else {
							System.out.println("Registration Fail.");
						}

						break;

					case 5:
						userLoop = false;
						break;

					default:
						System.out.println("Invalid Option");
						break;
					}

				}

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

		scanner.close();

	}

}
