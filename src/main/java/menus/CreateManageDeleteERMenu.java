package menus;

import model.EscapeRoom;
import model.EscapeRoomManager;
import model.UserInput;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import static menus.ManageERMenu.modifyRoomsMenu;

public class CreateManageDeleteERMenu {

    private static final EscapeRoomManager escapeRoomManager = EscapeRoomManager.getInstance();
    private static ManageERMenu manageERMenu;

    public static void showWelcomeMessage(){
        System.out.println("\n<========WELCOME TO THE ESCAPE ROOM MANAGER APP========>");
    }

    public static void showMainMenu(){
        System.out.println("\n1. CREATE ESCAPE ROOM" + System.lineSeparator() +
                "2. MANAGE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "3. DELETE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "0. EXIT THE PROGRAMME" + System.lineSeparator());
    }

    public static void executeMainMenuOption(){
        byte mainMenuOption = -1;
        while (mainMenuOption != 0) {
            try {
                mainMenuOption = UserInput.readByte("Please select an option: ");

                if (mainMenuOption == 1) {
                    EscapeRoom escapeRoom = escapeRoomManager.createEscapeRoom();
                    System.out.println("The escape room \"" + escapeRoom.getEscapeRoomName() + "\" was created successfully. ");
                    showMainMenu();
                } else if (mainMenuOption == 2) {
                    Optional<EscapeRoom> selectedEscapeRoom = null;
                    selectedEscapeRoom = escapeRoomManager.getEscapeRoomByConsole();

                    if (selectedEscapeRoom.isEmpty()) {
                        showMainMenu();
                    } else if (selectedEscapeRoom.isPresent()) {
                        modifyRoomsMenu();
                    } else {
                        throw new InputMismatchException();
                    }
                } else if (mainMenuOption == 3) {
                    Optional<EscapeRoom> selectedEscapeRoom = escapeRoomManager.getEscapeRoomByConsole();
                    EscapeRoom escapeRoomToDelete = selectedEscapeRoom.get();
                    escapeRoomManager.deleteEscapeRoom(escapeRoomToDelete);
                    System.out.println("The escape room \"" + escapeRoomToDelete.getEscapeRoomName() + "\" was deleted successfully. ");
                    showMainMenu();
                } else if (mainMenuOption == 0) {
                    System.out.println("\nYou exited the programme. \n");
                    return;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ime){
                System.out.println("Invalid input. ");
            } catch (NoSuchElementException nsee){
                System.out.println(nsee.getMessage());
                showMainMenu();
            } catch (NumberFormatException nfe){
                System.out.println("Invalid input. Please enter a number between 0 and 3: ");
                showMainMenu();
            }
        }
    }
}