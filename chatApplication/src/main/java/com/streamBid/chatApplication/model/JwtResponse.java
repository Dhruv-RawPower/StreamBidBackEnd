package com.streamBid.chatApplication.model;

public class JwtResponse {
	
	String token;
	String fullName;
	
	
	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", fullName=" + fullName + "]";
	}



	public JwtResponse(String token, String fullName) {
		super();
		this.token = token;
		this.fullName = fullName;
	}



	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}





}
