package com.njupt.kangaroo.utils;

public class getAddTimeSubString {

	private String addTime;
	/**
	 * @param addTime="2015-05-13 12:29:00"
	 * 只获得年月日的字符串
	 */
	public getAddTimeSubString(String addTime) {
		this.addTime=addTime;
	}
	public getAddTimeSubString() {
		// TODO Auto-generated constructor stub
	}
	private String getAddTimeSubString(String addTime) {
		char [] buf=new char[10];
		String subString=null;
		addTime.getChars(0, 10, buf, 0);
		subString=new String(buf);
		//subString=String.valueOf(buf);
		return subString;
	}
}
