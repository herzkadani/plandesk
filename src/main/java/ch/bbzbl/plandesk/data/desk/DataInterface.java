package ch.bbzbl.plandesk.data.desk;


import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

//split in kleinere Interfaces

public interface DataInterface {
    /**
     * creates a new Vorgang
     *
     * @param SpatenID identifing the Spalte of Vorgang
     */
    void createBugFix(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpatenID, String meldeTicket);
    /**
     * creates a new Vorgang
     *
     * @param SpatenID identifing the Spalte of Vorgang
     */
    void createNewFunction(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpatenID, boolean genemigt);
    /**
     * creates a new Vorgang
     *
     * @param SpatenID identifing the Spalte of Vorgang
     */

    void createVerbesserung(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterData> mitarbeiter, int SpatenID, String funktion);

    /**
     * deletes a Vorgang
     * @param ID identifing the Vorgang
     */
    void deleteVorgangByID(int ID);
    /**
     * changes a value of a Attribut of a Vorgang
     * @param VorgangID identifing the Vorgang
     * @param vorgangDto new Values of Attributes
     */
    void updateVorgang(int VorgangID, VorgangDto vorgangDto);

    /**
     * adds a Mitarbeiter to a Vorgang
     * @param VorgangID identifing the Vorgang
     * @param mitarbeiter all the Mitarbeiter
     */
    void setMitarbeiterOfVorgang(int VorgangID, ArrayList<MitarbeiterDto> mitarbeiter);

    /**
     * moves a Vorgang in a new Spalte
     * @param VorgangID identifing the Vorgang
     * @param NewSpaltenID identifing the new Spalte
     */
    void editVorgangArray(int VorgangID, int NewSpaltenID);
}
