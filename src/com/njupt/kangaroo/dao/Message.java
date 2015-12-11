package com.njupt.kangaroo.dao;

public class Message {

	private String username;
	private String sender;
	private String receiver;
	private String date;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message [username=" + username + ", sender=" + sender
				+ ", receiver=" + receiver + ", date=" + date + "]";
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}
	public Message(String username,String sender, String receiver, String date) {
		this.setUsername(username);
		this.setSender(sender);
		this.setReceiver(receiver);
		this.setDate(date);
	}
}
