package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.dto.desk.VorgangDto;

public interface DataInterface {
    /**
     * creates a new Vorgang
     * @param Type sets the type of the Vorgang
     */
    void createVorgang(String Type);

    /**
     * deletes a Vorgang
     * @param ID identifing the Vorgang
     */
    void deleteVorgangByID(int ID);
    void editVorgang(String Attribut);
    void setMitarbeiterOfVorgang(int VorgangID, int MitarbeiterID);
    void editVorgangArray(int VorgangID, int SpaltenID);
}
