package edu.gricar.brezskrbnik.koledar;

/*
* Copyright (c) 2010, Lauren Darcey and Shane Conder
* All rights reserved.
* 
* Redistribution and use in source and binary forms, with or without modification, are 
* permitted provided that the following conditions are met:
* 
* * Redistributions of source code must retain the above copyright notice, this list of 
*   conditions and the following disclaimer.
*   
* * Redistributions in binary form must reproduce the above copyright notice, this list 
*   of conditions and the following disclaimer in the documentation and/or other 
*   materials provided with the distribution.
*   
* * Neither the name of the <ORGANIZATION> nor the names of its contributors may be used
*   to endorse or promote products derived from this software without specific prior 
*   written permission.
*   
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
* EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES 
* OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
* SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, 
* INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
* TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR 
* BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
* STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
* THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
* <ORGANIZATION> = Mamlambo
*/

/*
* ******* WARNING WARNING WARNING ************
* 
* As stated above, this code is supplied AS-IS. Any damage, loss of data, or other harm 
* is not our liability. You use this code at your own risk.
* 
* You've been warned. 
* 
* Since this code has to be run on a handset, you may break your handset. We have not tested it on *your* handset.
* 
* You've been warned.
* 
* This code is subject to change. In fact, it has changed. Android SDK 2.1 -> 2.2 update changed it.
* 
* Please see the article at http://bit.ly/c2kYWk for more information. 
* 
*/


import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.R.id;
import edu.gricar.brezskrbnik.R.layout;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class CalendarActivity extends Activity {

   private static final String DEBUG_TAG = "CalendarActivity";
   TextView info;
   TextView miki;
   @Override
   public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.opomniki2);
       //ListAllCalendarEntries(2);
       info = (TextView) this.findViewById(R.id.textViewInfo);
       miki = (TextView) this.findViewById(R.id.tvKoledar);
       miki.setMovementMethod(new ScrollingMovementMethod());
       try {
           Log.i(DEBUG_TAG, "Starting Calendar Test");

            //ListAllCalendarDetails();
            ListAllCalendarEntries(5);
           // will return the last found calendar with "Test" in the name
           int iTestCalendarID = ListSelectedCalendars();

           // change this when you know which calendar you want to use
           // If you create a new calendar, you may need to manually sync the
           // phone first
           if (iTestCalendarID != 0) {

               Uri newEvent2 = MakeNewCalendarEntry2(iTestCalendarID);
               int eventID2 = Integer.parseInt(newEvent2.getLastPathSegment());
               ListCalendarEntry(eventID2);

               Uri newEvent = MakeNewCalendarEntry(iTestCalendarID);
               int eventID = Integer.parseInt(newEvent.getLastPathSegment());
               //ListCalendarEntry(eventID);
               
               // uncomment these to show updating and deleting entries

               //UpdateCalendarEntry(eventID);
               //ListCalendarEntrySummary(eventID);
               //DeleteCalendarEntry(eventID);
               
               //ListCalendarEntrySummary(eventID);
               //ListAllCalendarEntries(9);
           } else {
               Log.i(DEBUG_TAG, "No 'Test' calendar found.");
           }

           Log.i(DEBUG_TAG, "Ending Calendar Test");


       } catch (Exception e) {
           Log.e(DEBUG_TAG, "General failure", e);
       }
   }

   private int ListSelectedCalendars() {
       int result = 0;
       String[] projection = new String[] { "_id", "name" };
       String selection = "selected=1";
       String path = "calendars";

       Cursor managedCursor = getCalendarManagedCursor(projection, selection,
               path);

       if (managedCursor != null && managedCursor.moveToFirst()) {

           Log.i(DEBUG_TAG, "Listing Selected Calendars Only");

           int nameColumn = managedCursor.getColumnIndex("name");
           int idColumn = managedCursor.getColumnIndex("_id");

           do {
               String calName = managedCursor.getString(nameColumn);
               String calId = managedCursor.getString(idColumn);
               info.setText(""+ "Found Calendar '" + calName + "' (ID="
                       + calId + ")");
               Log.i(DEBUG_TAG, "Found Calendar '" + calName + "' (ID="
                       + calId + ")");
               if (calName != null && calName.contains("Test")) {
                   result = Integer.parseInt(calId);
               }
           } while (managedCursor.moveToNext());
       } else {
           Log.i(DEBUG_TAG, "No Calendars");
       }

       return result;

   }

   private void ListAllCalendarDetails() {
       Cursor managedCursor = getCalendarManagedCursor(null, null, "calendars");

       if (managedCursor != null && managedCursor.moveToFirst()) {

           Log.i(DEBUG_TAG, "Listing Calendars with Details");

           do {

               Log.i(DEBUG_TAG, "**START Calendar Description**");

               for (int i = 0; i < managedCursor.getColumnCount(); i++) {
                   Log.i(DEBUG_TAG, managedCursor.getColumnName(i) + "="
                           + managedCursor.getString(i));
               }
               Log.i(DEBUG_TAG, "**END Calendar Description**");
           } while (managedCursor.moveToNext());
       } else {
           Log.i(DEBUG_TAG, "No Calendars");
       }

   }

   private void ListAllCalendarEntries(int calID) {
	   int riki = 0;
       Cursor managedCursor = getCalendarManagedCursor(null, "calendar_id="
               + calID, "events");

       if (managedCursor != null && managedCursor.moveToFirst()) {

           Log.i(DEBUG_TAG, "Listing Calendar Event Details");

           do {

               Log.i(DEBUG_TAG, "**START Calendar Event Description**");

               for (int i = 0; i < managedCursor.getColumnCount(); i++) {
                   Log.i(DEBUG_TAG, managedCursor.getColumnName(i) + "="
                           + managedCursor.getString(i));
                   if (managedCursor.getColumnName(i).equalsIgnoreCase("title"))
                	   miki.setText(miki.getText() + "\n" + managedCursor.getString(i));
                   riki++;
                   
                   
               }
               Log.i(DEBUG_TAG, "**END Calendar Event Description**");
           } while (managedCursor.moveToNext());
       } else {
           Log.i(DEBUG_TAG, "No Calendars");
       }

   }

   private void ListCalendarEntry(int eventId) {
       Cursor managedCursor = getCalendarManagedCursor(null, null, "events/" + eventId);
   
       if (managedCursor != null && managedCursor.moveToFirst()) {

           Log.i(DEBUG_TAG, "Listing Calendar Event Details");
           int n = 0;
           do {
        	   
               Log.i(DEBUG_TAG, "**START Calendar Event Description**");

               for (int i = 0; i < managedCursor.getColumnCount(); i++) {
                   Log.i(DEBUG_TAG, managedCursor.getColumnName(i) + "="
                           + managedCursor.getString(i));
               }
               Log.i(DEBUG_TAG, "**END Calendar Event Description**");
           } while (managedCursor.moveToNext());
       } else {
           Log.i(DEBUG_TAG, "No Calendar Entry");
       }

   }

   private void ListCalendarEntrySummary(int eventId) {
       String[] projection = new String[] { "_id", "title", "dtstart" };
       Cursor managedCursor = getCalendarManagedCursor(projection,
               null, "events/" + eventId);

       if (managedCursor != null && managedCursor.moveToFirst()) {

           Log.i(DEBUG_TAG, "Listing Calendar Event Details");

           do {

               Log.i(DEBUG_TAG, "**START Calendar Event Description**");

               for (int i = 0; i < managedCursor.getColumnCount(); i++) {
                   Log.i(DEBUG_TAG, managedCursor.getColumnName(i) + "="
                           + managedCursor.getString(i));
               }
               Log.i(DEBUG_TAG, "**END Calendar Event Description**");
           } while (managedCursor.moveToNext());
       } else {
           Log.i(DEBUG_TAG, "No Calendar Entry");
       }

   }

   private Uri MakeNewCalendarEntry(int calId) {
       ContentValues event = new ContentValues();

       event.put("calendar_id", calId);
       event.put("title", "Today's Event [TEST]");
       event.put("description", "2 Hour Presentation");
       event.put("eventLocation", "Online");

       long startTime = System.currentTimeMillis() + 1000 * 60 * 60;
       long endTime = System.currentTimeMillis() + 1000 * 60 * 60 * 2;

       event.put("dtstart", startTime);
       event.put("dtend", endTime);

       event.put("allDay", 0); // 0 for false, 1 for true
       event.put("eventStatus", 1);
       event.put("visibility", 0);
       event.put("transparency", 0);
       event.put("hasAlarm", 0); // 0 for false, 1 for true

       Uri eventsUri = Uri.parse(getCalendarUriBase()+"events");

       Uri insertedUri = getContentResolver().insert(eventsUri, event);
       return insertedUri;
   }

   private Uri MakeNewCalendarEntry2(int calId) {
       ContentValues event = new ContentValues();

       event.put("calendar_id", calId);
       event.put("title", "Birthday [TEST]");
       event.put("description", "All Day Event");
       event.put("eventLocation", "Worldwide");

       long startTime = System.currentTimeMillis() + 1000 * 60 * 60 * 24;

       event.put("dtstart", startTime);
       event.put("dtend", startTime);

       event.put("allDay", 1); // 0 for false, 1 for true
       event.put("eventStatus", 1);
       event.put("visibility", 0);
       event.put("transparency", 0);
       event.put("hasAlarm", 0); // 0 for false, 1 for true

       Uri eventsUri = Uri.parse(getCalendarUriBase()+"events");
       Uri insertedUri = getContentResolver().insert(eventsUri, event);
       return insertedUri;

   }

   private int UpdateCalendarEntry(int entryID) {
       int iNumRowsUpdated = 0;

       ContentValues event = new ContentValues();

       event.put("title", "Changed Event Title");
       event.put("hasAlarm", 1); // 0 for false, 1 for true

       Uri eventsUri = Uri.parse(getCalendarUriBase()+"events");
       Uri eventUri = ContentUris.withAppendedId(eventsUri, entryID);

       iNumRowsUpdated = getContentResolver().update(eventUri, event, null,
               null);

       Log.i(DEBUG_TAG, "Updated " + iNumRowsUpdated + " calendar entry.");

       return iNumRowsUpdated;
   }

   private int DeleteCalendarEntry(int entryID) {
       int iNumRowsDeleted = 0;

       Uri eventsUri = Uri.parse(getCalendarUriBase()+"events");
       Uri eventUri = ContentUris.withAppendedId(eventsUri, entryID);
       iNumRowsDeleted = getContentResolver().delete(eventUri, null, null);

       Log.i(DEBUG_TAG, "Deleted " + iNumRowsDeleted + " calendar entry.");

       return iNumRowsDeleted;
   }

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
       } catch (IllegalArgumentException e) {
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

   /*
    * Determines if it's a pre 2.1 or a 2.2 calendar Uri, and returns the Uri
    */
   private String getCalendarUriBase() {
       String calendarUriBase = null;
       Uri calendars = Uri.parse("content://calendar/calendars");
       Cursor managedCursor = null;
       try {
           managedCursor = managedQuery(calendars, null, null, null, null);
       } catch (Exception e) {
           // eat
       }

       if (managedCursor != null) {
           calendarUriBase = "content://calendar/";
       } else {
           calendars = Uri.parse("content://com.android.calendar/calendars");
           try {
               managedCursor = managedQuery(calendars, null, null, null, null);
           } catch (Exception e) {
               // eat
           }

           if (managedCursor != null) {
               calendarUriBase = "content://com.android.calendar/";
           }

       }

       return calendarUriBase;
   }

}
