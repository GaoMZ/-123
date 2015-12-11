package com.njupt.kangaroo.test;

import java.util.ArrayList;
import java.util.List;

import com.njupt.kangaroo.R;
import com.njupt.kangaroo.dao.Track;
import com.njupt.kangaroo.dao.UseTimeControl;
import com.njupt.kangaroo.db.ChangedTrackDB;
import com.njupt.kangaroo.db.DBOpenHelper;
import com.njupt.kangaroo.db.TrackDB;
import com.njupt.kangaroo.db.UseTimeControlDB;
import com.njupt.kangaroo.utils.LoginingUseInfo;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TestTrack extends Activity{
	public LoginingUseInfo info;
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
		
		info=(LoginingUseInfo) getApplication();
		info.setUsernameLogin("jack");
		
		create.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//dbOpenHelper.getWritableDatabase();
				Intent intent=new Intent(TestTrack.this,TestUserActivity.class);
				startActivity(intent);
			}
		});
		
		add.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				TrackDB t=new TrackDB(getApplicationContext());
				List<Track> list=new ArrayList<Track>();
				Track track1=new Track("jack",118.9374060000,32.1215900000,"2015-12-09 12:05:00");
				Track track2=new Track("jack",118.9377710000,32.1049680000,"2015-12-09 12:10:00");	
				Track track3=new Track("jack",118.9244760000,32.1215900000,"2015-12-09 12:15:00");			
				Track track4=new Track("jack",118.9124390000,32.0910800000,"2015-12-09 12:20:00");	
				Track track5=new Track("jack",118.9377710000,32.1049680000,"2015-12-08 13:40:00");	
				list.add(track1);
				list.add(track2);
				list.add(track3);
				list.add(track4);	
				list.add(track5);					
				t.addTrack(list);
				
				Track track6=new Track("mary",118.9377710000,32.1049680000,"2015-12-07 13:00:00");	
				t.addTrack(track6);
			}
		});
		
		
		update_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				TrackDB t=new TrackDB(getApplicationContext());				
				Track track1=new Track("jack",14,14,"2015-6");	
				t.update(track1);
			}
		});
		delete_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				TrackDB t=new TrackDB(getApplicationContext());		
				Track track2=new Track("jack",12,14,"2015-6");	
				t.delete();
			}
		});
		
		query_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				TrackDB t=new TrackDB(getApplicationContext());		
				Track track2=new Track("jack",14,14,"2015-6");	
				Track track1=t.selectInfo(track2);	
				if(track2.equals(track1)){
					Toast.makeText(getApplicationContext(), track2.toString(), Toast.LENGTH_SHORT).show(); 
				}else {
					Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show(); 
				}
											
			}
		});
	}
}
