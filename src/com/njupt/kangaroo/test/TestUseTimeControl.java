package com.njupt.kangaroo.test;

import java.util.ArrayList;
import java.util.List;

import com.njupt.kangaroo.R;
import com.njupt.kangaroo.dao.UseTimeControl;
import com.njupt.kangaroo.db.DBOpenHelper;
import com.njupt.kangaroo.db.UseTimeControlDB;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class TestUseTimeControl extends Activity{
	
	private Button create;
	private Button add;
	private Button update_data;
	private Button delete_data;
	private Button query_data;				
	private DBOpenHelper dbOpenHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
			UseTimeControlDB useTimeControlDB=new UseTimeControlDB(getApplicationContext());
			List<UseTimeControl> list=new ArrayList<UseTimeControl>();		
			UseTimeControl timeControl1=new UseTimeControl("jack","12:10:00","14:00:00");
			UseTimeControl timeControl2=new UseTimeControl("marry","13:00:00","19:20:00");			
			list.add(timeControl1);
			list.add(timeControl2);
			useTimeControlDB.addUseTimeControl(list);

			}
		});
		
		
		update_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				UseTimeControlDB useTimeControlDB=new UseTimeControlDB(getApplicationContext());
				UseTimeControl timeControl=new UseTimeControl("jack","10:00:00","13:40:00");
				useTimeControlDB.update(timeControl);
			}
		});
		delete_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				UseTimeControlDB useTimeControlDB=new UseTimeControlDB(getApplicationContext());
				UseTimeControl timeControl=new UseTimeControl("jack","10:00:00","20:20:00");
				UseTimeControl control=useTimeControlDB.selectInfo(timeControl.getUsername());
				if(timeControl.equals(control)){
					//useTimeControlDB.delUseTimeControl(timeControl);	
					useTimeControlDB.delete();
					Toast.makeText(getApplicationContext(), "É¾³ý³É¹¦", Toast.LENGTH_SHORT).show();					
			
				}else {
					Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		query_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
					UseTimeControlDB useTimeControlDB=new UseTimeControlDB(getApplicationContext());
					UseTimeControl control=useTimeControlDB.selectInfo("marry");					
					if(control.equals(null))
					{
						Toast.makeText(getApplicationContext(), control.toString(), Toast.LENGTH_SHORT).show();
					}else {
						Toast.makeText(getApplicationContext(), control.toString(), Toast.LENGTH_SHORT).show();
					}
			}
		
		});
	}
}
