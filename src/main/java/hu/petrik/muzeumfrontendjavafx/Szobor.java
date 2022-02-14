package hu.petrik.muzeumfrontendjavafx;

public class Szobor {
    private int id;
    private String nev;
    private int magassag;
    private int ar;

    public Szobor(int id, String nev, int magassag, int ar) {
        this.id = id;
        this.nev = nev;
        this.magassag = magassag;
        this.ar = ar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getMagassag() {
        return magassag;
    }

    public void setMagassag(int magassag) {
        this.magassag = magassag;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }
}
