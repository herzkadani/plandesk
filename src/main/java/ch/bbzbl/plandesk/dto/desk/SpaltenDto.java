package ch.bbzbl.plandesk.dto.desk;

import ch.bbzbl.plandesk.data.desk.VorgangData;

import java.util.ArrayList;

public class SpaltenDto {

    private int ID;
    private String name;
    private String postition;
    private ArrayList<VorgangDto> vorgaenge;

    public SpaltenDto(int ID, String name, String postition) {
        this.ID = ID;
        this.name = name;
        this.postition = postition;
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

    public String getPostition() {
        return postition;
    }

    public void setPostition(String postition) {
        this.postition = postition;
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
