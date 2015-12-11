package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import com.njupt.kangaroo.dao.GeoFencing;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class GeoFencingDB {
	public DBOpenHelper helper;
	public GeoFencingDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);
	}

	public GeoFencing selectInfo(String username) {
		GeoFencing geoFencing = new GeoFencing();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from GeoFencing where username=?",
			new String[] { username + "" });
		if (c.moveToFirst()) {
			geoFencing.setUsername(c.getString(c.getColumnIndex("username")));
			geoFencing.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			geoFencing.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			geoFencing.setDistance(c.getDouble(c.getColumnIndex("distance")));
			Log.i("selectInfo-----u.getUsername",geoFencing.getUsername()+"数据库里面有相同的数据");
			Log.i("selectInfo-----u.getStart",geoFencing.getLongtitude()+"");		
			Log.i("selectInfo-----u.getEnd",geoFencing.getLatitude()+"");
			Log.i("selectInfo-----u.getEnd",geoFencing.getDistance()+"");			
		} else {
			Log.i("geoFencing","geoFencing****");
			Log.i("geoFencing---selectInfo-----u","****数据库里面无相同的数据");
			return null;
		}
		return geoFencing;
	}
	
	public void addGeoFencing(GeoFencing u) {
		if (selectInfo(u.getUsername()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into user (username,longtitude,latitude,distance) values(?,?,?,?)",
				new Object[] { u.getUsername(),u.getLongtitude(),u.getLatitude(),u.getDistance() });
		db.close();

	}
	
	public void addGeoFencing(List<GeoFencing> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (GeoFencing u : list) {
			if (selectInfo(u.getUsername()) != null) {
				Log.i("addGeoFencing---list",""+u.getUsername()+"GeoFencing数据库里面有相同的数据项");
				update(u);
				continue;
			}
			db.execSQL(
					"insert into GeoFencing (username,longtitude,latitude,distance) values(?,?,?,?)",
					new Object[] { u.getUsername(), u.getLongtitude(),u.getLatitude(),u.getDistance()});
		}
		db.close();
	}
	

	public GeoFencing getGeoFencing(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from GeoFencing where username=?",
				new String[] { username });
		GeoFencing u = new GeoFencing();
		if (c.moveToNext()) {
			u.setUsername(username);
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setDistance(c.getDouble(c.getColumnIndex("distance")));			
		}
		return u;
	}

	public void updateGeoFencing(List<GeoFencing> list) {
		if (list.size() > 0) {
			delete();
			addGeoFencing(list);
		}
	}

	public List<GeoFencing> getGeoFencing() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<GeoFencing> list = new LinkedList<GeoFencing>();
		Cursor c = db.rawQuery("select * from GeoFencing", null);
		while (c.moveToNext()) {
			GeoFencing u = new GeoFencing();
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setDistance(c.getDouble(c.getColumnIndex("distance")));
			list.add(u);
		}
		c.close();
		db.close();
		return list;
	}

	public void update(GeoFencing u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update GeoFencing set longtitude=?,latitude=?,distance =? where username=?",
				new Object[] { u.getLongtitude(),u.getLatitude(),u.getDistance(),u.getUsername()});
		db.close();
	}

	public GeoFencing getLastGeoFencing() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from GeoFencing", null);
		GeoFencing u = new GeoFencing();
		while (c.moveToLast()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setLongtitude(c.getDouble(c.getColumnIndex("longtitude")));
			u.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
			u.setDistance(c.getDouble(c.getColumnIndex("distance")));
		}
		c.close();
		db.close();
		return u;
	}

	public void delGeoFencing(GeoFencing u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from GeoFencing where username=?",
				new Object[] { u.getUsername() });
		db.close();
	}

	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from GeoFencing");
		db.close();
	}
	

}
