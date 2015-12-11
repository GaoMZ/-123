package com.njupt.kangaroo.dao;

public class Track {
	
	private String username;
	private double longtitude;
	private double latitude;
	private String addTime;
	
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
	}
}
