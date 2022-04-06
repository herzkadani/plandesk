package ch.bbzbl.plandesk.data.desk;


import ch.bbzbl.plandesk.dto.desk.BoardDto;
import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import ch.bbzbl.plandesk.dto.desk.SpaltenDto;
import ch.bbzbl.plandesk.dto.desk.VorgangDto;

import java.util.ArrayList;

//TODO split in kleinere Interfaces

public interface DataInterface {
    /**
     * creates a BugFix
     * @param name attributes of a Vorgang
     * @param beschreibung attributes of a Vorgang
     * @param dringlichkeit attributes of a Vorgang
     * @param mitarbeiter attributes of a Vorgang
     * @param SpatenID attributes of a Vorgang
     * @param meldeTicket attributes of a Vorgang
     */
    void createBugFix(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpatenID, String meldeTicket);

    /**
     * creates a new Function
     * @param name attributes of a Vorgang
     * @param beschreibung attributes of a Vorgang
     * @param dringlichkeit attributes of a Vorgang
     * @param mitarbeiter attributes of a Vorgang
     * @param SpatenID attributes of a Vorgang
     * @param allowed attributes of a Vorgang
     */
    void createNewFunction(String name, String beschreibung,String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpatenID, boolean allowed);

    /**
     * creates a new Improvment
     * @param name attributs of a Vorgang
     * @param description attributs of a Vorgang
     * @param dringlichkeit attributs of a Vorgang
     * @param mitarbeiter attributs of a Vorgang
     * @param SpatenID attributs of a Vorgang
     * @param function  attributs of a Vorgang
     */
    void createImprovement(String name, String description, String dringlichkeit, ArrayList<MitarbeiterDto> mitarbeiter, int SpatenID, String function);

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
     * moves a Vorgang in a new Column
     * @param VorgangID identifing the Vorgang
     * @param NewSpaltenID identifing the new Spalte
     */
    void editVorgangArray(int VorgangID, int NewSpaltenID);

    /**
     * returns all the Info on the Board
     * @return is a copy of the data of the board
     */
    BoardDto getBoard();

    /**
     * returnns Vorgang by a given ID
     * @param VorgangID identifeies the Vorgang
     * @return a Dto of the Info of a Vorgang
     */
    VorgangDto getVorgangByID(int VorgangID);

    /**
     * initializes a Board with Name and Columns
     */
    void initBoard();

    /**
     * creates a new Mitarbeiter
     * @param vorname vorname
     * @param nachname nachname
     */
    void createNewMitarbeiter(String vorname, String nachname);

    /**
     * retunrs a Spalte by ginven ID
     * @param SpaltenId idenfifies the Column
     * @return a Dto of a Columns Data
     */
    SpaltenData getSpalteByID(int SpaltenId);

    /**
     * returns a Column by given VorgangId
     * @param VorgangID spezifes a Column with the ID
     * @return returns a Dto of the Data of a Column
     */
    SpaltenDto getSpalteByVorgangID(int VorgangID);
}
