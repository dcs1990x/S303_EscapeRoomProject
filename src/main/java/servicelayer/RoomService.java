package servicelayer;

import daomodel.DaoRoom;
import dtomodel.RoomDTO;
import model.Room;

public class RoomService {
    private final DaoRoom daoRoom = new DaoRoom();

    public RoomService() {}

    public List<RoomDTO> readAllRooms() throws Exception {
        daoRoom.readAllEntities();


    }

    public void insertRoom(Room room) throws Exception {
        daoRoom.insertRoom(room);
    }


}
