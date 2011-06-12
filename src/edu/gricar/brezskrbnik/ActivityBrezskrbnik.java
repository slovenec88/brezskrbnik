package edu.gricar.brezskrbnik;

import java.util.List;

import com.google.android.maps.Overlay;

import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.budilka.AlarmActivity;
import edu.gricar.brezskrbnik.koledar.CalendarActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
//shared pref - nastavitve za budilko.. ip, dolžina vibriranja, zvok
//shared pref navigacija, google navigation on off?

//celoten kolendar - prikaz in vnašanje novih koledarjev
public class ActivityBrezskrbnik extends Activity implements OnClickListener{
	ApplicationBrezskrbnik app;
	Menu nMenu;
    private static final int TEST_LIST_ACTIVITY_ID = 0;
    public static final String PREF_NAME="PREF_TELEFONSKA";
    
	private static final int EXIT_DIALOG = 0;
	private static final String PREF_SHRANI = null;
	private static final String PREF_DEBUG_LOCATION = null;
	private static final String PREF_IP = "IP";
	private static final String PREF_DOMACI = "DOMACI";
	private static final String PREF_TELEFONSKA = "TELEFONSKA";
	public static final String PREF_NASTAVITVE = "nastavitve";
	/** Called when the activity is first created. */
	
	static public String destination = "040597224";
	ProgressBar progressBar;
    Button buttonStartProgress;
    Button button31;
    Button sosko;
    
	
    public class BackgroundAsyncTask extends
    AsyncTask<Void, Integer, Void> {
  
  int myProgress;

  @Override
  protected void onPostExecute(Void result) {
   // TODO Auto-generated method stub
   Toast.makeText(ActivityBrezskrbnik.this,
         "konec", Toast.LENGTH_LONG).show();
   buttonStartProgress.setClickable(true);
  }

  @Override
  protected void onPreExecute() {
   // TODO Auto-generated method stub
   Toast.makeText(ActivityBrezskrbnik.this,
         "zaèetek", Toast.LENGTH_LONG).show();
   myProgress = 0;
  }

  @Override
  protected Void doInBackground(Void... params) {
   // TODO Auto-generated method stub
   while(myProgress<100){
    myProgress++;
    publishProgress(myProgress);
       SystemClock.sleep(100);
   }
   return null;
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
   // TODO Auto-generated method stub
   progressBar.setProgress(values[0]);
  }

 }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sosko = (Button) findViewById(R.id.button1);
       
        
        //sosko.getBackground().setColorFilter(0x42FFFFFF, PorterDuff.Mode.MULTIPLY);
        ///uttonStartProgress = (Button)findViewById(R.id.startprogress);
        //progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
        //progressBar.setProgress(0);
     
       // buttonStartProgress.setOnClickListener(new Button.OnClickListener(){
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	SharedPreferences settings = getSharedPreferences("brezskrbnik",0);
    	
    	MenuNastavitve.ip = settings.getString(PREF_IP, "Ne najdem");
    	MenuNastavitve.domaci = settings.getString(PREF_DOMACI, "Ne najdem");
    	MenuNastavitve.telefonska = settings.getString(PREF_TELEFONSKA, "Ne najdem");
    	
    	nastaviIPstreznika();
    	nastaviKraj();
    	nastaviTel();
    
    }
    
    public void nastaviIPstreznika(){
     AlarmActivity.ip = MenuNastavitve.ip;
    	
    }
    
    public void nastaviKraj(){
    	KjeSemActivity.dom = MenuNastavitve.domaci;
    }
    
    public void nastaviTel(){
    	
    	destination = MenuNastavitve.telefonska;
    }
    
    public void onNavigacija(View v) {
    	

		
		Intent i = new Intent(this.getApplicationContext(), KjeSemActivity.class);
    	startActivity(i);
		
		
				
				

				Toast toast =Toast.makeText(this, "narii", Toast.LENGTH_LONG); 
				toast.show();
				
				

	}
    
    
    public void onBudilka(View v) {
    	Toast toast =Toast.makeText(this, "Budilka", Toast.LENGTH_LONG);

		toast.show();
		Intent i = new Intent(this.getApplicationContext(), AlarmActivity.class);
    	startActivity(i);

	}
    
    public void onPomoc(View v) {
    	Toast toast =Toast.makeText(this, "Pomoè", Toast.LENGTH_LONG);

		toast.show();
		Intent i = new Intent(this.getApplicationContext(), ActivityPomoc.class);
    	startActivity(i);
	}
    
    public void onOpomniki(View v) {
    	Toast toast =Toast.makeText(this, "Opomniki", Toast.LENGTH_LONG);

		toast.show();
//CalendarActivity
		Intent i = new Intent(this.getApplicationContext(), CalendarActivity.class);
    	startActivity(i);
    
    	    	/*
    	 * Intent i = new Intent(this.getApplicationContext(), Opomniki.class);
    	startActivity(i);
    
    	Opomniki a = new Opomniki();
    	a.dodajVKoledar();
    	 */
	}
    
    
    
    public void OnSOS(View v){
    	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {
    	        switch (which){
    	        case DialogInterface.BUTTON_POSITIVE:
    	        	
    	        	
    	        	LocationManager locationManager;
    	    		String context = Context.LOCATION_SERVICE;
    	    		locationManager = (LocationManager)getSystemService(context);
    	    		Criteria criteria = new Criteria();
    	    		criteria.setAccuracy(Criteria.ACCURACY_FINE);
    	    		criteria.setAltitudeRequired(false);
    	    		criteria.setBearingRequired(false);
    	    		criteria.setCostAllowed(true);
    	    		criteria.setPowerRequirement(Criteria.POWER_LOW);
    	    		String provider = locationManager.getBestProvider(criteria, true);

    	    		Location location = locationManager.getLastKnownLocation(provider);
    	    		
    	
    	        	SmsManager m = SmsManager.getDefault();
    	        	/*String text = "(TEST) Prosim za pomoè! Moja lokacija je: " + "Širina: "+ location.getLatitude() 
    	        		+ " Dolžina:" + location.getLongitude() +
    	        			" http://maps.google.com/maps?q="+ location.getLatitude() + "," + location.getLongitude() + " [brezskrbnik]";*/
    	        	
    	        	String text = "http://maps.google.com/maps?q="+ location.getLatitude() + "," + location.getLongitude();
    	        	m.sendTextMessage(destination, null, "TEST! Prosim za pomoè na lokaciji: ", null, null);
    	        	m.sendTextMessage(destination, null, text, null, null);
    	        	
    	        	
    	        	
    	        	Toast toast = Toast.makeText(ActivityBrezskrbnik.this, "Sporoèilo JE poslano!", Toast.LENGTH_LONG);
    	    		toast.show();
    	            break;

    	        case DialogInterface.BUTTON_NEGATIVE:
    	        	Toast toast2 = Toast.makeText(ActivityBrezskrbnik.this, "Sporoèilo NI poslano!", Toast.LENGTH_LONG);
    	    		toast2.show();
    	            break;
    	        }
    	    }
    	};

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("ALI RES ŽELIŠ POSLATI SOS NA " + destination + "?").setPositiveButton("DA", dialogClickListener)
    	    .setNegativeButton("NE", dialogClickListener).show();
    }
    
    
    
    /*public void button1(View v) {
    	Intent moj2=new Intent(this,KjeSemActivity.class);
		this.startActivity(moj2);
       }*/
    
  //menu nastavitve
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		switch (id) {
		case EXIT_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Konèama za danes?")
			.setCancelable(false)
			.setPositiveButton("Da", new DialogInterface.OnClickListener() {

				
				public void onClick(DialogInterface dialog, int id) {

					ActivityBrezskrbnik.this.setResult(RESULT_CANCELED);
					finish();
				}

			})
			.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					ActivityBrezskrbnik.this.setResult(RESULT_OK);
					dialog.cancel();
				}
			});
			return builder.create();
			
		}
		return null;
	}

	public void izhod(View v) {
		showDialog(EXIT_DIALOG);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		nMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.nastavitve_menu, nMenu);
		return true;
		
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogTest:
			showDialog(EXIT_DIALOG);
			return true;
		case R.id.itemSettings:
			Intent i = new Intent();
			i.setClass(this, MenuNastavitve.class);
			startActivityForResult(i, R.id.itemSettings);
			return true;
		case R.id.itemOprog:
			Intent moj2=new Intent(this,ActivityO.class); 
			this.startActivity(moj2);
			return true;

		default:// Generic catch all for all the other menu resources
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			}
			break;
		}

		return false;
	}



	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
    
}