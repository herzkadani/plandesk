package ch.bbzbl.plandesk.data.desk;

public class MitarbeiterData {

    private int ID;
    private String vorname;
    private String nachname;
    private String Farbe;

    public MitarbeiterData(int ID, String vorname, String nachname, String farbe) {
        this.ID = ID;
        this.vorname = vorname;
        this.nachname = nachname;
        Farbe = farbe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFarbe() {
        return Farbe;
    }

    public void setFarbe(String farbe) {
        Farbe = farbe;
    }


}
