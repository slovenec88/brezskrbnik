package edu.gricar.brezskrbnik.widget;

import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.vreme.ActivityVreme;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {

    ApplicationBrezskrbnik app;
    RemoteViews remoteViews;

    @Override
    public void onStart(Intent intent, int startId) {
        if(app==null)
            app = (ApplicationBrezskrbnik) getApplicationContext();
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] appWidgetIds = intent
        .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        if (appWidgetIds.length > 0) {
            for (int widgetId : appWidgetIds) {
                remoteViews = new RemoteViews(getPackageName(),
                        R.layout.widget_layout);

                try {
                    SharedPreferences pref = getSharedPreferences("vreme.ActivityVreme", 0);
                    remoteViews.setTextViewText(R.id.tvWidgetVremeOpis, pref.getString("opis", ""));
                    remoteViews.setTextViewText(R.id.tvWidgetVremeTemp, pref.getString("tvTempDanes", ""));
                    nastaviSliko();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }         
                appWidgetManager.updateAppWidget(widgetId, remoteViews);
            }
            stopSelf();
        }
        super.onStart(intent, startId);
    }

    public void nastaviSliko(){
        SharedPreferences pref = getSharedPreferences("vreme.ActivityVreme", 0);
        if (pref.getString("ivslika1", "").equalsIgnoreCase("1_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_1);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("2_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_2);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("3_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_3);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("4_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_4);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("5_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_5);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("6_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_6);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("7_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_7);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("8_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_8);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("11_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_11);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("12_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_12);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("13_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_13);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("14_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_14);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("15_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_15);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("16_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_16);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("17_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_17);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("18_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_18);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("19_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_19);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("20_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_20);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("21_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_21);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("22_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_22);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("23_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_23);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("24_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_24);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("25_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_25);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("26_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_26);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("29_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_29);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("30_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_30);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("31_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_31);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("32_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_32);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("33_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_33);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("34_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_34);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("35_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_35);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("36_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_36);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("37_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_37);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("38_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_38);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("39_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_39);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("40_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_40);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("41_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_41);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("42_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_42);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("43_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_43);
        }
        if (pref.getString("ivslika1", "").equalsIgnoreCase("40_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_44);
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
