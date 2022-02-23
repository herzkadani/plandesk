package ch.bbzbl.plandesk.data.desk;

public class VorgangData {

    private int ID;
    private String titel;
    private String beschreibung;
    private MitarbeiterData mitarbeiter;
    private int dringlichkeit;

    public VorgangData(int ID, String titel, String beschreibung, MitarbeiterData mitarbeiter, int dringlichkeit) {
        this.ID = ID;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.mitarbeiter = mitarbeiter;
        this.dringlichkeit = dringlichkeit;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public MitarbeiterData getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(MitarbeiterData mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public int getDringlichkeit() {
        return dringlichkeit;
    }

    public void setDringlichkeit(int dringlichkeit) {
        this.dringlichkeit = dringlichkeit;
    }


}

