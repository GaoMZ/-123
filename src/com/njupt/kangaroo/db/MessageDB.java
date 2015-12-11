package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.njupt.kangaroo.dao.Message;
import com.njupt.kangaroo.dao.User;

public class MessageDB {

	public DBOpenHelper helper;
	public MessageDB(Context context) {
		helper = new DBOpenHelper(context);
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
}
