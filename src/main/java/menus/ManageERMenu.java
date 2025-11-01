package menus;

import model.EscapeRoom;
import model.Room;
import model.RoomBuilderInterface;

import java.rmi.NoSuchObjectException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import static model.EscapeRoom.*;

public class ManageERMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private CreateManageDeleteERMenu createManageDeleteERMenu;
    private EscapeRoom escapeRoom;
    private RoomBuilderInterface roomBuilder;

    public static void modifyRoomsMenu() {
        System.out.println("\nWhat do you want to do?\n");
        System.out.println("1. CREATE A ROOM" + System.lineSeparator() +
                "2. MODIFY ROOM" + System.lineSeparator() +
                "3. DELETE ROOM" + System.lineSeparator() +
                "0. GO BACK" + System.lineSeparator());
        System.out.print("Please select an option: ");
    }

    public void executeModifyRoomsMenuOption(){
        byte modifyRoomsMenuOption = -1;
        while (modifyRoomsMenuOption != 0){
            try{
                modifyRoomsMenuOption = scanner.nextByte();
                scanner.nextLine();

                if (modifyRoomsMenuOption == 1){
                    createRoom(this.roomBuilder);
                    System.out.println("\nThe room was created successfully. ");
                    modifyRoomsMenu();
                } else if (modifyRoomsMenuOption == 2){

                } else if (modifyRoomsMenuOption == 3){
                    Optional<Room> selectedRoom = escapeRoom.getRoomByConsole();
                    Room roomToDelete = selectedRoom.get();
                    escapeRoom.deleteEscapeRoom(roomToDelete);
                    System.out.println("The escape room \"" + roomToDelete.getName() + "\" has been deleted successfully. ");
                } else if (modifyRoomsMenuOption == 0){
                    CreateManageDeleteERMenu.executeMainMenuOption();
                } else {
                    throw new InputMismatchException("\nInvalid option. \n");
                }
            } catch (InputMismatchException ime){
                System.out.println(ime.getMessage());
            } catch (NoSuchObjectException nsoe) {
                System.out.println(nsoe.getMessage());
            }
        }
    }
}