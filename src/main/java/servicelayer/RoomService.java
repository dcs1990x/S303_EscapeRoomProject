package servicelayer;

import daomodel.DaoRoom;

public class RoomService {
    private final DaoRoom daoRoom = new DaoRoom();

    public RoomService() {}

    public void readAllRooms() throws Exception {
        daoRoom.readAllEntities();

    }
}
