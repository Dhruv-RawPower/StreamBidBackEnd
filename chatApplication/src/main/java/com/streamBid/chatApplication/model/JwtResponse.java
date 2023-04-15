package com.streamBid.chatApplication.model;

public class JwtResponse {
	
	String token;
	String fullName;
	String imageUrl;
	
	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getToken() {
		return token;
	}



	



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", fullName=" + fullName + ", imageUrl=" + imageUrl + "]";
	}



	public JwtResponse(String token, String fullName, String imageUrl) {
		super();
		this.token = token;
		this.fullName = fullName;
		this.imageUrl = imageUrl;
	}



	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}





}
