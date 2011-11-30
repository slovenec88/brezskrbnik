package edu.gricar.brezskrbnik.pomoc;

import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.R.layout;
import edu.gricar.brezskrbnik.budilka.AlarmActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class ActivityPomoc extends Activity{
	   @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.pomoc);

	    }
	   
	   public void onKnof(View v) {

			Intent i = new Intent(this.getApplicationContext(), ActivitySenzor.class);
	    	startActivity(i);

		}
	   
	   public void onKnofList(View v) {

			Intent i = new Intent(this.getApplicationContext(), List11.class);
	    	startActivity(i);

		}
	   
	   
}
