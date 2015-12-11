package com.njupt.kangaroo.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.njupt.kangaroo.R;
import com.njupt.kangaroo.dao.GeoFencing;
import com.njupt.kangaroo.dao.Relationship;
import com.njupt.kangaroo.dao.UseTimeControl;
import com.njupt.kangaroo.db.ChangedRelationshipDB;
import com.njupt.kangaroo.db.DBOpenHelper;
import com.njupt.kangaroo.db.GeoFencingDB;
import com.njupt.kangaroo.db.RelationshipDB;
import com.njupt.kangaroo.db.UseTimeControlDB;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class TestRelationship extends Activity{
	
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
/*				RelationshipDB db=new RelationshipDB(getApplicationContext());
				List<Relationship> list=new LinkedList<Relationship>() ;
				Relationship relationship1=new Relationship("jack","1");
				list.add(relationship1);
				relationship1=new Relationship("anna","1");
				list.add(relationship1);
				relationship1=new Relationship("1","bob");		
				list.add(relationship1);
				relationship1=new Relationship("1","jim");	
				list.add(relationship1);				
				db.addRelationship(list);*/
				
		/*		List<String> list2=new ArrayList<String>();
				list2=db.getInfo("childname");
				Log.i("childnamelist", list2.toString());*/
				
				ChangedRelationshipDB db=new ChangedRelationshipDB(getApplicationContext());
				Relationship sRelationship=new Relationship("jack","username");
				
			}
		});
		
		
		update_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				RelationshipDB db=new RelationshipDB(getApplicationContext());
				Relationship relationship1=new Relationship("joe","1");
				db.update(relationship1);

			}
		});
		delete_data.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				RelationshipDB db=new RelationshipDB(getApplicationContext());
				Relationship relationship1=new Relationship("jack","1");				
				db.delRelationship(relationship1);		
				db.delete();
			}
		});
		
		query_data.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				RelationshipDB db=new RelationshipDB(getApplicationContext());
				 Relationship relationship1=new Relationship("anna","1");	
/*				Relationship relationship=new Relationship();
				db.selectInfo(relationship1.getChildname());
				if(relationship1.equals(relationship))
				{
				Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();					
				}*/
				 
				 ChangedRelationshipDB s=new ChangedRelationshipDB(getApplicationContext());
				 boolean flag=s.IsHasRelationship(relationship1.getChildname(), relationship1.getParentname());
				 Toast.makeText(getApplicationContext(), ""+flag, Toast.LENGTH_SHORT).show();
			}
				 
		});
	}
}
