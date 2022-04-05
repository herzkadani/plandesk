package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.IApplicationConstants;
import ch.bbzbl.plandesk.data.desk.*;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

public class Converter {

    /**
     * converts the Data to a Dto of a Board
     * @param boardData the given Data
     * @return Dto with the Data
     */
    public BoardDto convertBoardToDto(BoardData boardData){

        BoardDto boardDto = new BoardDto(boardData.getName());

        for(SpaltenData spaltenData : boardData.getSpalten()) {
            boardDto.addSpalte(convertToSpaltenDto(spaltenData));
        }

        boardDto.setMitarbeiter(convertToMitarbeiterDto(boardData.getMitarbeiter()));

        return boardDto;
    }

    /**
     * converts mitarbeiter to a Dto
     * @param mitarbeiter List of Data to transfer
     * @return List of mitartbeiter
     */
    public ArrayList<MitarbeiterDto> convertToMitarbeiterDto(ArrayList<MitarbeiterData> mitarbeiter){

        ArrayList<MitarbeiterDto> mitarbeiterDtos = new ArrayList<>();

        for (MitarbeiterData mitarbeiterData : mitarbeiter) {
            mitarbeiterDtos.add(new MitarbeiterDto(mitarbeiterData.getID(),
                    mitarbeiterData.getVorname(),
                    mitarbeiterData.getNachname()));
        }
        return mitarbeiterDtos;
    }

    /**
     * converts Dto to Data of Mitarbeiter
     * @param mitarbeiterData old Data
     * @param mitarbeiter new Data
     * @return List of the new Mitarbeiter
     */
    public ArrayList<MitarbeiterData> convertToMitarbeiterData(ArrayList<MitarbeiterData> mitarbeiterData, ArrayList<MitarbeiterDto> mitarbeiter) {

        ArrayList<MitarbeiterData> mitarbeiterDatas = new ArrayList<>();

        for (MitarbeiterData mitarbeiterData1 : mitarbeiterData) {
            for (MitarbeiterDto mitarbeiterDto : mitarbeiter) {
                if(mitarbeiterData1.getID() == mitarbeiterDto.getID()){
                    mitarbeiterDatas.add(mitarbeiterData1);
                }
            }
        }
        return mitarbeiterDatas;
    }

    /**
     * converts Column Data to Dto
     * @param spaltenData data to tranfer
     * @return Dto of the Data
     */
    public SpaltenDto convertToSpaltenDto(SpaltenData spaltenData){

        SpaltenDto spaltenDto = new SpaltenDto(spaltenData.getID(),
                spaltenData.getName());

        for (VorgangData vorgangData : spaltenData.getVorgaenge()){
            spaltenDto.addToVorgangArray(convertToVorgangDto(vorgangData));
        }
        return spaltenDto;
    }

    /**
     * converts a Vorgang to a Dto
     * @param vorgangData data to transfer
     * @return Dto of a Vprgang
     */
    public VorgangDto convertToVorgangDto(VorgangData vorgangData){

        if(vorgangData instanceof BugFixData){

            BugFixData bugFixData = (BugFixData) vorgangData;

            return new VorgangDto(bugFixData.getID(),
                    bugFixData.getTitel(),
                    bugFixData.getBeschreibung(),
                    convertToMitarbeiterDto(bugFixData.getMitarbeiter()),
                    bugFixData.getDringlichkeit(),
                    IApplicationConstants.BUGFIX,
                    bugFixData.getMeldeticket(),
                    null,
                    false);
        }else if(vorgangData instanceof NewFunctionData){

            NewFunctionData newFunction = (NewFunctionData) vorgangData;

            return new VorgangDto(newFunction.getID(),
                    newFunction.getTitel(),
                    newFunction.getBeschreibung(),
                    convertToMitarbeiterDto(newFunction.getMitarbeiter()),
                    newFunction.getDringlichkeit(),
                    IApplicationConstants.NEW_FEATURE,
                    null,
                    null,
                    newFunction.isGenehmigt());


        }else{

            VerbesserungData verbesserungData = (VerbesserungData) vorgangData;

            return new VorgangDto(verbesserungData.getID(),
                    verbesserungData.getTitel(),
                    vorgangData.getBeschreibung(),
                    convertToMitarbeiterDto(verbesserungData.getMitarbeiter()),
                    verbesserungData.getDringlichkeit(),
                    IApplicationConstants.IMPROVEMENT,
                    null,
                    verbesserungData.getFunction(),
                    false);
        }
        }

}
