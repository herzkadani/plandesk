package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.data.desk.*;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;
import ch.bbzbl.plandesk.views.desk.IViewConstants;

import java.util.ArrayList;

public class Converter {

    public BoardDto convertBoardToDto(BoardData boardData){

        BoardDto boardDto = new BoardDto(boardData.getName());

        for(SpaltenData spaltenData : boardData.getSpalten()) {
            boardDto.addSpalte(convertToSpaltenDto(spaltenData));
        }

        boardDto.setMitarbeiter(convertToMitarbeiterDto(boardData.getMitarbeiter()));

        return boardDto;
    }

    public ArrayList<MitarbeiterDto> convertToMitarbeiterDto(ArrayList<MitarbeiterData> mitarbeiter){

        ArrayList<MitarbeiterDto> mitarbeiterDtos = new ArrayList<>();

        for (MitarbeiterData mitarbeiterData : mitarbeiter) {
            mitarbeiterDtos.add(new MitarbeiterDto(mitarbeiterData.getID(),
                    mitarbeiterData.getVorname(),
                    mitarbeiterData.getNachname()));
        }
        return mitarbeiterDtos;
    }

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

    public SpaltenDto convertToSpaltenDto(SpaltenData spaltenData){

        SpaltenDto spaltenDto = new SpaltenDto(spaltenData.getID(),
                spaltenData.getName());

        for (VorgangData vorgangData : spaltenData.getVorgaenge()){
            spaltenDto.addToVorgangArray(convertToVorgangDto(vorgangData));
        }
        return spaltenDto;
    }

    public VorgangDto convertToVorgangDto(VorgangData vorgangData){

        if(vorgangData instanceof BugFixData){

            BugFixData bugFixData = (BugFixData) vorgangData;

            return new VorgangDto(bugFixData.getID(),
                    bugFixData.getTitel(),
                    bugFixData.getBeschreibung(),
                    convertToMitarbeiterDto(bugFixData.getMitarbeiter()),
                    bugFixData.getDringlichkeit(),
                    IViewConstants.BUGFIX,
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
                    IViewConstants.NEW_FEATURE,
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
                    IViewConstants.IMPROVEMENT,
                    null,
                    verbesserungData.getFunction(),
                    false);
        }
        }

}
