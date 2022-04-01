package ch.bbzbl.plandesk.data.desk;

import ch.bbzbl.plandesk.dto.desk.MitarbeiterDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryDataTest {
    InMemoryData inMemoryData = new InMemoryData();

    @BeforeEach
    void setUp() {
        inMemoryData.initBoard();
    }

    @Test
    void createBugFix() {

        ArrayList<MitarbeiterDto> mitarbeiterDto = new ArrayList<>();
        mitarbeiterDto.add(new MitarbeiterDto(5,"Iven", "Kuder"));

        inMemoryData.createBugFix("Gutscheincode nicht erkannt",
                "Gutschein wird beim Bezahlen als Falsch erkannt und nicht eingelöst",
                "Low",
                mitarbeiterDto,
                2,
                "EST-2012"
                );

        inMemoryData.getBoard().getSpalten().get(2).getVorgaenge().get(0).getID();
        assertTrue(inMemoryData.getBoard().getSpalten().get(2).getVorgaenge().get(0).getID()==0);
    }

    @Test
    void createNewFunction() {
    }

    @Test
    void createVerbesserung() {
    }

    @Test
    void deleteVorgangByID() {
    }

    @Test
    void updateVorgang() {
    }

    @Test
    void setMitarbeiterOfVorgang() {
    }

    @Test
    void editVorgangArray() {
    }

    @Test
    void getBoard() {
    }

    @Test
    void getVorgangByID() {
    }

    @Test
    void initBoard() {
    }

    @Test
    void createMitarbeiter() {
    }

    @Test
    void getSpalteByID() {
    }
}