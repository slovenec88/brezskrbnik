package edu.gricar.brezskrbnik.koledar;

public class Koledar {
    
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String ime;
    private String id;

    public Koledar(String ime, String calId){
        this.ime = ime;
        this.id = calId;
    }
}
