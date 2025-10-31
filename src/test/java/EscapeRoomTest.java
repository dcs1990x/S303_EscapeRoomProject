import model.*;
import org.junit.jupiter.api.Test;
import static model.Difficulty.*;
import static model.Theme.*;
import static org.junit.jupiter.api.Assertions.*;

public class EscapeRoomTest {

    private RoomBuilder roomBuilder;
    private Room room;
    private Theme theme;
    private Clue clue;
    private Decoration decoration;
    private Difficulty difficulty;

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