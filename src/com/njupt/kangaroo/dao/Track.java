package com.njupt.kangaroo.dao;

import com.njupt.kangaroo.utils.GetAddTimeSubString;

public class Track {
	
	private String username;
	private double longtitude;
	private double latitude;
	private String addTime;
	//addTime="2015-05-13 12:29:00"   只获得年月日的字符串,如："2015-05-13"
	private String dateString;	
	private int isCommit;
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

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

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}



	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "Track [username=" + username + ", longtitude=" + longtitude
				+ ", latitude=" + latitude + ", addTime=" + addTime + "]";
	}

	public Track() {
	
	}
	public Track(String username,double longtitude,double latitude,String addTime) {
		this.setUsername(username);
		this.setLongtitude(longtitude);
		this.setLatitude(latitude);
		this.setAddTime(addTime);
		GetAddTimeSubString string=new GetAddTimeSubString();
		this.dateString=string.getAddTimeSubString(addTime);
		this.isCommit=0;
	}
}
