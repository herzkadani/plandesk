package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class InMemoryData implements DataInterface{

    private BoardData boardData;

    @Override
    public void createVorgang(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, String Type, int SpaltenID) {

    }

    @Override
    public void deleteVorgangByID(int ID) {

    }

    @Override
    public void editVorgang(int VorgangID,String Attribut, String value) {

    }

    @Override
    public void setMitarbeiterOfVorgang(int VorgangID, int MitarbeiterID) {

    }

    @Override
    public void editVorgangArray(int VorgangID, int SpaltenID, int NewSpaltenID) {

    }
}
