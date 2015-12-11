package com.njupt.kangaroo.db;

import com.njupt.kangaroo.dao.Relationship;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ChangedRelationshipDB {

	public DBOpenHelper helper;
	public ChangedRelationshipDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new DBOpenHelper(context);
	}
	public boolean IsHasRelationship(String childname,String parentname){
		boolean flag=false;
		Relationship u = new Relationship(childname,parentname);
		Relationship relationship = new Relationship();		
		SQLiteDatabase db = helper.getReadableDatabase();		
		Cursor c = db.rawQuery("select * from relationship where childname=? and parentname ",
				new String[] { childname,parentname});
		if(c.moveToFirst()){
			relationship.setChildname(c.getString(c.getColumnIndex("childname")));
			relationship.setParentname(c.getString(c.getColumnIndex("parentname")));
			if((u.getChildname()).equals(relationship.getChildname())&&(u.getParentname()).equals((u.getParentname())))
				flag=true;
		}
		return flag;
	}
	
	private void AddRelationship( Relationship u ) {
		if(IsHasRelationship(u.getChildname(), u.getParentname())){
			Log.i("是否该关系存在","***yes");
		}else  {
			SQLiteDatabase db = helper.getWritableDatabase();
			db.execSQL(
					"insert into relationship (childname,parentname) values(?,?)",
					new Object[] { u.getChildname(),u.getParentname() });
			db.close();
		}
	}
/*	public Relationship selectInfo(String username) {
		Relationship u = new Relationship();		
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from relationship where username=?",
			new String[] { username + "" });
		if (c.moveToFirst()) {
			u.setChildname(c.getString(c.getColumnIndex("username")));
			u.setParentname(c.getString(c.getColumnIndex("parentname")));
		} else {
			Log.i("geoFencing","geoFencing****");
			return null;
		}
		return u;
	}*/

}
