package edu.gricar.brezskrbnik.android;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;



import java.net.*;
import java.io.*;




public class Opomniki extends Activity {
	private static final String DEBUG_TAG = null;


	ApplicationBrezskrbnik app;


	private int id_l;
	private int id_s;
	private boolean sprememba;
	private String podatki;
	private long dbID;


	public long getDbID() {
		return dbID;
	}
	public void setDbID(long dbID) {
		this.dbID = dbID;
	}
	public int getId_l() {
		return id_l;
	}
	public void setId_l(int id_l) {
		this.id_l = id_l;
	}
	public int getId_s() {
		return id_s;
	}
	public void setId_s(int id_s) {
		this.id_s = id_s;
	}
	public boolean isSprememba() {
		return sprememba;
	}
	public void setSprememba(boolean sprememba) {
		this.sprememba = sprememba;
	}
	public String getPodatki() {
		return podatki;
	}
	public void setPodatki(String podatki) {
		this.podatki = podatki;
	}

	public void dodajVKoledar(){
    String calName; 
    String calId; 
	String[] projection = new String[] { "_id", "name" };
	Uri calendars = Uri.parse("content://calendar/calendars");
	     
	     
	//Cursor managedCursor = managedQuery(calendars, projection, "selected=1", null, null);
	
	   //String[] projection = new String[] { "_id", "name" };
       String selection = "selected=1";
       String path = "calendars";

       Cursor managedCursor = getCalendarManagedCursor(projection, selection,
               path);
	
	/*Cursor managedCursor =
	  managedQuery(calendars, projection, "selected=1", null, null);*/
	//http://www.developer.com/ws/article.php/3850276/Working-with-the-Android-Calendar.htm
	
	if (managedCursor.moveToFirst()) {
		
		 int nameColumn = managedCursor.getColumnIndex("name"); 
		 int idColumn = managedCursor.getColumnIndex("_id");
		 do {
		    calName = managedCursor.getString(nameColumn);
		    calId = managedCursor.getString(idColumn);
		    Toast toast =Toast.makeText(this, calName + " " + calId, Toast.LENGTH_SHORT);

			toast.show();
		 } while (managedCursor.moveToNext());
		}
	
	/*ContentValues event = new ContentValues();
	
	event.put("calendar_id", calId);
	event.put("title", "Event Title");
	event.put("description", "Event Desc");
	event.put("eventLocation", "Event Location");
	
	long startTime = 1000;
	long endTime = 2000;
	event.put("dtstart", startTime);
	event.put("dtend", endTime);*/

	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	   
	   

	}
	
	/*public class CalendarTest {

	    
	        CalendarService myService = new CalendarService("exampleCo-exampleApp-1.0");
	        myService.setUserCredentials("root@gmail.com", "pa$$word");
	            
	        URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
	        CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
	            
	        System.out.println("Your calendars:");
	        System.out.println();
	        
	        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
	          CalendarEntry entry = resultFeed.getEntries().get(i);
	          System.out.println("\t" + entry.getTitle().getPlainText());
	        //http://code.google.com/intl/sl-SI/apis/gdata/articles/java_client_lib.html#windows
	    
	    }
	}*/
	 /**
	    * @param projection
	    * @param selection
	    * @param path
	    * @return
	    */
	   private Cursor getCalendarManagedCursor(String[] projection,
	           String selection, String path) {
	       Uri calendars = Uri.parse("content://calendar/" + path);

	       Cursor managedCursor = null;
	       try {
	           managedCursor = managedQuery(calendars, projection, selection,
	                   null, null);
	       } catch (Exception e) {
	           Log.w(DEBUG_TAG, "Failed to get provider at ["
	                   + calendars.toString() + "]");
	       }

	       if (managedCursor == null) {
	           // try again
	           calendars = Uri.parse("content://com.android.calendar/" + path);
	           try {
	               managedCursor = managedQuery(calendars, projection, selection,
	                       null, null);
	           } catch (IllegalArgumentException e) {
	               Log.w(DEBUG_TAG, "Failed to get provider at ["
	                       + calendars.toString() + "]");
	           }
	       }
	       return managedCursor;
	   }

}



