package com.njupt.kangaroo.db;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class DBOpenHelper extends SQLiteOpenHelper{
	
	private static  String name="stitp.db";
	private static final String CREATE_USER="CREATE table  user"+
			"("+
			"id integer primary key,"+
			"username text," +
			"password text,"+
			"timeOfContinuousUse real," + 
			"timeOfContinuousListen integer," +
			"channelId  text,"+
			"isCommit integer default 0"+
			")";
	
	private static final String CREATE_USETIMECONTROL="CREATE table  usetimeControl"+
			"( "+
			"id integer primary key ," +
			"username text," +
			"start text,"+
			"end text," +
			"isCommit integer default 0"+
			")";
	
	private static final String CREATE_GEOFENCING="create table GeoFencing"+/*"create table IF NOT EXISTS GeoFencing"*/ 
			"( "+
			"username text  primary key," +
			"longtitude rear,"+
			"latitude rear," +
			"distance rear," +
			"isCommit integer default 0"+
			")";
	
	private static final String CREATE_RELATIONSHIP="create table  relationship( "+	/*IF NOT EXISTS*/
			"id integer primary key ,"+
			"childname text," +
			"parentname text, "+
			"isCommit integer default 0"+
			")";
	
	private static final String CREATE_TRACK="create table  track( "+/*"IF NOT EXISTS"*/
			"id integer primary key,"+			
			"username text,"+
			"longtitude rear,"+
			"latitude rear," +
			"addTime text,"+
			"dateString text,"+
			"isCommit integer default 0"+
			")";
	
	
	private static final String CREATE_MESSAGE="create table IF NOT EXISTS message( "+
			"id integer primary key,"+
			"sender text,"+
			"receiver text,"+
			"message text ," +
			"date text "+
			")";
	
	public static final String CREATE_APP = "create table  app("
			+ "username text, " + "appusetime integer, " + "appname text,"
			+ "addtime text ," +"iscommit integer, "+ "primary key(username,appname,addtime))";
	
	private Context mContext;
	public static int version=1;
	
	public DBOpenHelper(Context context) {
		super(context, name, null, version);
		// TODO Auto-generated constructor stub
		mContext=context;
	}

	
	/*
	 *  首次创建数据库的时候调用,完成对数据库的表的创建，可以添加列等表格样式
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//db.execSQL("create table person(id integer primary key autoincrement,name varchar(64),address varchar(64))");
		db.execSQL(CREATE_USER);
		Toast.makeText(mContext, "Create user succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_USETIMECONTROL);
		Toast.makeText(mContext, "Create usetimeControl  succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_GEOFENCING);
		Toast.makeText(mContext, "Create GeoFencing succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_RELATIONSHIP);
		Toast.makeText(mContext, "Create relationship succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_TRACK);
		Toast.makeText(mContext, "Create track succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_MESSAGE);
		Toast.makeText(mContext, "Create message succeeded", Toast.LENGTH_SHORT).show();
		db.execSQL(CREATE_APP);		
		Toast.makeText(mContext, "Create app succeeded", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists user");
		db.execSQL("drop table if exists usetimeControl");
		db.execSQL("drop table if exists GeoFencing");
		db.execSQL("drop table if exists relationship");	
		db.execSQL("drop table if exists track");			
		db.execSQL("drop table if exists message");					
		db.execSQL("drop table if exists app");	
	//	db.execSQL("ALTER TABLE user ADD COLUMN other TEXT");
		}

	public void saveMessage(SQLiteDatabase db, String username,
			List<String> applicationName) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateString = format.format(date);
		for (String name : applicationName) {
			String insert = "insert or ignore into app(username,appname,addtime,appusetime) values(?,?,?,?)";
			String update = "update app set appusetime = appusetime+10 where username=? and appname=? and addtime=?";
			db.execSQL(insert,new String []{username,name,dateString,new Integer(0).toString()});
			db.execSQL(update,new String[]{username,name,dateString});
		}
	}
}
