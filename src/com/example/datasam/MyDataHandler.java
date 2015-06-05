package com.example.datasam;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHandler extends SQLiteOpenHelper
{
	
	public static final String DATABASE_NAME = "MyDBName.db";
	   public static final String CONTACTS_TABLE_NAME = "contacts";
	   public static final String CONTACTS_COLUMN_ID = "id";
	   public static final String CONTACTS_COLUMN_NAME = "name";
	   public static final String CONTACTS_COLUMN_LAST = "last";
	   public static final String CONTACTS_COLUMN_STATE = "state";
	   public static final String CONTACTS_COLUMN_CITY = "place";
	   public static final String CONTACTS_COLUMN_MOBILE = "phone";
	   
	   @SuppressWarnings({ "rawtypes", "unused" })
	private HashMap hp;
	   int flag;

	   public MyDataHandler(Context context)
	   {
	      super(context, DATABASE_NAME , null, 1);
	      flag = 0;
	   }

	   @Override
	   public void onCreate(SQLiteDatabase db) {
	      // TODO Auto-generated method stub
	      db.execSQL(
	      "create table contacts " +
	      "(id integer primary key, name text,phone text,last text, state text,place text)"
	      );
	   }

	   @Override
	   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	      // TODO Auto-generated method stub
	      db.execSQL("DROP TABLE IF EXISTS contacts");
	      onCreate(db);
	   }

	
	public void insert(String nm,String lst,String ct,String state,String mob)
	{
		SQLiteDatabase db = getWritableDatabase(); 
		
		ContentValues cv = new ContentValues();
		cv.put(CONTACTS_COLUMN_NAME, nm);
		cv.put(CONTACTS_COLUMN_LAST, lst);
		cv.put(CONTACTS_COLUMN_STATE, state);
		cv.put(CONTACTS_COLUMN_CITY, ct);
		cv.put(CONTACTS_COLUMN_MOBILE, mob);
		
		db.insert(CONTACTS_TABLE_NAME, null, cv);
		
		db.close();
	}
	
	public ArrayList<String> show()
	{
		SQLiteDatabase db = getReadableDatabase();
		String name = null;
		ArrayList<String> al = new ArrayList<String>();
		Cursor c = db.rawQuery("SELECT "+CONTACTS_COLUMN_NAME+" FROM " + CONTACTS_TABLE_NAME, null);
		if(c.moveToFirst())
		{
		  do
		 {
			 name = c.getString(c.getColumnIndex(CONTACTS_COLUMN_NAME));
			 al.add(name);
		 }while(c.moveToNext());
		
		}
		
		db.close();
		return al;
		
	}
	
	public HashMap<String, String> getAllDetails(String nm)
	{
		SQLiteDatabase db = getReadableDatabase();
		HashMap<String, String> hm = new HashMap<String, String>();
		String name = null;
		String last = null;
		String city = null;
		String state = null;
		String mobile = null;
		Cursor c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME +" WHERE "+CONTACTS_COLUMN_NAME+"=?", new String[] {nm});
		if(c.moveToFirst())
		{
			 c.moveToFirst();
			 name = c.getString(c.getColumnIndex(CONTACTS_COLUMN_NAME));
			 last = c.getString(c.getColumnIndex(CONTACTS_COLUMN_LAST));
			 city = c.getString(c.getColumnIndex(CONTACTS_COLUMN_CITY));
			 state = c.getString(c.getColumnIndex(CONTACTS_COLUMN_STATE));
			 mobile =c.getString(c.getColumnIndex(CONTACTS_COLUMN_MOBILE)); 
			 
			 hm.put("NAME", name);
			 hm.put("LAST", last);
			 hm.put("CITY", city);
			 hm.put("STATE", state);
			 hm.put("MOBILE", mobile);
			 c.moveToNext();		
		}
		
		db.close();
		return hm;
	}
	
	public void deleteAll()
	{
		SQLiteDatabase db = getWritableDatabase();
		
		db.execSQL("DELETE FROM " + CONTACTS_TABLE_NAME);
//		db.delete(CONTACTS_TABLE_NAME, null, null);
	}
	
	public HashMap<String, String> getAllCotacts()
	   {
		SQLiteDatabase db = getReadableDatabase();
		HashMap<String, String> hm = new HashMap<String, String>();
		String name = null;
		String last = null;
		String city = null;
		String state = null;
		String mobile = null;
		Cursor c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);
		
		if(c.moveToFirst())
		{
			 c.moveToFirst();
			 name = c.getString(c.getColumnIndex(CONTACTS_COLUMN_NAME));
			 last = c.getString(c.getColumnIndex(CONTACTS_COLUMN_LAST));
			 city = c.getString(c.getColumnIndex(CONTACTS_COLUMN_CITY));
			 state = c.getString(c.getColumnIndex(CONTACTS_COLUMN_STATE));
			 mobile =c.getString(c.getColumnIndex(CONTACTS_COLUMN_MOBILE)); 
			 
			 hm.put("NAME", name);
			 hm.put("LAST", last);
			 hm.put("CITY", city);
			 hm.put("STATE", state);
			 hm.put("MOBILE", mobile);
					
			 c.moveToNext();
		}
		db.close();
		return hm;
	   }
	
	public Cursor nextRecord()
	{
		Cursor c = null;
		SQLiteDatabase db = getReadableDatabase();
 
		c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);
		c.moveToFirst();
		
		db.close();
		return c;
	}
	
	public Cursor prevRecord()
	{

		Cursor c = null;
		SQLiteDatabase db = getReadableDatabase();
 
		c = db.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME, null);
		c.moveToLast();
	
		db.close();
		return c;
		
	}
}
