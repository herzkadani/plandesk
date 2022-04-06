package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class BoardData {

    private String name;
    private ArrayList<SpaltenData> spalten = new ArrayList<>();
    private ArrayList<MitarbeiterData> mitarbeiter = new ArrayList<>();

    public BoardData() {
        this.name = "PlanDesk";
    }

    public ArrayList<SpaltenData> getSpalten() {
        return spalten;
    }

    /**
     * adds a Column to the Array
     * @param spaltenData Column that will be added
     */
    public void addSpalte(SpaltenData spaltenData){
        spalten.add(spaltenData);
    }

    public void setSpalten(ArrayList<SpaltenData> spalten) {
        this.spalten = spalten;
    }

    public ArrayList<MitarbeiterData> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(ArrayList<MitarbeiterData> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    /**
     * adds a mitarbeiter to the Array
     * @param mitarbeiterData mitarbeiter that will be added
     */
    public void addMitarbeiter(MitarbeiterData mitarbeiterData){
        mitarbeiter.add(mitarbeiterData);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
