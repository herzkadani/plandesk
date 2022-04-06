package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.data.desk.DataInterface;
import ch.bbzbl.plandesk.data.desk.InMemoryData;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

public class BoardLogic {

    DataInterface inMemoryData = new InMemoryData();

    public void createBugFix(String titel, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, String meldeTicket){
        inMemoryData.createBugFix(titel,beschreibung, dringlichkeit,  mitarbeiter, SpaltenID, meldeTicket);
    }

    public void createNewFunction(String titel, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, boolean genemigt){
        inMemoryData.createNewFunction(titel,beschreibung, dringlichkeit,  mitarbeiter, SpaltenID, genemigt);
    }

    public void createVerbesserung(String titel, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, String funktion){
        inMemoryData.createImprovement(titel,beschreibung, dringlichkeit,  mitarbeiter, SpaltenID, funktion);
    }

    public void deleteVorgangByID(int VorgangID){
        inMemoryData.deleteVorgangByID(VorgangID);
    }

    public void updateVorgang(int VorgangID, VorgangDto vorgangDto){
        inMemoryData.updateVorgang(VorgangID, vorgangDto);
    }

    @Deprecated
    public void setMitarbeiterOfVorgang(int VorgangID, ArrayList<MitarbeiterDto> mitarbeiter){
        inMemoryData.setMitarbeiterOfVorgang(VorgangID, mitarbeiter);
    }

    public void updateSpaltenVorgangArray(int VorgangID, int NewSpalteID){
        inMemoryData.editVorgangArray(VorgangID, NewSpalteID);
    }

    public BoardDto getBoard(){
       return inMemoryData.getBoard();
    }

    public SpaltenDto getSpalteByVorgangID(int VorgangID){
        return inMemoryData.getSpalteByVorgangID(VorgangID);
    }

    public VorgangDto getVorgangByID(int VorgangID){
        return inMemoryData.getVorgangByID(VorgangID);
    }

    public void initBoard(){
        inMemoryData.initBoard();
    }

    public void createNewMitarbeiter(String vorname, String nachname){
        inMemoryData.createNewMitarbeiter(vorname,nachname);
    }

}
