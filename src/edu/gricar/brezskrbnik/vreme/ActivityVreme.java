package edu.gricar.brezskrbnik.vreme;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.gricar.brezskrbnik.ActivityBrezskrbnik;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.budilka.AlarmActivity;
import edu.gricar.brezskrbnik.budilka.AlarmActivity.BackgroundAsyncTask;
import edu.gricar.brezskrbnik.krizciKrozci.MainActivity;
import edu.gricar.brezskrbnik.nastavitve.MenuNastavitve;
import edu.gricar.brezskrbnik.pomoc.ActivityO;

public class ActivityVreme extends Activity{
    ApplicationBrezskrbnik app;
    public static String kraj;
    Menu nMenu;
    ImageView ivslika1, ivslika2, ivslika3, ivslika4;
    ImageView[] slikar;
   
    TextView tvDanDanes, tvTempDanes;
    TextView tvDanDanesPlus1, tvTempDanesPlus1;
    TextView tvDanDanesPlus2, tvTempDanesPlus2;
    TextView tvDanDanesPlus3, tvTempDanesPlus3;
    TextView tvVremeKraj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vreme);
        app = (ApplicationBrezskrbnik) getApplication();
        
        tvVremeKraj = (TextView) findViewById(R.id.tv_kraj);
        tvVremeKraj.setText("Kraj: " + kraj);
        ivslika1 = (ImageView) findViewById(R.id.vreme_slika_danes);
        ivslika2 = (ImageView) findViewById(R.id.vreme_slika_danesPlus1);
        ivslika3 = (ImageView) findViewById(R.id.vreme_slika_danesPlus2);
        ivslika4 = (ImageView) findViewById(R.id.vreme_slika_danesPlus3);
          
        slikar = new ImageView[4];
        slikar[0] = ivslika1;
        slikar[1] = ivslika2;
        slikar[2] = ivslika3;
        slikar[3] = ivslika4;
        
        tvDanDanes = (TextView) findViewById(R.id.vreme_tekst_dan_danes);
        tvTempDanes = (TextView) findViewById(R.id.vreme_tekst_real_danes);
        
        tvDanDanesPlus1 = (TextView) findViewById(R.id.vreme_tekst_dan_danesP1);
        tvTempDanesPlus1 = (TextView) findViewById(R.id.vreme_tekst_real_danesP1);
        
        tvDanDanesPlus2 = (TextView) findViewById(R.id.vreme_tekst_dan_danesP2);
        tvTempDanesPlus2 = (TextView) findViewById(R.id.vreme_tekst_real_danesP2);
        
        tvDanDanesPlus3 = (TextView) findViewById(R.id.vreme_tekst_dan_danesP3);
        tvTempDanesPlus3 = (TextView) findViewById(R.id.vreme_tekst_real_danesP3);

    }
    
    
    
    public void nafilajPodatke(){
        
        tvDanDanesPlus2.setText(app.vreme[2].getDatum());
        tvTempDanesPlus2.setText(app.vreme[2].getRealfeel());
        
        tvDanDanesPlus3.setText(app.vreme[3].getDatum());
        tvTempDanesPlus3.setText(app.vreme[3].getRealfeel());
        
        tvDanDanes.setText(app.vreme[0].getDatum());
        tvTempDanes.setText(app.vreme[0].getRealfeel());
        
        tvDanDanesPlus1.setText(app.vreme[1].getDatum());
        tvTempDanesPlus1.setText(app.vreme[1].getRealfeel());
        
       
        
        for (int i = 0; i < 4; i++){
            String slika = app.vreme[i].getSlika();

            if (slika.equalsIgnoreCase("1_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_1));
            }
            if (slika.equalsIgnoreCase("2_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_2));
            }
            if (slika.equalsIgnoreCase("3_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_3));
            }
            if (slika.equalsIgnoreCase("4_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_4));
            }
            if (slika.equalsIgnoreCase("5_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_5));
            }
            if (slika.equalsIgnoreCase("6_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_6));
            }
            if (slika.equalsIgnoreCase("7_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_7));
            }
            if (slika.equalsIgnoreCase("8_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_8));
            }
            if (slika.equalsIgnoreCase("11_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_11));
            }
            if (slika.equalsIgnoreCase("12_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_12));
            }
            if (slika.equalsIgnoreCase("13_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_13));
            }
            if (slika.equalsIgnoreCase("14_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_14));
            }
            if (slika.equalsIgnoreCase("15_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_15));
            }
            if (slika.equalsIgnoreCase("16_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_16));
            }
            if (slika.equalsIgnoreCase("17_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_17));
            }
            if (slika.equalsIgnoreCase("18_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_18));
            }
            if (slika.equalsIgnoreCase("19_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_19));
            }
            if (slika.equalsIgnoreCase("20_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_20));
            }
            if (slika.equalsIgnoreCase("21_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_21));
            }
            if (slika.equalsIgnoreCase("22_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_22));
            }
            if (slika.equalsIgnoreCase("23_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_23));
            }
            if (slika.equalsIgnoreCase("24_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_24));
            }
            if (slika.equalsIgnoreCase("25_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_25));
            }
            if (slika.equalsIgnoreCase("26_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_26));
            }
            if (slika.equalsIgnoreCase("29_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_29));
            }
            if (slika.equalsIgnoreCase("30_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_30));
            }
            if (slika.equalsIgnoreCase("31_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_31));
            }
            if (slika.equalsIgnoreCase("32_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_32));
            }
            if (slika.equalsIgnoreCase("33_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_33));
            }
            if (slika.equalsIgnoreCase("34_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_34));
            }
            if (slika.equalsIgnoreCase("35_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_35));
            }
            if (slika.equalsIgnoreCase("36_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_36));
            }
            if (slika.equalsIgnoreCase("37_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_37));
            }
            if (slika.equalsIgnoreCase("38_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_38));
            }
            if (slika.equalsIgnoreCase("39_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_39));
            }
            if (slika.equalsIgnoreCase("40_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_40));
            }
            if (slika.equalsIgnoreCase("41_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_41));
            }
            if (slika.equalsIgnoreCase("42_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_42));
            }
            if (slika.equalsIgnoreCase("43_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_43));
            }
            if (slika.equalsIgnoreCase("40_int.jpg")){
                slikar[i].setImageDrawable(getResources().getDrawable(R.drawable.weather_44));
            }       
       
        }

    }

     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        nMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nastavitve_vreme, nMenu);
        return true;
        
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.OsveziPodatke:
            try {
                BackgroundAsyncTask mt = new BackgroundAsyncTask();
                mt.execute(app);
                
                
                
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
    
    public class BackgroundAsyncTask extends AsyncTask<ApplicationBrezskrbnik, Integer, String> {
        ProgressDialog dialogWait;
        
        
        @Override
        protected String doInBackground(ApplicationBrezskrbnik ... params) {
            
            
            dialogWait.setCancelable(true);
            new AccuParser(params[0]);
            
            dialogWait.dismiss();
            
            return "";
        }
        @Override
        protected void onPreExecute() {
            dialogWait = ProgressDialog.show(ActivityVreme.this, "Osvežujem", "Poèakajte prosim..", true);
        }
        
        protected void onPostExecute(String arg) {
            try {
                nafilajPodatke();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Toast.makeText(ActivityVreme.this, "Napaka v komunikaciji ali neobstojeè kraj!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        

    
    }
}
