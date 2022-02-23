package ch.bbzbl.plandesk.bl.desk;

import ch.bbzbl.plandesk.data.desk.VorgangData;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

public class VorgangConverter {

    public VorgangData convertToVorgangData(VorgangDto vorgangDto){

        return new VorgangData(vorgangDto.getID(),
                vorgangDto.getTitel(),
                vorgangDto.getBeschreibung(),
                vorgangDto.getMitarbeiter(),
                vorgangDto.getDringlichkeit());
    }
    public VorgangDto convertToVorgangDto(VorgangData vorgangData){
        return new VorgangDto(vorgangData.getID(),
                vorgangData.getTitel(),
                vorgangData.getBeschreibung(),
                vorgangData.getMitarbeiter(),
                vorgangData.getDringlichkeit());
    }
}
