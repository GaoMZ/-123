package com.njupt.kangaroo.utils;

public class GetAddTimeSubString {

	private String addTime;
	/**
	 * @param addTime="2015-05-13 12:29:00"
	 * 只获得年月日的字符串
	 */
	public GetAddTimeSubString(String addTime) {
		this.addTime=addTime;
	}
	public GetAddTimeSubString() {
		// TODO Auto-generated constructor stub
	}
	public String getAddTimeSubString(String addTime) {
		char [] buf=new char[10];
		String subString=null;
		addTime.getChars(0, 10, buf, 0);
		subString=new String(buf);
		//subString=String.valueOf(buf);
		return subString;
	}
}
