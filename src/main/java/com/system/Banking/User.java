package com.system.Banking;

public class User {
	
	private String userName;
	private String accountNumber;
	private String password;
	private int balance;
	private String transaction;
	
	public User() {
		
	}
	
	public User(String string, String string2, String string3, int i, String tr) {
		// TODO Auto-generated constructor stub
		accountNumber = string;
		userName = string2;
		password = string3;
		balance = i;
		transaction=tr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getUserName()+ " " +getAccountNumber() + " "+ getBalance()+" " + getTransaction();
	}
	
	

}
