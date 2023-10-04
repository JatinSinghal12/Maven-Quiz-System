package com.virtusa.quiz.model;

public class Admin {

	private String emailId;
	private String password;

	
	public Admin() {
		super();
		
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [emailId=" + emailId + ", password=" + password + "]";
	}

}
