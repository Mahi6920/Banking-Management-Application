package com.bank.model;

public class User {
	private int id;
	private String name;
	private String mail;
	private String password;
	private double amount;
	private long acountNumber;
	
	// login && registration
	public User(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}
	
	// view balance
	public User(String mail) {
		this.mail = mail;		
	}
	
	// transfer amount
	public User(String mail, double amount, long acountNumber) {
		this.mail = mail;
		this.amount = amount;
		this.acountNumber = acountNumber;
	}
	
	public long getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(long acountNumber) {
		this.acountNumber = acountNumber;
	}

	public User(long accountNumber) {
		this.acountNumber = accountNumber;
	}
	
	public User(String name, String mail, double amount) {
		this.name = name;
		this.mail = mail;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
