package com.njupt.kangaroo.test;

import java.util.LinkedList;
import java.util.List;

import com.njupt.kangaroo.R;
import com.njupt.kangaroo.dao.User;
import com.njupt.kangaroo.db.DBOpenHelper;
import com.njupt.kangaroo.db.UserDB;
import com.njupt.kangaroo.utils.LoginingUseInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestUserActivity extends Activity {

	private LoginingUseInfo info;
	private Button create;
	private Button add;
	private Button update_data;
	private Button delete_data;
	private Button query_data;				
	private DBOpenHelper dbOpenHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_sqlite);
		create=(Button) findViewById(R.id.create_database);
		add=(Button) findViewById(R.id.add_data);
		update_data=(Button) findViewById(R.id.update_data);
		delete_data=(Button) findViewById(R.id.delete_data);
		query_data=(Button) findViewById(R.id.query_data);	
		dbOpenHelper=new DBOpenHelper(this);
		
		create.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbOpenHelper.getWritableDatabase();
			}
		});
		
		add.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDB u=new UserDB(getApplicationContext());
			//	String username,String password,double timeOfContinuousUse ,
			//	double timeOfContinuousListen,int musicVolume,String channel_id
				info=(LoginingUseInfo) getApplication();
				
				
				UserDB db=new UserDB(getApplicationContext());
				List<User> list=new LinkedList<User>();
				User user =new User("ºó","0",23,10,"1306",1);
				User users =new User("xiao","0",24,160,"1347",0);		
				/*u.addUser(user);
				u.addUser(users);*/	
				list.add(users);
				list.add(user);
				db.addUsers(list);
				
			}
		});
		
		
		update_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDB u=new UserDB(getApplicationContext());
				User user=new User("jack","1",20,90,"1234",0);
				u.update(user);
				//u.setUserTimeOfContinuousUse(username, time)
				
			}
		});
		delete_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDB u=new UserDB(getApplicationContext());
				User user=new User("jack","1",20,90,"1234",0);
				//u.delUser(user);
				u.delete();
			}
		});
		
		query_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				UserDB u=new UserDB(getApplicationContext());
				User user=u.selectInfo("marry");
				//user.toString();
				Toast.makeText(getApplicationContext(), user.toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}
