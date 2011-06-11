package edu.gricar.brezskrbnik;

import android.app.Activity;
import android.os.Bundle;



public class ActivityO extends Activity {
	ApplicationBrezskrbnik app;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oaplikaciji); 
    }
	
	
}
