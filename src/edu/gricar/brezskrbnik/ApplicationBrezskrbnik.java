package edu.gricar.brezskrbnik;

import java.util.ArrayList;
import java.util.List;

import edu.gricar.brezskrbnik.bazaOpomnik.DBAdapterStevec;
import edu.gricar.brezskrbnik.koledar.Opomniki;
import edu.gricar.brezskrbnik.vreme.AccuParser;
import edu.gricar.brezskrbnik.vreme.Vreme;
import android.app.Application;
import android.widget.Toast;

public class ApplicationBrezskrbnik extends Application {
	ApplicationBrezskrbnik app;
	Opomniki mojTrenutniRezultat; //preimenoval
	public ArrayList<Opomniki> lista;
	DBAdapterStevec db;
	public String imeUporabnika;
	public Vreme[] vreme;
		
	public void onCreate() {
        super.onCreate(); //ne pozabi
        db = new DBAdapterStevec(this); 
        init();// na zacetku prazen
        //db.open();

        //addRezultat();
	}
	
	public void init() {
		mojTrenutniRezultat = new Opomniki();
		mojTrenutniRezultat.setId_l(3);

	}
	
	public void add(Opomniki a) {
		
		}
	
	public void addRezultat() {
		Opomniki tmp = new Opomniki();
		
		tmp.setId_l(5);
		tmp.setPodatki("gla");
		addDB(tmp);
	}
	
	
	public void addDB(Opomniki r) {
		db.open();
		r.setDbID(db.insertRezultat(r));
		db.close();	
	}
	// vreme
	public void setVreme(Vreme[] vreme){
	    this.vreme = vreme.clone();
	}	

	}