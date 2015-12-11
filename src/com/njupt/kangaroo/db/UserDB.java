package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.njupt.kangaroo.dao.*;

public class UserDB {

	public DBOpenHelper helper;
	public UserDB(Context context) {
		helper = new DBOpenHelper(context);
	}

	/**
	 * @param username用户名
	 * @return  在数据库中查找该用户，如果存在该用户，则返回该用户所有信息,否则为null
	 */
	public User selectInfo(String username) {
		User u = new User();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from user where username=?",
			new String[] { username + "" });
		if (c.moveToFirst()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setPassword(c.getString(c.getColumnIndex("password")));
			u.setTimeOfContinuousUse(c.getInt(c.getColumnIndex("timeOfContinuousUse")));
			u.setTimeOfContinuousListen(c.getInt(c.getColumnIndex("timeOfContinuousListen")));
			u.setChannel_id(c.getString(c.getColumnIndex("channelId")));
			u.setIsCommit(c.getInt(c.getColumnIndex("iscommit")));
			Log.i("selectInfo----u","****数据库里面有相同的数据");
		} else {
			Log.i("selectInfo----u","****数据库里面不存在相同的数据");
			return null;
		}
		return u;
	}
	/**
	 * 添加一个用户
	 * @param 一个用户对象
	 */
	public void addUser(User u) {
		if (selectInfo(u.getUsername()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into user (username,password,timeOfContinuousUse,timeOfContinuousListen,channelId,iscommit) values(?,?,?,?,?)",
				new Object[] { u.getUsername(), u.getPassword(), u.getTimeOfContinuousUse(),u.getTimeOfContinuousListen(),
						u.getChannel_id(),u.getIsCommit() });
		db.close();

	}
	/**
	 * 添加一组用户信息
	 * @param list
	 */
	public void addUsers(List<User> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (User u : list) {
			if (selectInfo(u.getUsername()) != null) {
				Log.i("addUser---list",""+u.getUsername()+"数据库里面有相同的数据项");
				update(u);
				continue;
			}
			db.execSQL(
					"insert into user (username,password,timeOfContinuousUse,timeOfContinuousListen,channelId,iscommit) values(?,?,?,?,?,?)",
					new Object[] { u.getUsername(), u.getPassword(), u.getTimeOfContinuousListen(),u.getTimeOfContinuousListen(),
							 u.getChannel_id(),u.getIsCommit() });
			Log.i("添加一组用户，插入用户","*****插入");
		}
		db.close();
	}
		
	/**
	 * 根据用户名返回一个User对象，通过对象可以获取到该用户的所有信息
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from user where username=?",
				new String[] { username });
		User u = new User();
		if (c.moveToNext()) {
			u.setUsername(username);
			u.setPassword(c.getString(c.getColumnIndex("password")));
			u.setTimeOfContinuousUse(c.getInt(c.getColumnIndex("timeOfContinuousUse")));
			u.setTimeOfContinuousListen(c.getInt(c.getColumnIndex("timeOfContinuousListen")));
			u.setChannel_id(c.getString(c.getColumnIndex("channelId")));
			u.setIsCommit(c.getInt(c.getColumnIndex("isCommit")));
		}
		return u;
	}
	//更新所有user表
	public void updateUser(List<User> list) {
		if (list.size() > 0) {
			delete();
			addUsers(list);
		}
	}

	public List<User> getUser() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<User> list = new LinkedList<User>();
		Cursor c = db.rawQuery("select * from user", null);
		while (c.moveToNext()) {
			User u = new User();
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setPassword(c.getString(c.getColumnIndex("password")));
			u.setTimeOfContinuousUse(c.getInt(c.getColumnIndex("timeOfContinuousUse")));
			u.setTimeOfContinuousListen(c.getInt(c.getColumnIndex("timeOfContinuousListen")));
			u.setChannel_id(c.getString(c.getColumnIndex("channelId")));
			u.setIsCommit(c.getInt(c.getColumnIndex("isCommit")));
			list.add(u);
		}
		c.close();
		db.close();
		return list;
	}

	public void update(User u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update user set password=?,timeOfContinuousUse=?," +
				"timeOfContinuousListen=?,channelId=? ," +
				"iscommit=? where username=?",
				new Object[] { u.getPassword(), u.getTimeOfContinuousUse(),
						u.getTimeOfContinuousListen(),
						u.getChannel_id(),u.getIsCommit(),u.getUsername()});
		db.close();
	}

	public User getLastUser() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from user", null);
		User u = new User();
		while (c.moveToLast()) {

		}
		c.close();
		db.close();
		return u;
	}

	public void delUser(User u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from user where username=?",
				new Object[] { u.getUsername() });
		db.close();
	}

	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from user");
		db.close();
	}
	
	/**
	 * 根据用户名获得连续使用手机时长（护眼功能）
	 */
	public int getUserTimeOfContinuousUse(String username) {
		// TODO Auto-generated method stub
		int time=0;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from user where username=?",
				new String[] { username + "" });
		if(c.moveToFirst()){
			time=c.getInt(c.getColumnIndex("timeOfContinuousUse"));
			}
		return time;
	}
	
	/**
	 * 根据用户名,设置连续使用手机时长（护眼功能）
	 */
	public void setUserTimeOfContinuousUse(String username,int time) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update user set timeOfContinuousUse=? where username=?",new String[]{time+"",username});
	}
	
	/**
	 * 根据用户名获得持续听歌时长（护耳功能）
	 */
	public int getUserTimeOfContinuousListen(String username) {
		// TODO Auto-generated method stub
		int time=0;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from user where username=?",
				new String[] { username + "" });
		if(c.moveToFirst()){
			time=c.getInt(c.getColumnIndex("timeOfContinuousListen"));
			}
		return time;
	}	
	
	/**
	 * 根据用户名,设置持续听歌时长（护耳功能）
	 */
	public void setUserTimeOfContinuousListen(String username,int time) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update user set timeOfContinuousListen=? where username=?",new String[]{time+"",username});
	}		
	
	

	/**
	 * 判断是否该用户名的数据已经上传到服务器
	 */
	public int getUserIsCommit(String username) {
		// TODO Auto-generated method stub
		int flag=0;
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from user where username=?",
				new String[] { username + "" });
		if(c.moveToFirst()){
			flag=c.getInt(c.getColumnIndex("isCommit"));
		}
		return flag;
	}	
	
	/**
	 *设置是否该用户名的数据已经上传到服务器
	 */
	public void setUserIsCommit(String username,int isCommit ) {
		// TODO Auto-generated method stub
		int flag=0;
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("select * from user isCommit=? where username=?", new String[]{isCommit+"",username});
		 
	}	
}
