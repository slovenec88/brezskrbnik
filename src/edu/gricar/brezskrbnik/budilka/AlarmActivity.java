package edu.gricar.brezskrbnik.budilka;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;


import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import edu.gricar.brezskrbnik.budilka.MyAlarmService;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.KjeSemActivity;
import edu.gricar.brezskrbnik.R;
import org.ksoap2.*;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;



public class AlarmActivity extends Activity implements OnClickListener{
	static final int TIME_DIALOG_ID = 0;
	ApplicationBrezskrbnik app;
	private static final String SOAP_ACTION="http://tempuri.org/Floki";
    private static final String METHOD_NAME="Floki";
    private static final String NAMESPACE="http://tempuri.org/";
    private static final String URL="http://192.168.137.1/Service1.asmx";
    EditText et;
    TextView tv;
	TimePicker tp;
	int ura, minuta;
	
	  private PendingIntent mAlarmSender;
	   @Override
		protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.alarmll);
	        showDialog(TIME_DIALOG_ID);
	        
	        Button button = (Button)findViewById(R.id.btnAlarm);
	        Button button3 = (Button)findViewById(R.id.btnPovezi);
	       //tv = (TextView)findViewById(R.id.tvRezultat);
	       //et = (EditText)findViewById(R.id.editTextParam);
	       
	        button.setOnClickListener(this);
	        Button button2 = (Button)findViewById(R.id.btnAlarmStop);
	        button2.setOnClickListener(this);
	        button3.setOnClickListener(this);
	        tp = (TimePicker) findViewById(R.id.timePicker1);
	        tp.setIs24HourView(true);
	       mAlarmSender = PendingIntent.getService(AlarmActivity.this,
	                0, new Intent(AlarmActivity.this, MyAlarmService.class), 0);
	    }
	   
	   /*private TimePickerDialog.OnTimeSetListener mTimeSetListener =
		   new TimePickerDialog.OnTimeSetListener() {
		   public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		   Toast.makeText(AlarmActivity.this, "Time is="+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
		   ura = hourOfDay;
		   minuta = minute;
		   }};
		   
		   @Override
		   protected Dialog onCreateDialog(int id) {
		   switch (id) {
		   case TIME_DIALOG_ID:
		   return new TimePickerDialog(this,mTimeSetListener, 0, 0, false);
		   }
		   return null;
		   }*/

	
	public void onClickStart(View v) {
		
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
				int sek = (ura * 60 * 60) + minuta * 60;
				long firstTime = SystemClock.elapsedRealtime();

		           /*// Schedule the alarm!
		           AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		           am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
		                           firstTime, Integer.parseInt(et.getText().toString())*1000, mAlarmSender);
		           //Tu nastavi npr 1x na dan 24*60*60*1000 
		           // Tell the user about what we did.
		           */
		           // Single run
		            Calendar calendar = Calendar.getInstance();
		            calendar.setTimeInMillis(System.currentTimeMillis());
		            
		           
		            Date dt = new Date();
		            ura = tp.getCurrentHour();
		            minuta = tp.getCurrentMinute();
		            int trenutnaura = dt.getHours();
		            int trenutnaminuta = dt.getMinutes();
		            
		            int cajt = (trenutnaura * 60 * 60) + trenutnaminuta *60;
		            
		            sek = 86400 - cajt;
		            
		            int preracunan = (ura * 60 * 60) + minuta *60;
		            
		           // sek = cajt - preracunan;
		            
		            if (cajt < preracunan){
		            	sek = preracunan - cajt;
		            }
		            else
		            	sek = cajt - preracunan;
		            
		            calendar.add(Calendar.SECOND, sek);
		            
		            
		            

					Toast.makeText(AlarmActivity.this, "Budilka èez " + sek + "s.",
		                   Toast.LENGTH_LONG).show();
		                   
		            // Schedule the alarm!
		            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), mAlarmSender);
		            
		            
		            
		           
		           
				}
				if (v.getId() == R.id.btnAlarmStop) {
		            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
		            am.cancel(mAlarmSender);

		            // Tell the user about what we did.
		            Toast.makeText(AlarmActivity.this, "Stop repeating alarm",
		                    Toast.LENGTH_LONG).show();
					}
				
				if (v.getId() == R.id.btnPovezi) {
					String casIzspleta = GetData();
					
					 Toast.makeText(AlarmActivity.this, casIzspleta,
			                    Toast.LENGTH_LONG).show();
				
					 
					 String[] ura = casIzspleta.split(":");
					 
					 int iura = Integer.parseInt(ura[0]);
					 int iminuta = Integer.parseInt(ura[1]);
					 
					 tp.setCurrentHour(iura);
					 tp.setCurrentMinute(iminuta);
				}
			
		}
		
		
		
		private String GetData(){
	    	SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
	    	//Request.addProperty("Celsius", param);
	    	
	    	SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    	soapEnvelope.dotNet = true;
	    	soapEnvelope.setOutputSoapObject(Request);
	    	
	    	HttpTransportSE aht = new HttpTransportSE(URL);
	    	try{
	    		aht.call(SOAP_ACTION, soapEnvelope);
	    		SoapPrimitive result = (SoapPrimitive)soapEnvelope.getResponse();
	    		return result.toString();
	    	}
	    	catch(Exception e){
	    		
	    	e.printStackTrace();
	    	}
	    	
	    	
	    	return "NAPAKA";
	    }

	
}
