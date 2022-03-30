package ch.bbzbl.plandesk.dto.desk;

public class MitarbeiterDto {

    private int ID;
    private String vorname;
    private String nachname;

    public MitarbeiterDto(int ID, String vorname, String nachname) {
        this.ID = ID;
        this.vorname = vorname;
        this.nachname = nachname;

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

}
