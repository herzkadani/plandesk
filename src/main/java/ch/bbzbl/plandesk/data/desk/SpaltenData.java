package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

public class SpaltenData {

    private int ID;
    private String name;
    private String postition;
    private ArrayList<VorgangData> vorgaenge = new ArrayList();

    public SpaltenData(int ID, String name, String postition) {
        this.ID = ID;
        this.name = name;
        this.postition = postition;
    }

    public void addToVorgangArray(VorgangData vorgangData){
        vorgaenge.add(vorgangData);
    }
    public void removeFromVorgangArray(VorgangData vorgangData){
        vorgaenge.remove(vorgangData);
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


}
