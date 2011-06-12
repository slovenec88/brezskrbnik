package edu.gricar.brezskrbnik.nastavitve;

import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.R.xml;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class MenuNastavitve extends PreferenceActivity {
	public static final String TAG = "MenuNastavitve";
	ApplicationBrezskrbnik app;
	public static final String PREF_SHRANI="SHRANI";
	public static final String PREF_IP = "IP";
	public static final String PREF_DEBUG_LOCATION = "PREF_DEBUG_LOCATION";
	public static final String PREF_DOMACI = "DOMACI";
	public static final String PREF_TELEFONSKA = "TELEFONSKA";
	public static final String PREF_NASTAVITVE = "brezskrbnik";
	public static boolean shrani=true;
	public static int frequency=10;
	public static String debug_location="localhost";
	public static String ip="192.168.1.65";
	public static String domaci="dobletinska+ulica+5";
	public static String telefonska = "040597224";
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
		SharedPreferences bla = getSharedPreferences("brezskrbnik", 0);
		
		shrani = settings.getBoolean(PREF_SHRANI, true);
		debug_location = settings.getString(PREF_DEBUG_LOCATION, "localhost");
		ip = settings.getString(PREF_IP, "192.168.1.1");
		domaci = settings.getString(PREF_DOMACI, "dobletinska+ulica+5");
		telefonska = settings.getString(PREF_TELEFONSKA, "040597224");
		
		SharedPreferences.Editor editor = bla.edit();
		editor.putString(PREF_TELEFONSKA, settings.getString(PREF_TELEFONSKA, "040597224"));
		editor.putString(PREF_DOMACI, domaci);
		editor.putString(PREF_IP, ip);
		editor.commit();
		
		//app.setSettingsMenu(); //if something has been changed
	}
}