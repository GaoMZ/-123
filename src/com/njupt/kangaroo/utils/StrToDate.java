/**
 * 
 */
package com.njupt.kangaroo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 高明珠
 *
 */
public class StrToDate {

	/**
	 * 
	 */
	public StrToDate() {
		// TODO Auto-generated constructor stub
	}
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public  Date StringToDate(String str) {

	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	public  Date TimeStringToDate(String str) {

	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return date;
	}	

}
