package edu.gricar.brezskrbnik.budilka;

public class Vreme {
    
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getRealfeel() {
        return realfeel;
    }

    public void setRealfeel(String realfeel) {
        this.realfeel = realfeel;
    }

    public String getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(String maxtemp) {
        this.maxtemp = maxtemp;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
    
        public String getSlika() {
        return slika;
    }
    private String datum;
    private String realfeel;
    private String maxtemp;
    private String opis;
    private String slika;
   


    public void setSlika(String slika) {
        this.slika = slika;
    }

    public Vreme(String datum, String realfeel, String maxtemp, String opis, String slika){
        this.datum = datum;
        this.realfeel = realfeel;
        this.maxtemp = maxtemp;
        this.opis = opis;
        this.slika = slika;
    }
}
