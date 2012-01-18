package edu.gricar.brezskrbnik.vreme;

import java.text.DateFormat;
import java.util.Date;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;

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
    TextView tvVremeKraj, tvDatumSinhro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.vreme);
        app = (ApplicationBrezskrbnik) getApplication();

        tvVremeKraj = (TextView) findViewById(R.id.tv_kraj);
        tvDatumSinhro = (TextView) findViewById(R.id.vreme_tekst_datumsinhronizacije);

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

        try {
            ivslika1.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    String url = "http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/details1.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ivslika2.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    String url = "http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/details2.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ivslika3.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    String url = "http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/details3.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ivslika4.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    String url = "http://www.accuweather.com/en-us/si/" + kraj + "/" + kraj + "/details4.aspx";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() 
    {
        super.onPause();
        try {
            shraniPodatke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        try {
            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
            app.vreme = new Vreme[]{new Vreme(), new Vreme(), new Vreme(), new Vreme()};
            app.vreme[0].setSlika(preferences.getString("ivslika1", ""));
            app.vreme[1].setSlika(preferences.getString("ivslika2", ""));
            app.vreme[2].setSlika(preferences.getString("ivslika3", ""));
            app.vreme[3].setSlika(preferences.getString("ivslika4", ""));

            tvDanDanes.setText(preferences.getString("tvDanDanes", ""));
            tvTempDanes.setText(preferences.getString("tvTempDanes", ""));
            tvDanDanesPlus1.setText(preferences.getString("tvDanDanesPlus1", ""));
            tvTempDanesPlus1.setText(preferences.getString("tvTempDanesPlus1", ""));
            tvDanDanesPlus2.setText(preferences.getString("tvDanDanesPlus2", ""));
            tvTempDanesPlus2.setText(preferences.getString("tvTempDanesPlus2", ""));
            tvDanDanesPlus3.setText(preferences.getString("tvDanDanesPlus3", ""));
            tvTempDanesPlus3.setText(preferences.getString("tvTempDanesPlus3", ""));
            tvVremeKraj.setText(preferences.getString("tvVremeKraj", ""));

            nafilajSlike();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (tvDanDanes.getText().toString().equalsIgnoreCase("")){
            BackgroundAsyncTask mt = new BackgroundAsyncTask();
            mt.execute(app);
        }
    }

    public void shraniPodatke(){
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String ivslika1S = app.vreme[0].getSlika();
        String ivslika2S = app.vreme[1].getSlika();
        String ivslika3S = app.vreme[2].getSlika();
        String ivslika4S = app.vreme[3].getSlika();

        String tvDanDanesS = tvDanDanes.getText().toString();
        String tvTempDanesS = tvTempDanes.getText().toString();
        String tvDanDanesPlus1S = tvDanDanesPlus1.getText().toString();
        String tvTempDanesPlus1S = tvTempDanesPlus1.getText().toString();
        String tvDanDanesPlus2S = tvDanDanesPlus2.getText().toString();
        String tvTempDanesPlus2S = tvTempDanesPlus2.getText().toString();
        String tvDanDanesPlus3S = tvDanDanesPlus3.getText().toString();
        String tvTempDanesPlus3S = tvTempDanesPlus3.getText().toString();
        String tvVremeKrajS = tvVremeKraj.getText().toString();

        editor.putString("ivslika1", ivslika1S);
        editor.putString("ivslika2", ivslika2S);
        editor.putString("ivslika3", ivslika3S);
        editor.putString("ivslika4", ivslika4S);
        editor.putString("tvDanDanes", tvDanDanesS);
        editor.putString("tvTempDanes", tvTempDanesS);
        editor.putString("tvDanDanesPlus1", tvDanDanesPlus1S);
        editor.putString("tvTempDanesPlus1", tvTempDanesPlus1S);
        editor.putString("tvDanDanesPlus2", tvDanDanesPlus2S);
        editor.putString("tvTempDanesPlus2", tvTempDanesPlus2S);
        editor.putString("tvDanDanesPlus3", tvDanDanesPlus3S);
        editor.putString("tvTempDanesPlus3", tvTempDanesPlus3S);
        editor.putString("tvVremeKraj", tvVremeKrajS);

        editor.commit();
    }

    public void nafilajTekst(){
        tvVremeKraj.setText("Kraj: " + kraj);
        tvDanDanesPlus2.setText(app.vreme[2].getDatum());
        tvTempDanesPlus2.setText(app.vreme[2].getRealfeel());

        tvDanDanesPlus3.setText(app.vreme[3].getDatum());
        tvTempDanesPlus3.setText(app.vreme[3].getRealfeel());

        tvDanDanes.setText(app.vreme[0].getDatum());
        tvTempDanes.setText(app.vreme[0].getRealfeel());

        tvDanDanesPlus1.setText(app.vreme[1].getDatum());
        tvTempDanesPlus1.setText(app.vreme[1].getRealfeel());
        tvDatumSinhro.setText("Posodobljeno!");
        shraniPodatke();
    }
    

    public void nafilajSlike(){
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
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nastavitve_vreme, nMenu);
        MenuItem menuitem = menu.findItem(R.id.OsveziPodatke);
        menuitem.setTitle(preferences.getString("Cas", ""));
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
            new AccuParser(params[0]);

            return "";
        }
        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        protected void onPostExecute(String arg) {
            try {
                setProgressBarIndeterminateVisibility(false);
                nafilajSlike();
                nafilajTekst();
                
                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                Date d = new Date();
                editor.putString("Cas", DateFormat.getDateTimeInstance().format(d));
                editor.commit();
                
            } catch (Exception e) {
                Toast.makeText(ActivityVreme.this, "Napaka v komunikaciji ali neobstojeè kraj!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
    }
}
