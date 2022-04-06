package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.bl.desk.Converter;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

public class InMemoryData implements DataInterface{

    private BoardData boardData;
    private final Converter converter = new Converter();
    private int vorgangCounter;
    private int spaltenCounter;
    private int mitarbeiterCounter;

    @Override
    public void createBugFix(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, String meldeTicket) {

        getSpalteByID(SpaltenID).addToVorgangArray(
                new BugFixData(vorgangCounter,name, beschreibung,converter.convertToMitarbeiterData(boardData.getMitarbeiter(),mitarbeiter), dringlichkeit, meldeTicket));
        vorgangCounter++;
    }

    @Override
    public void createNewFunction(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, boolean genemigt) {
        getSpalteByID(SpaltenID).addToVorgangArray(
                new NewFunctionData(vorgangCounter,name, beschreibung,converter.convertToMitarbeiterData(boardData.getMitarbeiter(),mitarbeiter), dringlichkeit, genemigt ));
        vorgangCounter++;
    }

    @Override
    public void createImprovement(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpaltenID, String funktion) {
        getSpalteByID(SpaltenID).addToVorgangArray(
                new VerbesserungData(vorgangCounter,name, beschreibung,converter.convertToMitarbeiterData(boardData.getMitarbeiter(),mitarbeiter), dringlichkeit, funktion ));
    vorgangCounter++;
    }

    @Override
    public void deleteVorgangByID(int ID) {

        VorgangData vd = null;

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == ID){
                    vd = VD;
                }
            }
            SD.removeFromVorgangArray(vd);
        }
    }

    @Override
    public void updateVorgang(int VorgangID, VorgangDto vorgangDto) {

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == VorgangID){
                    VD.setTitel(vorgangDto.getTitel());
                    VD.setBeschreibung(vorgangDto.getBeschreibung());
                    VD.setMitarbeiter(converter.convertToMitarbeiterData(boardData.getMitarbeiter(), vorgangDto.getMitarbeiter()));
                    VD.setDringlichkeit(vorgangDto.getDringlichkeit());
                }
            }
        }

    }

    @Override
    public void setMitarbeiterOfVorgang(int VorgangID, ArrayList<MitarbeiterDto> mitarbeiter) {

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == VorgangID){

                    VD.setMitarbeiter(converter.convertToMitarbeiterData(boardData.getMitarbeiter(), mitarbeiter));
                }
            }
        }
    }

    @Override
    public void editVorgangArray(int VorgangID, int NewSpaltenID) {
        VorgangData transVorgang = null;

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == VorgangID){
                    transVorgang = VD;
                }
            }
            SD.removeFromVorgangArray(transVorgang);
        }
        getSpalteByID(NewSpaltenID).addToVorgangArray(transVorgang);
    }
    @Override
    public BoardDto getBoard() {
        return converter.convertBoardToDto(boardData);
    }
    @Override
    public VorgangDto getVorgangByID(int VorgangID) {
        for (SpaltenData SD : boardData.getSpalten()) {
            for (VorgangData VD : SD.getVorgaenge()) {
                if (VD.getID() == VorgangID) {
                    return converter.convertToVorgangDto(VD);
                }
            }
        }
        return null;
    }
    @Override
    public void initBoard() {

        boardData = new BoardData();
        boardData.setName("PlanDesk");

        String[] spaltenNamen = {"Backlog","In Progress", "On Hold", "Done" };
        String[] vornamen = {"Dani", "Enea", "Levi", "Oliver", "Simon"};
        String[] nachnamen = {"Herzka", "Siess", "Burn", "Saladin", "Emmisberger"};

        for(int i = 0;  i < 4; i++){
            boardData.addSpalte(new SpaltenData(spaltenCounter, spaltenNamen[i]));
            spaltenCounter++;
        }

        for(int f = 0;  f < 5; f++){
            boardData.addMitarbeiter(createMitarbeiter(mitarbeiterCounter, vornamen[f],nachnamen[f]));
            mitarbeiterCounter++;
        }

    }

    public MitarbeiterData createMitarbeiter(int ID,String vorname, String nachname){
            return new MitarbeiterData(ID, vorname, nachname);
    }
    @Override
    public void createNewMitarbeiter(String vorname, String nachname){
        boardData.addMitarbeiter(new MitarbeiterData(mitarbeiterCounter, vorname,nachname));
        mitarbeiterCounter++;
    }

    @Override
    public SpaltenData getSpalteByID(int SpaltenId) {

        for(SpaltenData SD : boardData.getSpalten()){
            if(SD.getID() == SpaltenId){
                return SD;
            }
        }
        return null;
    }
    @Override
    public SpaltenDto getSpalteByVorgangID(int VorgangID) {
        for (SpaltenData SD : boardData.getSpalten()) {
            for (VorgangData VD : SD.getVorgaenge()) {
                if (VD.getID() == VorgangID) {
                     return converter.convertToSpaltenDto(SD);
                }
            }
        }
        return null;
    }
}
