package menus;

import model.*;
import servicelayer.RoomService;

import java.rmi.NoSuchObjectException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ManageERMenu {

    private CreateManageDeleteERMenu createManageDeleteERMenu;
    private EscapeRoom escapeRoom;
    private RoomBuilderInterface roomBuilder;
    private RoomService roomService = new RoomService();

    public static void modifyRoomsMenu() {
        System.out.println("\nWhat do you want to do?\n");
        System.out.println("1. CREATE A ROOM" + System.lineSeparator() +
                "2. MODIFY ROOM" + System.lineSeparator() +
                "3. DELETE ROOM" + System.lineSeparator() +

                "0. GO BACK" + System.lineSeparator());
        //Falta leer habitaciones.
    }

    public void executeModifyRoomsMenuOption(){
        byte modifyRoomsMenuOption = -1;
        while (modifyRoomsMenuOption != 0){
            try{
                modifyRoomsMenuOption = UserInput.readByte("Please select an option: ");

                if (modifyRoomsMenuOption == 1){
                    Room room =  roomBuilder.setRoomName(UserInput.readLine("Elije el nombre de una hab"))
                            .setRoomDifficulty(Difficulty.VERY_EASY)
                            .setRoomTheme(Theme.SPACE)
                            .createRoom();

                    System.out.println("\nThe room was created successfully. ");
                    roomService.insertRoom(room);
                    modifyRoomsMenu();
                    //Persistir habitaciones creadas
                } else if (modifyRoomsMenuOption == 2){
                    //Modificar habitaciones

                } else if (modifyRoomsMenuOption == 3){
                    Optional<Room> selectedRoom = escapeRoom.getRoomByConsole();
                    Room roomToDelete = selectedRoom.get();
                    escapeRoom.deleteEscapeRoom(roomToDelete);
                    System.out.println("The escape room \"" + roomToDelete.getName() + "\" was deleted successfully. ");
                    modifyRoomsMenu();
                    executeModifyRoomsMenuOption();
                } else if (modifyRoomsMenuOption == 0){
                    createManageDeleteERMenu.showMainMenu();
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ime){
                System.out.println("Invalid input. ");
            } catch (NoSuchElementException nsee){
                System.out.println(nsee.getMessage());
                createManageDeleteERMenu.showMainMenu();
            } catch (NumberFormatException nfe){
                System.out.println("Invalid input. Please enter a number between 0 and 3: ");
                createManageDeleteERMenu.showMainMenu();
            } catch (NoSuchObjectException e) {
                System.out.println("A room with the entered name was not found. ");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void setRoomBuilder(RoomBuilderInterface roomBuilder) {
        this.roomBuilder = roomBuilder;
    }
}