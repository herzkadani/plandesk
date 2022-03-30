package ch.bbzbl.plandesk.dto.desk;

import ch.bbzbl.plandesk.data.desk.VorgangData;

import java.util.ArrayList;

public class SpaltenDto {

    private int ID;
    private String name;
    private ArrayList<VorgangDto> vorgaenge = new ArrayList<>();

    public SpaltenDto(int ID, String name) {
        this.ID = ID;
        this.name = name;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToVorgangArray(VorgangDto vorgangDto){
        vorgaenge.add(vorgangDto);
    }

    public ArrayList<VorgangDto> getVorgaenge() {
        return vorgaenge;
    }

    public void setVorgaenge(ArrayList<VorgangDto> vorgaenge) {
        this.vorgaenge = vorgaenge;
    }

}
