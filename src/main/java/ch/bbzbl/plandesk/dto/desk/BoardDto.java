package ch.bbzbl.plandesk.dto.desk;

import java.util.ArrayList;

public class BoardDto {

    private String name;
    private ArrayList<SpaltenDto> spalten = new ArrayList();
    private ArrayList<MitarbeiterDto> mitarbeiter = new ArrayList();

    public BoardDto(String name) {
        this.name = name;
    }
    /**
     * adds a Column to the Array
     * @param spaltenDto Column that will be added
     */
    public void addSpalte(SpaltenDto spaltenDto){
        spalten.add(spaltenDto);
    }

    public ArrayList<SpaltenDto> getSpalten() {
        return spalten;
    }

    public void setSpalten(ArrayList<SpaltenDto> spalten) {
        this.spalten = spalten;
    }
    /**
     * adds a mitarbeiter to the Array
     * @param mitarbeiterDto mitarbeiter that will be added
     */
    public void addMitarbeiter(MitarbeiterDto mitarbeiterDto){
        mitarbeiter.add(mitarbeiterDto);
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<MitarbeiterDto> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(ArrayList<MitarbeiterDto> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }
}
