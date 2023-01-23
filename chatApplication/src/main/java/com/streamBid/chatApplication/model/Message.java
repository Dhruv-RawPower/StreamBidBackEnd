package com.streamBid.chatApplication.model;

import java.util.Date;

public class Message {
	
	    private String fullName;
	    private String message;
	    private String date;
		public String getFullName() {
			return fullName;
		}
		public void setFullName(String fullName) {
			this.fullName = fullName;
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
		public Message(String fullName, String message, String date) {
			super();
			this.fullName = fullName;
			this.message = message;
			this.date = date;
		}
		public Message() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "Message [fullName=" + fullName + ", message=" + message + ", date=" + date + "]";
		}

	    
}
