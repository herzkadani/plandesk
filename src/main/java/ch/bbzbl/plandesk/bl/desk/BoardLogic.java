package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.data.desk.InMemoryData;
import ch.bbzbl.plandesk.data.desk.MitarbeiterData;

import java.util.ArrayList;

public class BoardLogic {

    InMemoryData inMemoryData = new InMemoryData();

    public void createVorgang(String titel, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, String type, int SpaltenID){
        inMemoryData.createVorgang(titel,beschreibung, dringlichkeit,  mitarbeiter, type, SpaltenID);
    }


}
