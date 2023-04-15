package com.streamBid.chatApplication.model;

import java.util.Date;

public class Message {
	
	    private String fullName;
	    private String imageUrl;
	    private String message;
	    private String date;
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		@Override
		public String toString() {
			return "Message [fullName=" + fullName + ", imageUrl=" + imageUrl + ", message=" + message + ", date="
					+ date + "]";
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Message(String fullName, String imageUrl, String message, String date) {
			super();
			this.fullName = fullName;
			this.imageUrl = imageUrl;
			this.message = message;
			this.date = date;
		}
		

	    
}
