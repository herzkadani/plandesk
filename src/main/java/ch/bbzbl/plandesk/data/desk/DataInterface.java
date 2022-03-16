package ch.bbzbl.plandesk.data.desk;


import java.util.ArrayList;

public interface DataInterface {
    /**
     * creates a new Vorgang
     * @param Type sets the type of the Vorgang
     * @param SpatenID identifing the Spalte of Vorgang
     */
    void createVorgang(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter,  String Type, int SpatenID);

    /**
     * deletes a Vorgang
     * @param ID identifing the Vorgang
     */
    void deleteVorgangByID(int ID);
    /**
     * changes a value of a Attribut of a Vorgang
     * @param VorgangID identifing the Vorgang
     * @param Attribut Attribut of the Vorgang
     * @param Value Value hat the new Value of the Attribut
     */
    void editVorgang(int VorgangID,String Attribut, String Value);

    /**
     * adds a Mitarbeiter to a Vorgang
     * @param VorgangID identifing the Vorgang
     * @param MitarbeiterID identifing the Mitarbeiter
     */
    void setMitarbeiterOfVorgang(int VorgangID, int MitarbeiterID);

    /**
     * moves a Vorgang in a new Spalte
     * @param VorgangID identifing the Vorgang
     * @param SpaltenID identifing the old Spalte
     * @param NewSpaltenID identifing the new Spalte
     */
    void editVorgangArray(int VorgangID, int SpaltenID, int NewSpaltenID);
}
