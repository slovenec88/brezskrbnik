package edu.gricar.brezskrbnik.pomoc;

import edu.gricar.brezskrbnik.R;
import android.content.Context;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.View;
import android.view.animation.*;


public class ActivitySenzor extends GraphicsActivity{   
	public static SensorManager sSensorManager;
	private static AnimateDrawable mDrawable;
	private static Animation an;
	public static float cz = 0;
	public static float cx = 0;
	public static float cy = 0;
	public static int levo = 90;
	public static int gor = 200;
	public static Drawable dr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SampleView(this));
		sSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sSensorManager.registerListener(sSensorListener, sSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION | Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	protected void onStop() {
		sSensorManager.unregisterListener(sSensorListener);
		super.onStop();
	}

	private SensorEventListener sSensorListener = new SensorEventListener() {


		public void onSensorChanged(SensorEvent event) {
			
			cx = event.values[1];
			cy = event.values[2];

			if (cy > 10 && an.hasEnded() == true){
				levo = 0;
				startActivity(getIntent()); finish();
			}

			if (cy < -10 && an.hasEnded() == true){
				levo = 200;
				startActivity(getIntent()); finish();
			}

			if (cx > 10 && an.hasEnded() == true){
				gor = 0;
				startActivity(getIntent()); finish();
			}

			if (cx < -10 && an.hasEnded() == true){
				gor = 300;
				startActivity(getIntent()); finish();			
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	private static class SampleView extends View {
		public SampleView(Context context) {
			super(context);
			setFocusable(true);
			setFocusableInTouchMode(true);

			dr = context.getResources().getDrawable(R.drawable.budilka);
			dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
			an = new TranslateAnimation(90, levo, 200, gor);
			an.setDuration(2000);
			an.setRepeatCount(0);
			an.initialize(90, 90, 200, 200);
			mDrawable = new AnimateDrawable(dr, an);
			an.startNow();
			levo = 90;
			gor = 200;
		}


		@Override protected void onDraw(Canvas canvas) {
			canvas.drawColor(Color.BLACK);

			mDrawable.draw(canvas);
			invalidate();            
		}
	}
}
