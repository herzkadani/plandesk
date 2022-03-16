package ch.bbzbl.plandesk.dto.desk;

import ch.bbzbl.plandesk.data.desk.MitarbeiterData;

import java.util.ArrayList;

public class VorgangDto {

    private int ID;
    private String titel;
    private String beschreibung;
    private ArrayList<MitarbeiterDto> mitarbeiter;
    private int dringlichkeit;
    private String type;
    private String meldeticket;
    private String function;
    private boolean genehmigt;

    public VorgangDto(int ID, String titel, String beschreibung, ArrayList<MitarbeiterDto> mitarbeiter, int dringlichkeit, String type, String meldeticket, String function, boolean genehmigt) {
        this.ID = ID;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.mitarbeiter = mitarbeiter;
        this.dringlichkeit = dringlichkeit;
        this.type = type;
        this.meldeticket = meldeticket;
        this.function = function;
        this.genehmigt = genehmigt;
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

    public ArrayList<MitarbeiterDto> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(ArrayList<MitarbeiterDto> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public int getDringlichkeit() {
        return dringlichkeit;
    }

    public void setDringlichkeit(int dringlichkeit) {
        this.dringlichkeit = dringlichkeit;
    }
}
