package edu.gricar.brezskrbnik.vreme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import edu.gricar.brezskrbnik.ApplicationBrezskrbnik;
import edu.gricar.brezskrbnik.R;

public class ActivityVreme extends Activity{
    ApplicationBrezskrbnik app;
    ImageView ivslika1, ivslika2;
    @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.vreme);
         app = (ApplicationBrezskrbnik) getApplication();
         ivslika1 = (ImageView) findViewById(R.id.vreme_slika_danes);
         ivslika2 = (ImageView) findViewById(R.id.vreme_slika_danesPlus1);
         nafilajPodatke();
         
     }
    
    public void nafilajPodatke(){
        String slika1 = app.vreme[0].getSlika();
        String slika2= app.vreme[1].getSlika();
        
        if (slika1.equalsIgnoreCase("4_int.jpg")){
            ivslika1.setImageDrawable(getResources().getDrawable(R.drawable.weather_mostlysunny));
        }
        
        if (slika2.equalsIgnoreCase("1_int.jpg")){
            ivslika2.setImageDrawable(getResources().getDrawable(R.drawable.weather_sunny));
        }
        
        
    }
    
}
