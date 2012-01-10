package edu.gricar.brezskrbnik.widget;

import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import edu.gricar.brezskrbnik.vreme.ActivityVreme;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
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
                    remoteViews.setTextViewText(R.id.tvWidgetVremeOpis, app.vreme[0].getOpis().toString());
                    remoteViews.setTextViewText(R.id.tvWidgetVremeTemp, app.vreme[0].getRealfeel().toString());
                    nastaviSliko();
                    remoteViews.setTextViewText(R.id.tvWidgetVremeKraj, ActivityVreme.kraj);
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
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("1_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_1);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("2_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_2);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("3_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_3);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("4_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_4);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("5_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_5);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("6_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_6);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("7_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_7);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("8_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_8);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("11_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_11);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("12_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_12);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("13_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_13);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("14_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_14);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("15_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_15);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("16_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_16);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("17_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_17);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("18_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_18);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("19_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_19);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("20_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_20);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("21_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_21);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("22_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_22);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("23_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_23);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("24_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_24);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("25_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_25);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("26_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_26);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("29_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_29);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("30_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_30);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("31_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_31);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("32_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_32);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("33_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_33);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("34_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_34);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("35_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_35);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("36_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_36);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("37_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_37);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("38_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_38);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("39_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_39);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("40_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_40);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("41_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_41);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("42_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_42);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("43_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_43);
        }
        if (app.vreme[0].getSlika().toString().equalsIgnoreCase("40_int.jpg")){
            remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_44);
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
