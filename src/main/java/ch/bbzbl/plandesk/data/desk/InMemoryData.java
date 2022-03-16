package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class InMemoryData implements DataInterface{

    private BoardData boardData;
    //im implementing this right now
    @Override
    public void createVorgang(String title, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, String type, int SpaltenID) {
       switch(type){
           case "BugFix":
           VorgangData newvorgang = new BugFixData(0,title, beschreibung,mitarbeiter, dringlichkeit, null );
           break;

       }


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
