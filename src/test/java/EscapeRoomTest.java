import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.rmi.NoSuchObjectException;
import java.util.Optional;
import java.util.Scanner;
import static model.Difficulty.*;
import static model.Theme.*;
import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomTest {

    private EscapeRoomManager escapeRoomManager;
    private RoomBuilder roomBuilder;
    private Room room;
    private Theme theme;
    private Clue clue;
    private Decoration decoration;
    private Difficulty difficulty;

    @BeforeEach
    void setup(){
        EscapeRoomManager escapeRoomManager = EscapeRoomManager.getInstance();
        this.escapeRoomManager = escapeRoomManager;
    }

    @Test
    void givenInputUser_whenCreatingAnEscapeRoom_thenReturnEscapeRoom() {
        String simulatedInput = "La Casa de Papel\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        EscapeRoom escapeRoom = escapeRoomManager.createEscapeRoom();
        assertNotNull(escapeRoom);
        assertEquals("La Casa de Papel", escapeRoom.getEscapeRoomName());
        assertEquals(1, escapeRoomManager.getEscapeRoomsList().size());
    }

    @Test
    void givenAScapeRoomName_whenLookingForAEscapeRoom_thenGetThatEscapeRoom() throws NoSuchObjectException {
        String simulatedInput = "La Casa de Papel\nLa Casa de Papel\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));

        EscapeRoom escapeRoom = escapeRoomManager.createEscapeRoom();
        Optional<EscapeRoom> escapeRoomGot = escapeRoomManager.getEscapeRoomByConsole();
        assertEquals(escapeRoom, escapeRoomGot.get());
    }

    @Test
    void givenARoomBuilder_whenCreatingARoom_thenRoomIsNotNull(){
        Room room1 = new Room();
        Clue clue = new Clue("piece of paper", "number code", HORROR, 5, true, false);
        Decoration decoration = new Decoration("spider web", HORROR);
        room1.addClue(clue);
        room1.addDecoration(decoration);

        RoomBuilder roomBuilder = new RoomBuilder();
        assertAll(() -> assertNotNull(roomBuilder.setRoomTheme(HORROR).toString()),
                () -> assertNotNull(roomBuilder.setRoomDifficulty(VERY_HARD)),
                () -> assertNotNull(roomBuilder.addRoomClue(clue)),
                () -> assertNotNull(roomBuilder.addRoomDecoration(decoration)),
                () -> assertNotNull(roomBuilder.createRoom()));
    }
}