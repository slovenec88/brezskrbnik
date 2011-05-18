package edu.gricar.brezskrbnik.android;


public class Opomniki {
	ApplicationBrezskrbnik app;


	private int id_l;
	private int id_s;
	private boolean sprememba;
	private String podatki;
	private long dbID;


	public long getDbID() {
		return dbID;
	}
	public void setDbID(long dbID) {
		this.dbID = dbID;
	}
	public int getId_l() {
		return id_l;
	}
	public void setId_l(int id_l) {
		this.id_l = id_l;
	}
	public int getId_s() {
		return id_s;
	}
	public void setId_s(int id_s) {
		this.id_s = id_s;
	}
	public boolean isSprememba() {
		return sprememba;
	}
	public void setSprememba(boolean sprememba) {
		this.sprememba = sprememba;
	}
	public String getPodatki() {
		return podatki;
	}
	public void setPodatki(String podatki) {
		this.podatki = podatki;
	}



}

