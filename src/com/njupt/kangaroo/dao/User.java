package com.njupt.kangaroo.dao;

import java.io.Serializable;

public class User implements Serializable{
	
    private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	//以分钟计时
	private int timeOfContinuousUse;
	private int timeOfContinuousListen;	
	private String	channel_id;
	private int isCommit=0;
	
	public int getIsCommit() {
		return isCommit;
	}

	public void setIsCommit(int isCommit) {
		this.isCommit = isCommit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeOfContinuousUse() {
		return timeOfContinuousUse;
	}

	public void setTimeOfContinuousUse(int timeOfContinuousUse) {
		this.timeOfContinuousUse = timeOfContinuousUse;
	}

	public int getTimeOfContinuousListen() {
		return timeOfContinuousListen;
	}

	public void setTimeOfContinuousListen(int timeOfContinuousListen) {
		this.timeOfContinuousListen = timeOfContinuousListen;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", timeOfContinuousUse=" + timeOfContinuousUse
				+ ", timeOfContinuousListen=" + timeOfContinuousListen
				+ ", channel_id=" + channel_id
				+ "]";
	}

	public String getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String username,String password,int timeOfContinuousUse ,
			int timeOfContinuousListen,String channel_id,int isCommit) {
		// TODO Auto-generated constructor stub
		this.channel_id=channel_id;
		this.password=password;
		this.timeOfContinuousListen=timeOfContinuousListen;
		this.timeOfContinuousUse=timeOfContinuousUse;
		this.username=username;
		this.isCommit=isCommit;
	} 
}
