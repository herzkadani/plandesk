package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.bl.desk.Converter;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

public class InMemoryData implements DataInterface{

    private BoardData boardData;
    private Converter converter = new Converter();

    @Override
    public void createBugFix(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpaltenID, String meldeTicket) {

        getSpalteByID(SpaltenID).addToVorgangArray(
                new BugFixData(name, beschreibung,mitarbeiter, dringlichkeit, meldeTicket));
    }

    @Override
    public void createNewFunction(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpaltenID, boolean genemigt) {
        getSpalteByID(SpaltenID).addToVorgangArray(
                new NewFunctionData(name, beschreibung,mitarbeiter, dringlichkeit, genemigt ));
    }

    @Override
    public void createVerbesserung(String name, String beschreibung, String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpaltenID, String funktion) {
        getSpalteByID(SpaltenID).addToVorgangArray(
                new VerbesserungData(name, beschreibung,mitarbeiter, dringlichkeit, funktion ));
    }

    @Override
    public void deleteVorgangByID(int ID) {

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == ID){
                    SD.removeFromVorgangArray(VD);
                }
            }
        }
    }

    @Override
    public void updateVorgang(int VorgangID, VorgangDto vorgangDto) {

        for(SpaltenData SD: boardData.getSpalten()){
            for(VorgangData VD: SD.getVorgaenge()){
                if(VD.getID() == VorgangID){
                    VD.setTitel(vorgangDto.getTitel());
                    VD.setBeschreibung(vorgangDto.getBeschreibung());
                    VD.setMitarbeiter(converter.convertToMitarbeiterData(vorgangDto.getMitarbeiter()));
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
                    VD.setMitarbeiter(converter.convertToMitarbeiterData(mitarbeiter));
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
                    SD.removeFromVorgangArray(VD);
                }
            }
        }
        getSpalteByID(NewSpaltenID).addToVorgangArray(transVorgang);
    }
    public BoardDto getBoard(){
        return converter.convertBoardToDto(boardData);
    }
    public VorgangDto getVorgangByID(int VorgangID){
        return null;
    }
    public void createSpalten(){

    }
    public void initBoard(){

    }

    public SpaltenData getSpalteByID(int SpaltenId){

        for(SpaltenData SD : boardData.getSpalten()){
            if(SD.getID() == SpaltenId){
                return SD;
            }
        }
        return null;
    }
}
