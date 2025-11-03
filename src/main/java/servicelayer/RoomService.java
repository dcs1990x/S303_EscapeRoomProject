package servicelayer;

import daomodel.DaoRoom;
import model.Room;

public class RoomService {
    private final DaoRoom daoRoom = new DaoRoom();

    public RoomService() {}

    public void readAllRooms() throws Exception {
        daoRoom.readAllEntities();

    }

    public void insertRoom(Room room) throws Exception {
        daoRoom.insertRoom(room);
    }


}
