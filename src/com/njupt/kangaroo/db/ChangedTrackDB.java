package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.njupt.kangaroo.dao.Track;

public class ChangedTrackDB {

	public DBOpenHelper helper;
	public ChangedTrackDB(Context context) {
		helper = new DBOpenHelper(context);
	}

	public Track selectInfo(String addTime) {
		Track u = new Track();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from track where addTime=?",
			new String[] { addTime + "" });
		if (c.moveToFirst()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));
		} else {
			Log.i("u","****");
			return null;
		}
		return u;
	}
	
	public void addTrack(Track u) {
		if (selectInfo(u.getAddTime()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
				new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});
		db.close();

	}
	
	public void addTrack(List<Track> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (Track u : list) {
			db.execSQL(
					"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
					new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});
		}
		db.close();
	}
	

	public Track getTrack(String addTime) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from track where addTime=?",
				new String[] { addTime+"" });
		Track u = new Track();
		if (c.moveToNext()) {
			u.setUsername(addTime);
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));	
		}
		return u;
	}

	public void updateTrack(List<Track> list) {
		if (list.size() > 0) {
			delete();
			addTrack(list);
		}
	}

	public List<Track> getTrack() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Track> list = new LinkedList<Track>();
		Cursor c = db.rawQuery("select * from track", null);
		while (c.moveToNext()) {
			Track u = new Track();
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));
			list.add(u);
		}
		c.close();
		db.close();
		return list;
	}

	//根据参数，更新数据表中的某一行数据信息
	public void update(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update track set longtitude=?,latitude=?,addTime=? where username=?",
				new Object[] { u.getLongtitude(),u.getLatitude(),u.getAddTime(),u.getUsername()});
		db.close();
	}

	//获取表的最后一行数据
	public Track getLastTrack() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from track", null);
		Track u = new Track();
		//c.moveToLast()   移动到最后一条记录
		while (c.moveToLast()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));
		}
		c.close();
		db.close();
		return u;
	}

	//删除表中某一行数据
	public void delTrack(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from track where username=?",
				new Object[] { u.getUsername() });
		db.close();
	}

	//删除整个表
	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from track");
		db.close();
	}

}
