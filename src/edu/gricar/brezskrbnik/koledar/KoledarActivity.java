package edu.gricar.brezskrbnik.koledar;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import edu.gricar.brezskrbnik.R;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class KoledarActivity extends Activity {

    private static final int TIME_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID2 = 1;
    private static final int DATE_DIALOG_ID = 2;
    private static final int DATE_DIALOG_ID2 = 3;
    Button odDatum, doDatum, odUra, doUra;
    

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opomniki);

        odDatum = (Button) findViewById(R.id.btnDogodkiOdDatum);
        doDatum = (Button) findViewById(R.id.btnDogodkiDoDatum);
        odUra = (Button) findViewById(R.id.btnDogodkiOdCas);
        doUra = (Button) findViewById(R.id.btnDogodkiDoCas);
        zacetnaFazapolnjena();
    }


    void zacetnaFazapolnjena(){
        Calendar c = Calendar.getInstance();
        int h,m;
        h = c.getTime().getHours();
        m = c.getTime().getMinutes();
        odUra.setText("" + h + ":" + m);
        h+=1;
        doUra.setText("" + h + ":" + m);

        
        odDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime()).toString());
        doDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime()).toString());

    }

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            odUra.setText(""+hourOfDay + ":" +minute);
        }
    };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            doUra.setText(""+hourOfDay + ":" +minute);
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar d = Calendar.getInstance();
            d.set(year, month, day);
            odDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(d.getTime()).toString());
        }
    };

    private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar d = Calendar.getInstance();
            d.set(year, month, day);
            doDatum.setText(DateFormat.getDateInstance(DateFormat.LONG).format(d.getTime()).toString());
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
}
