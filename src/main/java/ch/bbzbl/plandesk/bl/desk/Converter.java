package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.data.desk.*;
import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

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

        for (MitarbeiterData mitarbeiterData : mitarbeiter){
           mitarbeiterDtos.add(new MitarbeiterDto(mitarbeiterData.getID(),
                   mitarbeiterData.getVorname(),
                   mitarbeiterData.getNachname()));
        }
        return mitarbeiterDtos;
    }
    public ArrayList<MitarbeiterData> convertToMitarbeiterData(ArrayList<MitarbeiterDto> mitarbeiter){

        ArrayList<MitarbeiterData> mitarbeiterDatas = new ArrayList<>();

        for (MitarbeiterDto mitarbeiterDto : mitarbeiter){
            mitarbeiterDatas.add(new MitarbeiterData(mitarbeiterDto.getID(),
                    mitarbeiterDto.getVorname(),
                    mitarbeiterDto.getNachname()));
        }
        return mitarbeiterDatas;
    }


    public SpaltenDto convertToSpaltenDto(SpaltenData spaltenData){

        SpaltenDto spaltenDto = new SpaltenDto(spaltenData.getID(),
                spaltenData.getName(),
                spaltenData.getPostition());

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
                    "BugFix",
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
                    "NewFunction",
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
                    "Verbesserung",
                    null,
                    verbesserungData.getFunction(),
                    false);
        }
        }

}
