package edu.gricar.brezskrbnik;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class MenuNastavitve extends PreferenceActivity {
	public static final String TAG = "MenuNastavitve";
	SharedPreferences prefs;
	ApplicationBrezskrbnik app;
	public static final String PREF_SHRANI="SHRANI";
	public static final String PREF_IP = "IP";
	public static final String PREF_DEBUG_LOCATION = "PREF_DEBUG_LOCATION";
	public static boolean shrani=true;
	public static int frequency=10;
	public static String debug_location="localhost";
	public static String ip="192.168.1.65";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (ApplicationBrezskrbnik) this.getApplication();
		addPreferencesFromResource(R.xml.menu_nastavitve);
	}
	@Override
	public void onPause() {
		super.onPause();
		SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(app); 
		shrani = settings.getBoolean(PREF_SHRANI, true);
		debug_location = settings.getString(PREF_DEBUG_LOCATION, "localhost");
		ip = settings.getString(PREF_IP, "192.168.1.1");
		//app.setSettingsMenu(); //if something has been changed
	}
}