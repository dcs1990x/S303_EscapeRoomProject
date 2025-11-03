package menus;

import model.EscapeRoom;
import model.EscapeRoomManager;
import model.RoomBuilderInterface;
import model.UserInput;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

import static menus.ManageERMenu.modifyRoomsMenu;

public class CreateManageDeleteERMenu {

    private static final EscapeRoomManager escapeRoomManager = EscapeRoomManager.getInstance();
    private ManageERMenu manageERMenu = new ManageERMenu();
    private final ItemMenu itemMenu = new ItemMenu();
    private final MenuEliminacionLois menuEliminacionLois = new MenuEliminacionLois(); // ver nota de renombre
    private final MenuLecturaLois menuLecturaLois = new MenuLecturaLois();
    private final ClueMenu clueMenu = new ClueMenu();
    private final InventoryMenu inventoryMenu = new InventoryMenu();

    public void showWelcomeMessage(){
        System.out.println("\n<========WELCOME TO THE ESCAPE ROOM MANAGER APP========>");
    }

    public void showMainMenu(){
        System.out.println("\n=== ESCAPE ROOM MANAGEMENT SYSTEM ===" + System.lineSeparator() +
                "1. CREATE ESCAPE ROOM" + System.lineSeparator() +
                "2. MANAGE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "3. DELETE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "4. MANAGE ITEMS" + System.lineSeparator() +
                "5. MANAGE CLUES" + System.lineSeparator() +
                "6. MANAGE INVENTORY" + System.lineSeparator() +
                "0. EXIT THE PROGRAMME" + System.lineSeparator());
    }

    public void executeMainMenuOption(){
        Scanner sc = new Scanner(System.in);
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
                        executeMainMenuOption();
                    } else if (selectedEscapeRoom.isPresent()) {
                        modifyRoomsMenu();
                        manageERMenu.executeModifyRoomsMenuOption();
                    } else {
                        throw new InputMismatchException();
                    }
                } else if (mainMenuOption == 3) {
                    Optional<EscapeRoom> selectedEscapeRoom = escapeRoomManager.getEscapeRoomByConsole();
                    EscapeRoom escapeRoomToDelete = selectedEscapeRoom.get();
                    escapeRoomManager.deleteEscapeRoom(Optional.of(escapeRoomToDelete));
                    System.out.println("The escape room \"" + escapeRoomToDelete.getEscapeRoomName() + "\" was deleted successfully. ");
                    showMainMenu();
                    executeMainMenuOption();
                }
                // === INTEGRACIÓN LOIS ===
             else if (mainMenuOption == 4) {
                itemMenu.optionSelector();
                showMainMenu();
                executeMainMenuOption();

            } else if (mainMenuOption == 5) {
                    clueMenu.optionSelector();
                    showMainMenu();
                    executeMainMenuOption();
             }
                else if (mainMenuOption == 6) {
                    inventoryMenu.optionSelector();
                    showMainMenu();
                    executeMainMenuOption();

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
                executeMainMenuOption();
            } catch (NumberFormatException nfe){
                System.out.println("Invalid input. Please enter a number between 0 and 5: ");
                showMainMenu();
                executeMainMenuOption();
            }
        }
    }

    public void setRoomBuilder(RoomBuilderInterface roomBuilder) {
        manageERMenu.setRoomBuilder(roomBuilder);
    }
}