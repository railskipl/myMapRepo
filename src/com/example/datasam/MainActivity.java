package com.example.datasam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
	EditText name;
	EditText last;
	EditText city;
	EditText state;
	EditText mobile;
	
	Button submit;
	Button display;
	MyDataHandler dbh;
	
	String nm;
	String lst;
	String ct;
	String st;
	String mob;
	int counter=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		dbh = new MyDataHandler(this);
		
		name=(EditText)findViewById(R.id.edname);
		last=(EditText)findViewById(R.id.edlast);
		city=(EditText)findViewById(R.id.edcity);
		state=(EditText)findViewById(R.id.edstate);
		mobile=(EditText)findViewById(R.id.edmob);
		
		submit=(Button)findViewById(R.id.submit);
		display=(Button)findViewById(R.id.display);
		
		
		
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				nm=name.getText().toString();
				lst=last.getText().toString();
				ct=city.getText().toString();
				st=state.getText().toString();
				mob=mobile.getText().toString();
				
				dbh.insert(nm,lst,ct,st,mob);
				Toast.makeText(getApplicationContext(), "Data Submited", Toast.LENGTH_SHORT).show();
				
				name.setText("");
				last.setText("");
				city.setText("");
				state.setText("");
				mobile.setText("");
				
				counter++;
			}
		});
		
		display.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,Detals.class);
				i.putExtra("TOTAL", counter);
				startActivity(i);
			}
		});
		
	}

}
