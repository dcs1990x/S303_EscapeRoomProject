package menus;

import daomodel.DaoEscapeRoom;
import model.EscapeRoom;
import model.RoomBuilderInterface;
import model.UserInput;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Optional;

import static menus.ManageERMenu.modifyRoomsMenu;

public class CreateManageDeleteERMenu {


    private ManageERMenu manageERMenu = new ManageERMenu();
    private final ItemMenu itemMenu = new ItemMenu();
    private final MenuEliminacionLois menuEliminacionLois = new MenuEliminacionLois(); // ver nota de renombre
    private final MenuLecturaLois menuLecturaLois = new MenuLecturaLois();
    private final ClueMenu clueMenu = new ClueMenu();
    private final InventoryMenu inventoryMenu = new InventoryMenu();
    DaoEscapeRoom daoEscapeRoom = new DaoEscapeRoom();

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
        byte mainMenuOption = -1;
        while (mainMenuOption != 0) {
            try {
                mainMenuOption = UserInput.readByte("Please select an option: ");

                if (mainMenuOption == 1) {
                    EscapeRoom escapeRoom = new EscapeRoom(UserInput.readLine("Please insert the name of the EscapeRoom"));
                    System.out.println("The escape room \"" + escapeRoom.getEscapeRoomName() + "\" was created successfully. ");
                    daoEscapeRoom.insertEntity(escapeRoom);
                    //Llamar a service Esaperoom para pasar a DB.
                    showMainMenu();
                } else if (mainMenuOption == 2) {
                    Optional<EscapeRoom> selectedEscapeRoom;
                    selectedEscapeRoom = getEscapeRoomByConsole();
                    //Mostrar escapeRoom.

                    if (selectedEscapeRoom.isEmpty()) {
                        showMainMenu();
                        executeMainMenuOption();
                    } else if (selectedEscapeRoom.isPresent()) {
                        modifyRoomsMenu();
                        manageERMenu.executeModifyRoomsMenuOption();
                        //Aquí nos vamos a las opciones CRUD
                    } else {
                        throw new InputMismatchException();
                    }
                } else if (mainMenuOption == 3) {
                    Optional<EscapeRoom> selectedEscapeRoom = getEscapeRoomByConsole();
                    EscapeRoom escapeRoomToDelete = selectedEscapeRoom.get();
                    daoEscapeRoom.deleteEntity(escapeRoomToDelete.getIdEscapeRoom());
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
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void setRoomBuilder(RoomBuilderInterface roomBuilder) {
        manageERMenu.setRoomBuilder(roomBuilder);
    }
    public Optional<EscapeRoom> getEscapeRoomByConsole() throws Exception {

        int id = UserInput.readInt("\nPlease type the escape room's id or type \"Return\" to go back: ");
        String name = UserInput.readLine("\nPlease type the escape room's name or type \"Return\" to go back: ");

            if (name.equalsIgnoreCase("return")|| id == 0) {
                return Optional.empty();
            }
            else{
                return Optional.of(new EscapeRoom(id,name));
            }
    }
}