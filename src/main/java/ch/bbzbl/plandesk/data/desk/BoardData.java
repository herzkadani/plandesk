package ch.bbzbl.plandesk.data.desk;

import java.util.ArrayList;

public class BoardData {

    private String name;
    private ArrayList<SpaltenData> spalten = new ArrayList();
    private ArrayList<MitarbeiterData> mitarbeiter = new ArrayList();

    public BoardData(String name) {
        this.name = name;
    }

    public void addNewMitarbeiter(MitarbeiterData mitarbeiterData){
        mitarbeiter.add(mitarbeiterData);
    }


    public ArrayList<SpaltenData> getSpalten() {
        return spalten;
    }
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
