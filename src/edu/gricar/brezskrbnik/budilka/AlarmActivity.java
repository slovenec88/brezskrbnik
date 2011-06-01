package edu.gricar.brezskrbnik.budilka;
import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;


import android.view.View;
import android.view.View.OnClickListener;

import edu.gricar.brezskrbnik.budilka.MyAlarmService;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.KjeSemActivity;
import edu.gricar.brezskrbnik.R;


public class AlarmActivity extends Activity implements OnClickListener{
	ApplicationBrezskrbnik app;
	EditText et;
	  private PendingIntent mAlarmSender;
	   @Override
		protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.alarmll);
	       
	        Button button = (Button)findViewById(R.id.btnAlarm);
	        et = (EditText) findViewById(R.id.editTextSekund);
	        button.setOnClickListener(this);
	        Button button2 = (Button)findViewById(R.id.btnAlarmStop);
	        button2.setOnClickListener(this);
	        
	       mAlarmSender = PendingIntent.getService(AlarmActivity.this,
	                0, new Intent(AlarmActivity.this, MyAlarmService.class), 0);
	    }

	
	public void onClickStart(View v) {
		
	       long firstTime = SystemClock.elapsedRealtime();

           // Schedule the alarm!
           AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
           am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                           firstTime, Integer.parseInt(et.getText().toString())*1000, mAlarmSender);
           //Tu nastavi npr 1x na dan 24*60*60*1000 
           // Tell the user about what we did.
           Toast.makeText(AlarmActivity.this, "Start alarm",
                   Toast.LENGTH_LONG).show();
           /* Single run
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 30);

            // Schedule the alarm!
            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mAlarmSender);
            * 
            * 
            */
           }
           
           
		
		public void onClickStop(View v){
            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
            am.cancel(mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(AlarmActivity.this, "Stop repeating alarm",
                    Toast.LENGTH_LONG).show();
			

       
		}


		public void onClick(View v) {
			if (v.getId() == R.id.btnAlarm) {
			       long firstTime = SystemClock.elapsedRealtime();

		           // Schedule the alarm!
		           AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		           am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		                           firstTime, Integer.parseInt(et.getText().toString())*1000, mAlarmSender);
		           //Tu nastavi npr 1x na dan 24*60*60*1000 
		           // Tell the user about what we did.
		           Toast.makeText(AlarmActivity.this, "Start alarm",
		                   Toast.LENGTH_LONG).show();
		           /* Single run
		            Calendar calendar = Calendar.getInstance();
		            calendar.setTimeInMillis(System.currentTimeMillis());
		            calendar.add(Calendar.SECOND, 30);

		            // Schedule the alarm!
		            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mAlarmSender);
		            * 
		            * 
		            */
		           
		           
				}
				if (v.getId() == R.id.btnAlarmStop) {
		            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.cancel(mAlarmSender);

		            // Tell the user about what we did.
		            Toast.makeText(AlarmActivity.this, "Stop repeating alarm",
		                    Toast.LENGTH_LONG).show();
					}
			
		}


	
}
