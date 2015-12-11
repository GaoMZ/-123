package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.njupt.kangaroo.dao.UseTimeControl;
import com.njupt.kangaroo.dao.User;

public class UseTimeControlDB {

	public DBOpenHelper helper;
	public UseTimeControlDB(Context context) {
		helper = new DBOpenHelper(context);
	}
	
	public UseTimeControl selectInfo(String username) {
		UseTimeControl u = new UseTimeControl();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from usetimeControl where username=?",
			new String[] { username + "" });
		if (c.moveToFirst()) {
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setStart(c.getInt(c.getColumnIndex("start")));
			u.setEnd(c.getInt(c.getColumnIndex("end")));
			Log.i("selectInfo-----u.getUsername",u.getUsername()+"数据库里面有相同的数据");
			Log.i("selectInfo-----u.getStart",u.getStart()+"");		
			Log.i("selectInfo-----u.getEnd",u.getEnd()+"");				
		} else {
			Log.i("selectInfo-----u","****数据库里面无相同的数据");
			return null;
		}
		return u;
	}
	
	public void addUseTimeControl(UseTimeControl u) {
		if (selectInfo(u.getUsername()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into usetimeControl (username,start,end) values(?,?,?)",
				new Object[] { u.getUsername(), u.getStart(),u.getEnd()});
		db.close();

	}
	
	public void addUseTimeControl(List<UseTimeControl> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (UseTimeControl u : list) {
			if (selectInfo(u.getUsername()) != null) {
				update(u);
				Log.i("addUserControl---list",""+u.getUsername()+"数据库里面有相同的数据项");
				continue;
			}
			db.execSQL(
					"insert into usetimeControl (username,start,end) values(?,?,?)",
					new Object[] {  u.getUsername(), u.getStart(),u.getEnd() });
		}
		db.close();
	}
	

	public UseTimeControl getUseTimeControl(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from usetimeControl where username=?",
				new String[] { username });
		UseTimeControl u = new UseTimeControl();
		if (c.moveToNext()) {
			u.setUsername(username);
			u.setStart(c.getInt(c.getColumnIndex("start")));
			u.setEnd(c.getInt(c.getColumnIndex("end")));
		}
		return u;
	}

	public void updateUseTimeControl(List<UseTimeControl> list) {
		if (list.size() > 0) {
			delete();
			addUseTimeControl(list);
		}
	}

	public List<UseTimeControl> getUseTimeControl() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<UseTimeControl> list = new LinkedList<UseTimeControl>();
		Cursor c = db.rawQuery("select * from usetimeControl", null);
		while (c.moveToNext()) {
			UseTimeControl u = new UseTimeControl();
			u.setUsername(c.getString(c.getColumnIndex("username")));
			u.setStart(c.getInt(c.getColumnIndex("start")));
			u.setEnd(c.getInt(c.getColumnIndex("end")));
			list.add(u);
		}
		c.close();
		db.close();
		return list;
	}

	public void update(UseTimeControl u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update usetimeControl set start=?,end=? where username=?",
				new Object[] { u.getStart(),u.getEnd(),u.getUsername()});
		Log.i("update----u.getUsername",u.getUsername());
		Log.i("u.getStart",u.getStart()+"");		
		Log.i("u.getEnd",u.getEnd()+"");
		db.close();
	}

	public UseTimeControl getLastUseTimeControl() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from usetimeControl", null);
		UseTimeControl u = new UseTimeControl();
		while (c.moveToLast()) {

		}
		c.close();
		db.close();
		return u;
	}

	public void delUseTimeControl(UseTimeControl u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from usetimeControl where username=?",
				new Object[] { u.getUsername() });
		db.close();
	}

	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from usetimeControl");
		db.close();
	}
}
