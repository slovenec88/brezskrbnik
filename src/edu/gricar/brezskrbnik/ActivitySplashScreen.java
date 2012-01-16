package edu.gricar.brezskrbnik;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import edu.gricar.brezskrbnik.budilka.AlarmActivity;
import edu.gricar.brezskrbnik.budilka.AlarmActivity.BackgroundAsyncTask2;
import edu.gricar.brezskrbnik.pomoc.ActivityPomoc;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class ActivitySplashScreen extends Activity{
	//how long until we go to the next activity
    ApplicationBrezskrbnik app;
	protected int _splashTime = 2000; 

	private Thread splashTread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	    setContentView(R.layout.splash);
	    app = (ApplicationBrezskrbnik) getApplication();
	    setProgressBarIndeterminateVisibility(true);
	    final ActivitySplashScreen sPlashScreen = this; 
        BackgroundAsyncTask mt = new BackgroundAsyncTask();
        mt.execute();
	    // thread for displaying the SplashScreen
	    /*splashTread = new Thread() {
	        @Override
	        public void run() {
	            try {
	            	synchronized(this){

	            		//wait 5 sec
	            		wait(_splashTime);
	            	}

	            } catch(InterruptedException e) {}
	            finally {
	                finish();

	                //start a new activity
	                Intent i = new Intent();
	                i.setClass(sPlashScreen, ActivityBrezskrbnik.class);
	        		startActivity(i);

	                stop();
	            }
	        }
	    };

	    splashTread.start();
	}
	
	

	//Function that will handle the touch
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    if (event.getAction() == MotionEvent.ACTION_DOWN) {
	    	synchronized(splashTread){
	    		splashTread.notifyAll();
	    	}
	    }
	    return true;
	}*/
	}
	
	public class BackgroundAsyncTask extends AsyncTask<Void, Integer, String> {

	        @Override
	        protected String doInBackground(Void... params) {

                    try {
                        Thread.sleep(_splashTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
  

                
	            
                return null;
	        }

	        protected void onPreExecute() {
	            setProgressBarIndeterminateVisibility(true);
	        }

	        protected void onPostExecute(String arg) {
	            setProgressBarIndeterminateVisibility(false);
	            
	            Intent i = new Intent(getApplicationContext(), ActivityBrezskrbnik.class);
                startActivity(i);
                finish();
	           
	        }
	    }

	   
	    }
	
	

