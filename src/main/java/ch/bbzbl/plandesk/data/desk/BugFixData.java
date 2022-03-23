package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class BugFixData extends VorgangData{

    private String Meldeticket;

    public BugFixData(String titel, String beschreibung, ArrayList<MitarbeiterData> mitarbeiter, String dringlichkeit, String meldeticket) {
        super(titel, beschreibung, mitarbeiter, dringlichkeit);
        Meldeticket = meldeticket;
    }

    public String getMeldeticket() {
        return Meldeticket;
    }

    public void setMeldeticket(String meldeticket) {
        Meldeticket = meldeticket;
    }
}
