package com.virtusa.quiz.model;

public class User implements Comparable<User> {

	private int id;
	private String name;
	private String emailId;
	private String phoneNo;
	private String password;

	

	

	public User() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User(String name, String emailId, String phoneNo, String password) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
	}

	public User(int id,String name, String emailId, String phoneNo, String password) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", password="
				+ password + "]";
	}

	@Override
	public int compareTo(User o) {
		
		return this.getName().compareToIgnoreCase(o.getName());
	}
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		boolean r = true;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (this.getName() != other.getName()) {
			r = false;
		}
		return r;
	}

}
