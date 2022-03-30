package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class SpaltenData {

    private int ID;
    private String name;
    private ArrayList<VorgangData> vorgaenge = new ArrayList();

    public SpaltenData(int ID, String name) {
        this.ID = ID;
        this.name = name;
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

    public ArrayList<VorgangData> getVorgaenge() {
        return vorgaenge;
    }

}
