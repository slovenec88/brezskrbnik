package edu.gricar.brezskrbnik.budilka;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import edu.gricar.brezskrbnik.budilka.MyAlarmService;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import org.ksoap2.*;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AlarmActivity extends Activity implements OnClickListener {
    static final int TIME_DIALOG_ID = 0;
    ApplicationBrezskrbnik app;
    static public String kraj;
    static public String[] vremenskiPodatkiTabela;
    private static final String SOAP_ACTION = "http://tempuri.org/Floki";
    private static final String METHOD_NAME = "Floki";
    private static final String NAMESPACE = "http://tempuri.org/";
    public static String URL = "";
    static public String ip;
    static String casIzspleta = "";
    static String vremenskiPodatki ="";
    EditText et;
    TextView tvIP, tvVreme, tvDialogOpis, tvDialogTemp;
    TimePicker tp;
    int ura, minuta;
    Dialog dialogOdlocitev;
    ImageView ivSlika;
    Button btnOK;
    Button btnPreklici;
    public static String iptomcat ="";

    private static final String NAMESPACE2 = "http://service.gricar.edu";
    private static final String SOAP_ACTION2 = NAMESPACE + METHOD_NAME;
    private static final String METHOD_NAME2 = "Parser";
    private static String URL2 = "";

    private PendingIntent mAlarmSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.alarmll);
        showDialog(TIME_DIALOG_ID);
        app = (ApplicationBrezskrbnik) getApplication();
        Button button = (Button) findViewById(R.id.btnAlarm);
        Button button3 = (Button) findViewById(R.id.btnPovezi);
        tvIP = (TextView) findViewById(R.id.textViewIP);
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.btnAlarmStop);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        tp = (TimePicker) findViewById(R.id.timePicker1);
        tp.setIs24HourView(true);
        mAlarmSender = PendingIntent.getService(AlarmActivity.this, 0,
                new Intent(AlarmActivity.this, MyAlarmService.class), 0);

        URL = "http://" + ip + "/service1.asmx";
        URL2 = "http://" + iptomcat + ":8080/testja/services/Service2?wsdl";
        tvIP.setText(URL);
        // pingIIS();

    }

    /*
     * private TimePickerDialog.OnTimeSetListener mTimeSetListener = new
     * TimePickerDialog.OnTimeSetListener() { public void onTimeSet(TimePicker
     * view, int hourOfDay, int minute) { Toast.makeText(AlarmActivity.this,
     * "Time is="+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show(); ura =
     * hourOfDay; minuta = minute; }};
     * 
     * @Override protected Dialog onCreateDialog(int id) { switch (id) { case
     * TIME_DIALOG_ID: return new TimePickerDialog(this,mTimeSetListener, 0, 0,
     * false); } return null; }
     */

    public void nastaviSliko(){
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("1_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_1));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("2_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_2));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("3_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_3));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("4_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_4));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("5_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_5));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("6_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_6));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("7_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_7));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("8_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_8));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("11_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_11));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("12_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_12));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("13_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_13));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("14_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_14));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("15_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_15));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("16_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_16));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("17_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_17));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("18_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_18));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("19_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_19));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("20_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_20));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("21_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_21));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("22_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_22));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("23_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_23));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("24_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_24));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("25_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_25));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("26_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_26));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("29_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_29));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("30_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_30));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("31_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_31));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("32_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_32));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("33_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_33));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("34_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_34));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("35_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_35));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("36_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_36));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("37_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_37));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("38_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_38));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("39_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_39));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("40_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_40));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("41_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_41));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("42_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_42));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("43_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_43));
            }
            if (vremenskiPodatkiTabela[0].toString().equalsIgnoreCase("40_int.jpg")){
                ivSlika.setImageDrawable(getResources().getDrawable(R.drawable.weather_44));
            }       
    }

    public void odpriDialogOdlocanja(){
        dialogOdlocitev = new Dialog(this);
        dialogOdlocitev.setContentView(R.layout.budilkavremedialog);
        btnOK = (Button)dialogOdlocitev.findViewById(R.id.btnDialogOk);
        btnPreklici = (Button)dialogOdlocitev.findViewById(R.id.btnDialogPreklici);
        tvDialogTemp = (TextView) dialogOdlocitev.findViewById(R.id.tvDialogTemp);
        tvDialogOpis = (TextView) dialogOdlocitev.findViewById(R.id.tvDialogOpis);
        ivSlika = (ImageView) dialogOdlocitev.findViewById(R.id.ivDialogSlika);
        nastaviSliko();
        tvDialogTemp.setText(vremenskiPodatkiTabela[1].toString());
        tvDialogOpis.setText(vremenskiPodatkiTabela[2].toString());
        dialogOdlocitev.setTitle("Bova vstala ob " + tp.getCurrentHour().toString() 
                + ":" + tp.getCurrentMinute().toString() + "?" );
        dialogOdlocitev.show();
        
        btnOK.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                
                //bla bla
                int sek = (ura * 60 * 60) + minuta * 60;
                long firstTime = SystemClock.elapsedRealtime();

                /*
                 * // Schedule the alarm! AlarmManager am =
                 * (AlarmManager)getSystemService(ALARM_SERVICE);
                 * am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                 * Integer.parseInt(et.getText().toString())*1000, mAlarmSender);
                 * //Tu nastavi npr 1x na dan 24*60*60*1000 // Tell the user about
                 * what we did.
                 */
                // Single run
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(System.currentTimeMillis());

                Date dt = new Date();
                ura = tp.getCurrentHour();
                minuta = tp.getCurrentMinute();
                int trenutnaura = dt.getHours();
                int trenutnaminuta = dt.getMinutes();

                int cajt = (trenutnaura * 60 * 60) + trenutnaminuta * 60;

                sek = 86400 - cajt;

                int preracunan = (ura * 60 * 60) + minuta * 60;

                // sek = cajt - preracunan;

                if (cajt < preracunan) {
                    sek = preracunan - cajt;
                } else
                    sek = cajt - preracunan;

                calendar.add(Calendar.SECOND, sek);

                Toast.makeText(AlarmActivity.this, "Budilka èez " + sek + "s.",
                        Toast.LENGTH_LONG).show();

                // Schedule the alarm!
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        mAlarmSender);
                //bla bla
                dialogOdlocitev.dismiss();
                
            }
        });
        
        btnPreklici.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                dialogOdlocitev.dismiss();
                
            }
        });

    }
    

    public void onAccu(View v){
        BackgroundAsyncTask2 mt2 = new BackgroundAsyncTask2();
        mt2.execute();
    }

    public void onClickStart(View v) {

    }

    public void onClickStop(View v) {
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.cancel(mAlarmSender);

        // Tell the user about what we did.
        Toast.makeText(AlarmActivity.this, "Stop repeating alarm",
                Toast.LENGTH_LONG).show();

    }

    public void onClick(View v) {

        if (v.getId() == R.id.btnAlarm) {
            int sek = (ura * 60 * 60) + minuta * 60;
            long firstTime = SystemClock.elapsedRealtime();

            /*
             * // Schedule the alarm! AlarmManager am =
             * (AlarmManager)getSystemService(ALARM_SERVICE);
             * am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
             * Integer.parseInt(et.getText().toString())*1000, mAlarmSender);
             * //Tu nastavi npr 1x na dan 24*60*60*1000 // Tell the user about
             * what we did.
             */
            // Single run
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());

            Date dt = new Date();
            ura = tp.getCurrentHour();
            minuta = tp.getCurrentMinute();
            int trenutnaura = dt.getHours();
            int trenutnaminuta = dt.getMinutes();

            int cajt = (trenutnaura * 60 * 60) + trenutnaminuta * 60;

            sek = 86400 - cajt;

            int preracunan = (ura * 60 * 60) + minuta * 60;

            // sek = cajt - preracunan;

            if (cajt < preracunan) {
                sek = preracunan - cajt;
            } else
                sek = cajt - preracunan;

            calendar.add(Calendar.SECOND, sek);

            Toast.makeText(AlarmActivity.this, "Budilka èez " + sek + "s.",
                    Toast.LENGTH_LONG).show();

            // Schedule the alarm!
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    mAlarmSender);

        }
        if (v.getId() == R.id.btnAlarmStop) {
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.cancel(mAlarmSender);

            // Tell the user about what we did.
            Toast.makeText(AlarmActivity.this, "Stop repeating alarm",
                    Toast.LENGTH_LONG).show();
        }

        if (v.getId() == R.id.btnPovezi) {
            BackgroundAsyncTask mt = new BackgroundAsyncTask();
            mt.execute();

        }

    }

    public class BackgroundAsyncTask extends AsyncTask<Void, Integer, String> {
        ProgressDialog dialogWait;

        @Override
        protected String doInBackground(Void... params) {
            dialogWait.setCancelable(true);
            casIzspleta = GetData();
            dialogWait.dismiss();
            return casIzspleta;
        }

        @Override
        protected void onPreExecute() {
            dialogWait = ProgressDialog.show(AlarmActivity.this, "Povezujem",
                    "Poèakajte prosim..", true);
        }

        protected void onPostExecute(String arg) {

            try {
                if (casIzspleta.equalsIgnoreCase("0"))
                    Toast.makeText(AlarmActivity.this,
                            "Napaka v komunikaciji!", Toast.LENGTH_LONG).show();
                else {
                    casIzspleta = arg;

                    String[] ura = casIzspleta.split(":");

                    int iura = Integer.parseInt(ura[0]);
                    int iminuta = Integer.parseInt(ura[1]);

                    tp.setCurrentHour(iura);
                    tp.setCurrentMinute(iminuta);
                }
            } catch (NumberFormatException e) { 
                e.printStackTrace();
            }

        }

        private String GetData() {
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            // Request.addProperty("Celsius", param);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(Request);

            HttpTransportSE aht = new HttpTransportSE(URL);
            try {
                aht.call(SOAP_ACTION, soapEnvelope);
                SoapPrimitive result = (SoapPrimitive) soapEnvelope
                .getResponse();

                return result.toString();
            } catch (Exception e) {

                e.printStackTrace();
            }

            return "0";
        }

        public void pingIIS() {

            try {
                InetAddress address = InetAddress.getByName("www.najdi.si");
                tvIP.setText("Name: " + address.getHostName() + " Addr: "
                        + address.getHostAddress() + " Reach: "
                        + address.isReachable(3000));
            } catch (UnknownHostException e) {
                tvIP.setText("colis");

            } catch (IOException e) {
                tvIP.setText("Unable to reach ");
            }
        }

    }

    public class BackgroundAsyncTask2 extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            vremenskiPodatki = onDobiVreme();

            return vremenskiPodatki;
        }

        @Override
        protected void onPreExecute() {
            setProgressBarIndeterminateVisibility(true);
        }

        protected void onPostExecute(String arg) {
            setProgressBarIndeterminateVisibility(false);
            if ((vremenskiPodatki.substring(0, 6)).equalsIgnoreCase("napaka")){
                Toast.makeText(AlarmActivity.this,
                        "Napaka v komunikaciji!", Toast.LENGTH_LONG).show();
            }
            else{
                vremenskiPodatkiTabela = vremenskiPodatki.split(";");
                odpriDialogOdlocanja();
            }
        }
    }

    public String onDobiVreme(){
        try{
            SoapObject request = new SoapObject(NAMESPACE2, METHOD_NAME2);
            request.addProperty("kraj", kraj);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL2);
            androidHttpTransport.call(SOAP_ACTION2,envelope);
            Object result = envelope.getResponse();
            return result.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "napaka: "+e.toString();
        }

    }
}


