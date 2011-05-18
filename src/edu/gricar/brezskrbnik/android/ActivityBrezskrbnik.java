package edu.gricar.brezskrbnik.android;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ActivityBrezskrbnik extends Activity {
	ApplicationBrezskrbnik app;
    private static final int TEST_LIST_ACTIVITY_ID = 0;
	/** Called when the activity is first created. */
	
	
	ProgressBar progressBar;
    Button buttonStartProgress;
    Button button31;
	
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
        
        buttonStartProgress = (Button)findViewById(R.id.startprogress);
        progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
        progressBar.setProgress(0);
     
        buttonStartProgress.setOnClickListener(new Button.OnClickListener(){

      @Override
      public void onClick(View v) {
       // TODO Auto-generated method stub
       new BackgroundAsyncTask().execute();
       buttonStartProgress.setClickable(false);
      }});
   
        
    }
    
    
    public void button1(View v) {
    	Intent moj2=new Intent(this,KjeSemActivity.class);
		this.startActivity(moj2);
       }
    
    
    
}