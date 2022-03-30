package ch.bbzbl.plandesk.data.desk;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import java.util.ArrayList;

public abstract class VorgangData {

    private int ID;
    private String titel;
    private String beschreibung;
    private ArrayList<MitarbeiterData> mitarbeiter;
    private String dringlichkeit;

    public VorgangData(int ID, String titel, String beschreibung, ArrayList<MitarbeiterData> mitarbeiter, String dringlichkeit) {
        this.ID = ID;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.mitarbeiter = mitarbeiter;
        this.dringlichkeit = dringlichkeit;
    }

    @Id
    @GenericGenerator(name="id" , strategy="increment")
    @GeneratedValue
    @Column(unique = true, nullable = false)
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public ArrayList<MitarbeiterData> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(ArrayList<MitarbeiterData> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public String getDringlichkeit() {
        return dringlichkeit;
    }

    public void setDringlichkeit(String dringlichkeit) {
        this.dringlichkeit = dringlichkeit;
    }

    public void addMitarbeiter(MitarbeiterData mitarbeiterData){
        mitarbeiter.add(mitarbeiterData);
    }
}

