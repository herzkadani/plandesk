package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class NewFunctionData extends VorgangData{

    private boolean genehmigt;

    public NewFunctionData(int ID,String titel, String beschreibung, ArrayList<MitarbeiterData> mitarbeiter, String dringlichkeit, boolean genehmigt) {
        super(ID,titel, beschreibung, mitarbeiter, dringlichkeit);
        this.genehmigt = genehmigt;
    }


    public boolean isGenehmigt() {
        return genehmigt;
    }

    public void setGenehmigt(boolean genehmigt) {
        this.genehmigt = genehmigt;
    }
}
