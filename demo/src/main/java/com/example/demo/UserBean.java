package com.example.demo;

public class UserBean {
	String userName;
	String password;
	String idString;
	double voiceRange;
	int status;
	
	public UserBean() {
		this.userName=userName;
		this.idString=idString;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;		
	}
	public void setPassword(String password) {
		this.password=password;
	}

	public String getIdString() {
		return idString;
	}

	public void setIdString(String idString) {
		this.idString = idString;
	}

	public double getVoiceRange() {
		return voiceRange;
	}

	public void setVoiceRange(double voiceRange) {
		this.voiceRange = voiceRange;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
