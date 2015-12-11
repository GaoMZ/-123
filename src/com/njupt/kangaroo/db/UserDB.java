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
	 * @param username�û���
	 * @return  �����ݿ��в��Ҹ��û���������ڸ��û����򷵻ظ��û�������Ϣ,����Ϊnull
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
			Log.i("selectInfo----u","****���ݿ���������ͬ������");
		} else {
			Log.i("selectInfo----u","****���ݿ����治������ͬ������");
			return null;
		}
		return u;
	}
	/**
	 * ���һ���û�
	 * @param һ���û�����
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
	 * ���һ���û���Ϣ
	 * @param list
	 */
	public void addUsers(List<User> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (User u : list) {
			if (selectInfo(u.getUsername()) != null) {
				Log.i("addUser---list",""+u.getUsername()+"���ݿ���������ͬ��������");
				update(u);
				continue;
			}
			db.execSQL(
					"insert into user (username,password,timeOfContinuousUse,timeOfContinuousListen,channelId,iscommit) values(?,?,?,?,?,?)",
					new Object[] { u.getUsername(), u.getPassword(), u.getTimeOfContinuousListen(),u.getTimeOfContinuousListen(),
							 u.getChannel_id(),u.getIsCommit() });
			Log.i("���һ���û��������û�","*****����");
		}
		db.close();
	}
		
	/**
	 * �����û�������һ��User����ͨ��������Ի�ȡ�����û���������Ϣ
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
	//��������user��
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
	 * �����û����������ʹ���ֻ�ʱ�������۹��ܣ�
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
	 * �����û���,��������ʹ���ֻ�ʱ�������۹��ܣ�
	 */
	public void setUserTimeOfContinuousUse(String username,int time) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update user set timeOfContinuousUse=? where username=?",new String[]{time+"",username});
	}
	
	/**
	 * �����û�����ó�������ʱ�����������ܣ�
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
	 * �����û���,���ó�������ʱ�����������ܣ�
	 */
	public void setUserTimeOfContinuousListen(String username,int time) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update user set timeOfContinuousListen=? where username=?",new String[]{time+"",username});
	}		
	
	

	/**
	 * �ж��Ƿ���û����������Ѿ��ϴ���������
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
	 *�����Ƿ���û����������Ѿ��ϴ���������
	 */
	public void setUserIsCommit(String username,int isCommit ) {
		// TODO Auto-generated method stub
		int flag=0;
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("select * from user isCommit=? where username=?", new String[]{isCommit+"",username});
		 
	}	
}
