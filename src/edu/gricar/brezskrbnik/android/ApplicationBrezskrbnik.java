package edu.gricar.brezskrbnik.android;

import java.util.ArrayList;
import edu.gricar.brezskrbnik.bazaOpomnik.DBAdapterStevec;
import android.app.Application;




public class ApplicationBrezskrbnik extends Application {
	ApplicationBrezskrbnik app;
	Opomniki mojTrenutniRezultat; //preimenoval
	public ArrayList<Opomniki> lista;
	DBAdapterStevec db;
	public String imeUporabnika;
	
	
	public void onCreate() {
        super.onCreate(); //ne pozabi
        db = new DBAdapterStevec(this); 
        init();// na zacetku prazen
	}
	
	
	public void init() {
		mojTrenutniRezultat = new Opomniki();
		mojTrenutniRezultat.setId_l(3);

	}
	
	public void add(Opomniki a) {
		
		}
	
	public void addRezultat(int st_ugibanj) {
		Opomniki tmp = new Opomniki();
		tmp.setId_l(5);
		tmp.setPodatki("gla");
		addDB(tmp);
	}
	
	/*public void fillFromDB() {
		db.open();
		Cursor c = db.getAll();
		Rezultati tmp;
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			tmp = new Rezultati();
			tmp.setIme(c.getString(DBAdapterStevec.POS_IME));
			tmp.setSt_ugibanj(c.getInt(DBAdapterStevec.POS_ST_UGIBANJ));
			tmp.setDbID(c.getLong(DBAdapterStevec.POS__ID));
			tmp.setDatum(c.getString(DBAdapterStevec.POS_DATUM));
			lista.add(tmp); 
		}
		c.close();
		db.close();
	}*/
	
	public void addDB(Opomniki r) {
		db.open();
		r.setDbID(db.insertRezultat(r));
		db.close();	
	}
	
	}