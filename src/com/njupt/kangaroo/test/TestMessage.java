package com.njupt.kangaroo.test;

import java.util.ArrayList;
import java.util.List;

import com.njupt.kangaroo.R;
import com.njupt.kangaroo.dao.GeoFencing;
import com.njupt.kangaroo.dao.UseTimeControl;
import com.njupt.kangaroo.db.DBOpenHelper;
import com.njupt.kangaroo.db.GeoFencingDB;
import com.njupt.kangaroo.db.UseTimeControlDB;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class TestMessage extends Activity{
	
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
		

			}
		});
		
		
		update_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
		
			}
		});
		delete_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			
			}
		});
		
		query_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
			
			}
		
		});
	}
}
