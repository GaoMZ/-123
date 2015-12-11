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


	//��Ѱ��������������ĵ�һ�м�¼
	public Track selectInfo(Track ut) {
		Track u = new Track();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from track where username=? and addTime=?",
			new String[] {ut.getUsername(),ut.getAddTime()});
		//�ж��Ƿ��ǵ�һ����¼
		if (c.moveToFirst()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setAddTime(c.getString(c.getColumnIndex("addTime")));
			Log.i("selectInfo----u","****���ݿ�����ͬ�����ݣ�selectInfo����ֵ��Ϊ��");			
		} else {
			Log.i("selectInfo----u","****���ݿ�û����ͬ�����ݣ�selectInfo����ֵΪ��");
			return null;
		}
		return u;
	}
	
	//����в���һ����¼
	public void addTrack(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		
		if (selectInfo(u) !=null) {
			//update(u);
			Log.i("addTrack---list",""+u.getUsername()+"track���ݿ���������ͬ��������");
		}
		else {
		db.execSQL(
				"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
				new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});
		}
		db.close();

	}
	
	//����в�����й켣����
	public void addTrack(List<Track> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (Track u : list) {
			if (selectInfo(u) !=null) {
				//update(u);
				Log.i("addTrack---list",""+u.getUsername()+"track���ݿ���������ͬ��������");
				continue;
			}else  {
				db.execSQL(
						"insert into track (username,longtitude,latitude,addTime) values(?,?,?,?)",
						new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getAddTime()});				
			}
		}
		db.close();
	}
	
/*	//�����û�����ѯ�����һ���켣����
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
	
	//���¶����켣����
	public void updateTrack(List<Track> list) {
		if (list.size() > 0) {
			delete();
			addTrack(list);
		}
	}
/*	//���������Ĺ켣����
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
	//���һ���û���ĳһ��Ĺ켣����
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

	//���ݲ������������ݱ��е�ĳһ��������Ϣ
	public void update(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update track set longtitude=?,latitude=?,addTime=? where username=?",
				new Object[] { u.getLongtitude(),u.getLatitude(),u.getAddTime(),u.getUsername()});
		db.close();
	}

	//��ȡ������һ������
	public Track getLastTrack() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from track", null);
		Track u = new Track();
		//c.moveToLast()   �ƶ������һ����¼
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

	//ɾ������ĳһ������
	public void delTrack(Track u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from track where username=?",
				new Object[] { u.getUsername() });
		db.close();
	}

	//ɾ��������
	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from track");
		db.close();
	}
	
}
