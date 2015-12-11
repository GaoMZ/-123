package com.njupt.kangaroo.db;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.njupt.kangaroo.dao.Track;
import com.njupt.kangaroo.dto.TrackDto;
public class TrackDB {

	public DBOpenHelper helper;
	public TrackDB(Context context) {
		helper = new DBOpenHelper(context);
	}


	//搜寻到满足参数条件的第一行记录
	public Track selectInfo(Track ut) {
		Track u = new Track();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from track where username=? and addTime=?",
			new String[] {ut.getUsername(),ut.getAddTime()});
		//判断是否是第一条记录
		if (c.moveToFirst()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));
			Log.i("selectInfo----u","****数据库有相同的数据，selectInfo返回值不为空");			
		} else {
			Log.i("selectInfo----u","****数据库没有相同的数据，selectInfo返回值为空");
			return null;
		}
		return u;
	}
	
	//向表中插入一条记录
	public void addTrack(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		
		if (selectInfo(u) !=null) {
			//update(u);
			Log.i("addTrack---list",""+u.getUsername()+"track数据库里面有相同的数据项");
		}
		else {
		db.execSQL(
				"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
				new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});
		}
		db.close();

	}
	
	//向表中插入多行轨迹数据
	public void addTrack(List<Track> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (Track u : list) {
			if (selectInfo(u) !=null) {
				//update(u);
				Log.i("addTrack---list",""+u.getUsername()+"track数据库里面有相同的数据项");
				continue;
			}else  {
				db.execSQL(
						"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
						new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});				
			}
		}
		db.close();
	}
	
/*	//根据用户名查询，获得一条轨迹数据
	public Track getTrack(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from track where username=?",
				new String[] { username });
		Track u = new Track();
		if (c.moveToNext()) {
			u.setUsername(username);
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));	
		}
		return u;
	}*/
	
	//更新多条轨迹数据
	public void updateTrack(List<Track> list) {
		if (list.size() > 0) {
			delete();
			addTrack(list);
		}
	}
/*	//获得整个表的轨迹数据
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
*/
	//获得一个用户在某一天的轨迹数据
	public List<Track> getTrack(String username,String addTime) {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Track> list = new LinkedList<Track>();
		Cursor c = db.rawQuery("select * from track where username=? and addTime=?",
				new String []{username,addTime});
		while (c.moveToNext()) {
			Track u = new Track();
		//	Date date=new Date();
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
