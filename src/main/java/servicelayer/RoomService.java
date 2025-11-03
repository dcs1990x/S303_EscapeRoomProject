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
        daoRoom.insertEntity(room, 1);
    }

    public void updateRoom(Room room, int id) throws Exception {
        daoRoom.updateEntity(id, room);
    }

    public void deleteRoomById(long id) throws Exception {
        daoRoom.deleteEntity(id);
    }

}
