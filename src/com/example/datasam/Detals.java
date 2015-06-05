package com.example.datasam;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Detals extends Activity
{

	ListView lv;
	Button b;
	MyDataHandler dbh;
    ArrayList<String> items;
    String count;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		dbh=new MyDataHandler(this);
		
		Intent k = getIntent();
	    count = k.getStringExtra("TOTAL");
		
		lv=(ListView)findViewById(R.id.listView1);
		b=(Button)findViewById(R.id.show);
	    items = dbh.show();
		lv.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,items));
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				
				Intent k = new Intent(Detals.this,Profiles.class);
				k.putExtra("NAME", items.get(pos));
				k.putExtra("TOTAL", count);
				startActivity(k);
				
			}
		});
		
		b.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dbh.deleteAll();
				Toast.makeText(getApplicationContext(), "Data Deleted", 5000).show();
				items = null;
				lv.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,items));
//				items = null;
//				lv.setAdapter(new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,items));
			}
		});
		
	}

}
