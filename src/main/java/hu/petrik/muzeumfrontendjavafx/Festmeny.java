package hu.petrik.muzeumfrontendjavafx;

public class Festmeny {
    private int id;
    private String cim;
    private int ev;
    private int kiallitva;

    public Festmeny(int id, String cim, int ev, int kiallitva) {
        this.id = id;
        this.cim = cim;
        this.ev = ev;
        this.kiallitva = kiallitva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public int getEv() {
        return ev;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public int isKiallitva() {
        return kiallitva;
    }

    public void setKiallitva(int kiallitva) {
        this.kiallitva = kiallitva;
    }
}
