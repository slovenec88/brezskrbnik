package edu.gricar.brezskrbnik.widget;

import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
    
    ApplicationBrezskrbnik app;
    
    @Override
    public void onStart(Intent intent, int startId) {
        if(app==null)
            app = (ApplicationBrezskrbnik) getApplicationContext();
        
        RSSReader rss = new RSSReader();
        if(app.getNews()==null || app.getNews().size()==0)
            app.setNews(rss.readNews());
        //app.vreme[0].getOpis().toString();
        
        
        
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] appWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        if (appWidgetIds.length > 0) {
            for (int widgetId : appWidgetIds) {
                RemoteViews remoteViews = new RemoteViews(getPackageName(),
                        R.layout.widget_layout);
                remoteViews.setTextViewText(R.id.TextViewNews, app.getNews().get(app.getStevec()%app.getNews().size()));
                /*remoteViews.setTextViewText(R.id.tvWidgetVremeOpis, app.vreme[0].getOpis().toString());
                remoteViews.setTextViewText(R.id.tvWidgetVremeTemp, app.vreme[0].getRealfeel().toString());
                remoteViews.setImageViewResource(R.id.imageWidgetVreme, R.drawable.weather_1);*/
                
                app.setStevec(app.getStevec()+1);
                appWidgetManager.updateAppWidget(widgetId, remoteViews);
            }
            stopSelf();
        }
        super.onStart(intent, startId);
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
