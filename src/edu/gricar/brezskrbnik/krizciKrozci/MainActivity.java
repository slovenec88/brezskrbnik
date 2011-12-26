package edu.gricar.brezskrbnik.krizciKrozci;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import edu.gricar.brezskrbnik.krizciKrozci.GameActivity;
import edu.gricar.brezskrbnik.krizciKrozci.GameView.State;

public class MainActivity extends Activity implements OnClickListener 
{
    /** Called when the activity is first created. */
    
	ImageButton pc;
	public static boolean igralec;
    
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        //setContentView(R.layout.main);
        
        if (igralec == true)
        	startGame(false);
        else
        	startGame(true);
        finish();

        
       
        /*pc = (ImageButton)findViewById(R.id.start_comp);
        pc.setOnClickListener(this);*/
        
        
    }

    public void startGame(boolean startWithHuman) 
    {
        Intent i = new Intent(this, GameActivity.class);
        
        i.putExtra(GameActivity.EXTRA_START_PLAYER,
                startWithHuman ? State.PLAYER1.getValue() : State.PLAYER2.getValue());
        
        this.startActivity(i);
        
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}