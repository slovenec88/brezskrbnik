package edu.gricar.brezskrbnik;

import edu.gricar.brezskrbnik.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


public class ActivityBudilka extends Activity {

	private Button b1;
	static final int TIME_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.budilka);
	b1 = (Button) findViewById(R.id.button1);
	b1.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
	showDialog(TIME_DIALOG_ID);
	}
	});
	}
	private TimePickerDialog.OnTimeSetListener mTimeSetListener =
	new TimePickerDialog.OnTimeSetListener() {
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	Toast.makeText(ActivityBudilka.this, "Time is="+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
	}
	};
	@Override
	protected Dialog onCreateDialog(int id) {
	switch (id) {
	case TIME_DIALOG_ID:
	return new TimePickerDialog(this,mTimeSetListener, 0, 0, false);
	}
	return null;
	}
	}