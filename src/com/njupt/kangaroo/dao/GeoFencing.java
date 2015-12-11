package com.njupt.kangaroo.dao;

public class GeoFencing {

	private String username;
	private double longtitude;
	private double latitude;
	private double distance;
	
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public GeoFencing() {
		// TODO Auto-generated constructor stub
	}

	public GeoFencing(String username,double longtitude,double latitude, double distance) {
		// TODO Auto-generated constructor stub
		this.setUsername(username);
		this.setLongtitude(longtitude);
		this.setLatitude(latitude);
		this.setDistance(distance);
	}

	@Override
	public String toString() {
		return "GeoFencing [username=" + username + ", longtitude="
				+ longtitude + ", latitude=" + latitude + ", distance="
				+ distance + "]";
	}


}
