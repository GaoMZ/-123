package com.njupt.kangaroo.dao;

public class UseTimeControl {

	private String username;
	private int start;
	private int end;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public UseTimeControl() {
		// TODO Auto-generated constructor stub
	}
	public UseTimeControl(String username,int start,int end) {
		// TODO Auto-generated constructor stub
		this.setUsername(username);
		this.setStart(start);
		this.setEnd(end);
	}
	@Override
	public String toString() {
		return "UseTimeControl [username=" + username + ", start=" + start
				+ ", end=" + end + "]";
	}
}
