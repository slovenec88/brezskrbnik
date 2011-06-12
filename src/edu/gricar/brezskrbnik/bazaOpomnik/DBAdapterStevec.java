package edu.gricar.brezskrbnik.bazaOpomnik;

import edu.gricar.brezskrbnik.koledar.Opomniki;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class DBAdapterStevec implements BaseColumns {
	public static final  String TAG="DBAdapterStevec";

	public static final  String IDS = "i_ids";
	public static final  String IDL = "s_idl";
	public static final  String SPREM = "b_sprem";
	public static final  String PODATKI = "s_podatki";
	public static final  int POS__ID=0;
	public static final  int POS_IDS=1;
	public static final  int POS_IDL=2;
	public static final  int POS_SPREM=3;
	public static final  int POS_POD=4;

	public static final  String TABLE="opomniki";



	private final Context context;

	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public DBAdapterStevec(Context ctx) 
	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}


	//---opens the database---
	public DBAdapterStevec open() throws SQLException 
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}

	//---closes the database---    
	public void close() 
	{
		DBHelper.close();
	}

	//---insert a stevec
	public long insertRezultat(Opomniki opomniki) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(IDL, opomniki.getId_l()); 
		initialValues.put(IDS, opomniki.getId_s()); 
		initialValues.put(SPREM, opomniki.isSprememba()); 
		initialValues.put(PODATKI, opomniki.getPodatki()); 
		return db.insert(TABLE, null, initialValues);
	}

	//---deletes a particular title---
	public boolean deleteRezultat(long rowId) 
	{
		return db.delete(TABLE, _ID + "=" + rowId, null) > 0;
	}

	//---retrieves all the titles---
	public Cursor getAll() 
	{
		return db.query(TABLE, new String[] {
				_ID,       //POS__ID=0;
				IDL,      //POS_NAME=1
				IDS,//POS_VALUE =2
				SPREM,
				PODATKI,},    
				null, 
				null, 
				null, 
				null, 
				null);
	}

	//---retrieves a particular title---
	public Cursor getRezultat(long rowId) throws SQLException 
	{
		Cursor mCursor =
			db.query(true, TABLE, new String[] {
					_ID, 
					IDL,
					IDS,
					SPREM,
					PODATKI,}, 
					_ID + "=" + rowId, 
					null,
					null, 
					null, 
					null, 
					null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	//---update---
	public boolean updateRezultat(Opomniki tmp) 
	{
		ContentValues args = new ContentValues();
		args.put(IDL, tmp.getId_l());
		args.put(IDS, tmp.getId_s());
		args.put(SPREM, tmp.isSprememba());
		args.put(PODATKI, tmp.getPodatki());
		return db.update(TABLE, args, 
				_ID + "=" + tmp.getDbID(), null) > 0;
	}


}
