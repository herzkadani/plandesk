package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class VerbesserungData extends VorgangData{

    private String function;

    public VerbesserungData(int ID, String titel, String beschreibung, ArrayList<MitarbeiterData> mitarbeiter, String dringlichkeit, String function) {
        super(ID,titel, beschreibung, mitarbeiter, dringlichkeit);
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
