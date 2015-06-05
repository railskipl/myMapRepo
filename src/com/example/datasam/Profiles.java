package com.example.datasam;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Profiles extends Activity
{

	TextView tv1,tv2,tv3;
	Button prev,nxt;
	MyDataHandler dbh;
	Cursor c;
	String fname = null;
	String last = null;
	String city = null;
	String state = null;
	String mobile = null;
	@SuppressWarnings("unused")
	private int count;
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prof);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		tv3 = (TextView)findViewById(R.id.textView3);
		
		prev = (Button)findViewById(R.id.prev);
		nxt = (Button)findViewById(R.id.next);
		dbh = new MyDataHandler(this);
		
		Intent i=getIntent();
		String name=i.getStringExtra("NAME");
		String total=i.getStringExtra("TOTAL");
		HashMap<String, String> sb = dbh.getAllDetails(name);
		count = sb.size();
		
		tv1.setText("Your Details"+"\n"
				   + "Name :"+ sb.get("NAME") + "\n"
				   + "Lastname :" + sb.get("LAST") + "\n" 
				   + "City :"+ sb.get("CITY") + "\n"
				   + "State :" +sb.get("STATE") + "\n" 
				   + "Mobile :" +sb.get("MOBILE"));
		
//		cursor.moveToFirst();
		HashMap<String, String> hm = dbh.getAllCotacts();
		tv2.setText("**Contacts**" + "\n" + "Name :" + hm.get("NAME"));
		
		tv3.setText("**Contacts**" + "\n" + "City :" + hm.get("CITY"));
		
		
		//code for Previous Button click
		prev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
	            {
					
				  int pos = c.getPosition();
				  if(pos >= 1)
				  {
					  if(c.moveToPrevious())
					  {
						fname=c.getString(c.getColumnIndex("name"));

						last=c.getString(c.getColumnIndex("last"));

						city=c.getString(c.getColumnIndex("place"));
						  
						//show on UI with cursor value
						tv2.setText("**Contacts**" + "\n" + "Name :" + fname);	
						tv3.setText("**Contacts**" + "\n" + "City :" + city);
						  
					   }
				  } 
				 
				  else
				  {
//					  c.moveToFirst();	
					  Toast.makeText(getApplicationContext(), "First Record", Toast.LENGTH_LONG).show();
				  }
		                   
	           }

	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
				
			}
		});
		
		//Code for Next Button click
		 c = dbh.nextRecord();
		nxt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try
	            {
					int pos = c.getPosition();
					if(pos < c.getCount()-1)
					{
					  if(c.moveToNext())
					  {
						fname=c.getString(c.getColumnIndex("name"));

						last=c.getString(c.getColumnIndex("last"));

						city=c.getString(c.getColumnIndex("place"));
						  
						//show on UI with cursor value
						tv2.setText("**Contacts**" + "\n" + "Name :" + fname);	
						tv3.setText("**Contacts**" + "\n" + "City :" + city);
						  
					   }
					}  
					else
				    {
//					  c.moveToFirst();	
					  Toast.makeText(getApplicationContext(), "Last Record", Toast.LENGTH_LONG).show();
				    }
		                   
	           }

	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
				
			}
		});
		
	}

}
                            