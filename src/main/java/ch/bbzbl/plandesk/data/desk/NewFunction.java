package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class NewFunction extends VorgangData{

    private boolean genehmigt;

    public NewFunction(int ID, String titel, String beschreibung, ArrayList<MitarbeiterData> mitarbeiter, int dringlichkeit, boolean genehmigt) {
        super(ID, titel, beschreibung, mitarbeiter, dringlichkeit);
        this.genehmigt = genehmigt;
    }


    public boolean isGenehmigt() {
        return genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }
}
