package edu.gricar.brezskrbnik.koledar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import edu.gricar.brezskrbnik.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class KoledarActivity extends Activity {

    private static final int TIME_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID2 = 1;
    private static final int DATE_DIALOG_ID = 2;
    private static final int DATE_DIALOG_ID2 = 3;
    Button odDatum, doDatum, odUra, doUra;
    EditText tvime, tvkje, tvopis;
    Spinner spinner;
    ArrayList<Koledar> koledar = new ArrayList<Koledar>();
    String[] spinTabela;
    public static int oznacen=1;
    Calendar koledarDo;
    Calendar koledarOd;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opomniki);

        odDatum = (Button) findViewById(R.id.btnDogodkiOdDatum);
        doDatum = (Button) findViewById(R.id.btnDogodkiDoDatum);
        odUra = (Button) findViewById(R.id.btnDogodkiOdCas);
        doUra = (Button) findViewById(R.id.btnDogodkiDoCas);
        spinner = (Spinner) findViewById(R.id.spinnerDogodkiKoledar);
        tvime = (EditText) findViewById(R.id.etDogodkiIme);
        tvkje = (EditText) findViewById(R.id.etDogodkiLokacija);
        tvopis = (EditText) findViewById(R.id.etDogodkiOpis);
        
        koledarjiNaVoljoSo();
        zacetnaFazapolnjena();
    }


    void zacetnaFazapolnjena(){
        Calendar c = Calendar.getInstance();
        koledarDo = Calendar.getInstance();
        koledarOd = Calendar.getInstance();
        int h,m;
        h = c.getTime().getHours();
        m = c.getTime().getMinutes();
        odUra.setText("" + h + ":" + m);
        h+=1;
        doUra.setText("" + h + ":" + m);
        koledarOd.set(Calendar.HOUR_OF_DAY, h);
        koledarOd.set(Calendar.MINUTE, m);
        koledarDo.set(Calendar.HOUR_OF_DAY, h+1);
        koledarDo.set(Calendar.MINUTE, m+1);
        
        odDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime()).toString());
        doDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime()).toString());
        
        
        spinTabela = new String[koledar.size()];
        
        for (int i=0; i < koledar.size(); i++){
            spinTabela[i] = koledar.get(i).getIme();
        }
        
        ArrayAdapter<String> katAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, spinTabela);
        katAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(katAdapter);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
        
        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            oznacen = position + 1; //ker se koledarji zaènejo z 1
        }

        
        public void onNothingSelected(AdapterView<?> parentView) {}
        });
    }

    void koledarjiNaVoljoSo(){
        String[] projection = new String[] { "_id", "name" };
        Uri calendars = Uri.parse("content://com.android.calendar/calendars");
             
        Cursor managedCursor = managedQuery(calendars, projection, null, null, null);
        if (managedCursor.moveToFirst()) {
            String calName; 
            String calId; 
            int nameColumn = managedCursor.getColumnIndex("name"); 
            int idColumn = managedCursor.getColumnIndex("_id");
            do {
                
               calName = managedCursor.getString(nameColumn);
               calId = managedCursor.getString(idColumn);
               koledar.add(new Koledar(calName, calId));
            } while (managedCursor.moveToNext());
           }
        
    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            odUra.setText(""+hourOfDay + ":" +minute);
            koledarOd.set(Calendar.HOUR_OF_DAY, hourOfDay);
            koledarOd.set(Calendar.MINUTE, minute);
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            doUra.setText(""+hourOfDay + ":" +minute);
            koledarDo.set(Calendar.HOUR_OF_DAY, hourOfDay);
            koledarDo.set(Calendar.MINUTE, minute);
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar d = Calendar.getInstance();
            d.set(year, month, day);
            odDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(d.getTime()).toString());
            koledarOd.set(year, month, day);
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar d = Calendar.getInstance();
            d.set(year, month, day);
            doDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(d.getTime()).toString());
            koledarDo.set(year, month, day);
        }
    };

    protected Dialog onCreateDialog(int id) {
        switch (id) {
        case TIME_DIALOG_ID:
            Calendar c = Calendar.getInstance();
            return new TimePickerDialog(this,mTimeSetListener, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true);
       
        case TIME_DIALOG_ID2:
            Calendar c1 = Calendar.getInstance();
            return new TimePickerDialog(this,mTimeSetListener2, c1.get(Calendar.HOUR), c1.get(Calendar.MINUTE), true);

        case DATE_DIALOG_ID:
            Calendar c2 = Calendar.getInstance();
            return new DatePickerDialog(this, mDateSetListener, c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), c2.get(Calendar.DATE));

        case DATE_DIALOG_ID2:
            Calendar c3 = Calendar.getInstance();
            return new DatePickerDialog(this, mDateSetListener2, c3.get(Calendar.YEAR), c3.get(Calendar.MONTH), c3.get(Calendar.DATE));
        }
        return null;
    }

    public void onDogodkiOdDatum(View v){
        showDialog(DATE_DIALOG_ID);
    }

    public void onDogodkiDoDatum(View v){
        showDialog(DATE_DIALOG_ID2);
    }

    public void onDogodkiOdCas(View v){
        showDialog(TIME_DIALOG_ID);
    }

    public void onDogodkiDoCas(View v){
        showDialog(TIME_DIALOG_ID2);
    }
    
    public void onDogodkiPrikazi(View v){
        Intent i = new Intent(this.getApplicationContext(), CalendarActivity.class);
        startActivity(i);
    }
    
    public void onDogodkiPovrni(View v){
        this.finish();
    }
    
    private String getCalendarUriBase() {
        String calendarUriBase = null;
        Uri calendars = Uri.parse("content://calendar/calendars");
        Cursor managedCursor = null;
        try {
            managedCursor = managedQuery(calendars, null, null, null, null);
        } catch (Exception e) {
            // eat
        }

        if (managedCursor != null) {
            calendarUriBase = "content://calendar/";
        } else {
            calendars = Uri.parse("content://com.android.calendar/calendars");
            try {
                managedCursor = managedQuery(calendars, null, null, null, null);
            } catch (Exception e) {
                // eat
            }

            if (managedCursor != null) {
                calendarUriBase = "content://com.android.calendar/";
            }

        }

        return calendarUriBase;
    }
    
    public void onDogodkiDokoncano(View v){
        //private Uri MakeNewCalendarEntry(int calId) {
            if (tvime.getText().toString().equalsIgnoreCase("") || koledarOd.getTimeInMillis() > koledarDo.getTimeInMillis()){
                Toast.makeText(this, "Vpiši ime / vnesi pravilen èas", Toast.LENGTH_LONG).show();
            }
            else{
            ContentValues event = new ContentValues();

            event.put("calendar_id", oznacen);
            event.put("title", tvime.getText().toString());
            event.put("description", tvopis.getText().toString());
            event.put("eventLocation", tvkje.getText().toString());
            
   
            
            //long startTime = System.currentTimeMillis() + 2000 * 60 * 60;
            //long startTime = System.currentTimeMillis() + time
            long endTime = System.currentTimeMillis() + 2000 * 60 * 60 * 2;

            event.put("dtstart", koledarOd.getTimeInMillis());
            event.put("dtend", koledarDo.getTimeInMillis());

            event.put("allDay", 0); // 0 for false, 1 for true
            event.put("eventStatus", 1);
            event.put("visibility", 0);
            event.put("transparency", 0);
            event.put("hasAlarm", 0); // 0 for false, 1 for true
           
            Uri eventsUri = Uri.parse(getCalendarUriBase()+"events");

            Uri insertedUri = getContentResolver().insert(eventsUri, event);
            Toast.makeText(this, "Dogodek: " + tvime.getText().toString() + " vpisan.", Toast.LENGTH_LONG).show();
            this.finish();
            }
            //return insertedUri;
       // }
    }
    
    
}
