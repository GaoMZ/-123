package com.njupt.kangaroo.db;

import java.util.LinkedList;
import java.util.List;

import com.njupt.kangaroo.dao.GeoFencing;
import com.njupt.kangaroo.dao.Relationship;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RelationshipDB {
	public DBOpenHelper helper;
	public RelationshipDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);
	}

/*	public List<String> getInfo(String columnsname) {
		List<String> list=new LinkedList<String>();
		list=null;
		Relationship u = new Relationship();		
		SQLiteDatabase db = helper.getReadableDatabase();
	//	Cursor c = db.query("relationship", new String[]{"id,childname,parentname"}, null, null, null, null, null);
		Cursor c = db.rawQuery("select * from relationship parentname=? where childname=? ",
				new String[] { "",columnsname+""  });
		while (c.moveToNext()) {
			String username=c.getString(c.getColumnIndexOrThrow(columnsname));
			list.add(username);
		}
		return list;
	}*/
	
/*	public String QueryChildname(String childname,String colname) {
		int 
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("relationship", new String[]{"id,childname,parentname"}, null, null, null, null, null);
		while (c.moveToNext()) {
			String username=c.getString(c.getColumnIndexOrThrow(colname));
			if(username.equals(childname)){
				return 
			}
		}
		return username;
	}*/
	
	public Relationship selectInfo(String childname) {
		Relationship u = new Relationship();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from relationship where childname=?  ",
			new String[] { childname});
		if (c.moveToFirst()) {
			u.setChildname(c.getString(c.getColumnIndex("childname")));
			u.setParentname(c.getString(c.getColumnIndex("parentname")));
		} else {
			Log.i("null","null****");
			return null;
		}
		return u;
	}
	
/*	public void addRelationship(String columnsname) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.query("relationship", new String[]{"id,childname,parentname"}, null, null, null, null, null);
		while (c.moveToNext()) {
			String username=c.getString(c.getColumnIndexOrThrow(columnsname));
			list.add(username);
		}		
		
		if (selectInfo(u.getChildname()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into user (childname,parentname) values(?,?)",
				new Object[] { u.getChildname(),u.getParentname() });
		db.close();

	}*/
	
	
	public void addRelationship(Relationship u) {
		if (selectInfo(u.getChildname()) != null) {
			update(u);
			return;
		}
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"insert into user (childname,parentname) values(?,?,?,?)",
				new Object[] { u.getChildname(),u.getParentname() });
		db.close();

	}
	
	
	public void addRelationship(List<Relationship> list) {
		SQLiteDatabase db = helper.getWritableDatabase();
		for (Relationship u : list) {
			db.execSQL(
					"insert into relationship (childname,parentname) values(?,?)",
					new Object[] { u.getChildname(),u.getParentname()});
		}
		db.close();
	}
	

	public Relationship getRelationship(String username) {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from relationship where childname=?",
				new String[] { username });
		Relationship u = new Relationship();
		if (c.moveToNext()) {
			u.setChildname(username);
			u.setParentname(c.getString(c.getColumnIndex("parentname")));
		}
		return u;
	}

	public void updateRelationship(List<Relationship> list) {
		if (list.size() > 0) {
			delete();
			addRelationship(list);
		}
	}

	public List<Relationship> getRelationship() {
		SQLiteDatabase db = helper.getWritableDatabase();
		List<Relationship> list = new LinkedList<Relationship>();
		Cursor c = db.rawQuery("select * from relationship", null);
		while (c.moveToNext()) {
			Relationship u = new Relationship();
			u.setChildname(c.getString(c.getColumnIndex("childname")));
			u.setParentname(c.getString(c.getColumnIndex("parentname")));
			list.add(u);
		}
		c.close();
		db.close();
		return list;
	}

	public void update(Relationship u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL(
				"update relationship set parentname=? where childname=?",
				new Object[] { u.getParentname(),u.getChildname()});
		db.close();
	}

	public Relationship getLastRelationship() {
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor c = db.rawQuery("select * from relationship", null);
		Relationship u = new Relationship();
		while (c.moveToLast()) {
			u.setChildname(c.getString(c.getColumnIndex("childname")));
			u.setParentname(c.getString(c.getColumnIndex("parentname")));
		}
		c.close();
		db.close();
		return u;
	}

	public void delRelationship(Relationship u) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from relationship where childname=?",
				new Object[] { u.getChildname() });
		db.close();
	}

	public void delete() {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from relationship");
		db.close();
	}
	

}
