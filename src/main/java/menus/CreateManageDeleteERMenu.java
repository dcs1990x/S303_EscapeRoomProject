package menus;

import daomodel.DaoEscapeRoom;
import model.EscapeRoom;
import model.EscapeRoomManager;
import model.RoomBuilderInterface;
import model.UserInput;

import java.util.*;

import static menus.ManageERMenu.modifyRoomsMenu;

public class CreateManageDeleteERMenu {

    private ManageERMenu manageERMenu = new ManageERMenu();
    private final ItemMenu itemMenu = new ItemMenu();
    private final MenuEliminacionLois menuEliminacionLois = new MenuEliminacionLois(); // ver nota de renombre
    private final MenuLecturaLois menuLecturaLois = new MenuLecturaLois();
    private final ClueMenu clueMenu = new ClueMenu();
    private final InventoryMenu inventoryMenu = new InventoryMenu();
    private final DaoEscapeRoom escapeRoomService = new DaoEscapeRoom();

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

    public void executeMainMenuOption() {
        byte mainMenuOption = -1;
        while (mainMenuOption != 0) {
            try {
                mainMenuOption = UserInput.readByte("Please select an option: ");

                if (mainMenuOption == 1) {
                    String name = UserInput.readLine("\nPlease write the escape room's name: ");
                    escapeRoomService.insertEntity(new EscapeRoom(name));
                    System.out.println("The escape room \"" + name + "\" was created successfully. ");
                    //Llamar a service Esaperoom para pasar a DB.
                    showMainMenu();
                } else if (mainMenuOption == 2) {
                    Optional<EscapeRoom> selectedEscapeRoom;
                    selectedEscapeRoom = getEscapeRoomByConsole();
                    //Mostrar escapeRoom.

                    if (selectedEscapeRoom.isEmpty()) {
                        System.out.println("The selected EscapeRoom was not correclty retrived from DB, start again from the menu.");
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
                    escapeRoomService.deleteEntity(selectedEscapeRoom.get().getIdEscapeRoom());
                    System.out.println("The escape room \"" + selectedEscapeRoom.get().getEscapeRoomName() + "\" was deleted successfully. ");
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
                } else if (mainMenuOption == 6) {
                    inventoryMenu.optionSelector();
                    showMainMenu();
                    executeMainMenuOption();

                } else if (mainMenuOption == 0) {
                    System.out.println("\nYou exited the programme. \n");
                    return;
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. ");
            } catch (NoSuchElementException nsee) {
                System.out.println(nsee.getMessage());
                showMainMenu();
                executeMainMenuOption();
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Please enter a number between 0 and 5: ");
                showMainMenu();
                executeMainMenuOption();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

        public Optional<EscapeRoom> getEscapeRoomByConsole () throws Exception {
            List<EscapeRoom> escapeRooms = escapeRoomService.readAllEntities();
            if (escapeRoomService.readAllEntities().toString() == null) {
                System.out.println("We don't have any EscapeRooms yet, please create one EscapeRoom first.");
                showMainMenu();
                executeMainMenuOption();
                return null;
            } else {

                System.out.println("These are the existing escape rooms: \n");
                System.out.println(escapeRoomService.readAllEntities().toString());
                String name = UserInput.readLine("\nPlease type the escape room's name or type \"Return\" to go back: ");

                for (EscapeRoom escapeRoom : escapeRooms) {
                    if (name.equalsIgnoreCase("return")) {
                        return Optional.empty();
                    }
                    if (name.equalsIgnoreCase(escapeRoom.getEscapeRoomName())) {
                        return Optional.of(escapeRoom);
                    } else {
                        return Optional.empty();
                    }
                }
                throw new NoSuchElementException("No escape room found with the name \"" + name + "\".");
            }
        }

        public void setRoomBuilder (RoomBuilderInterface roomBuilder){
            manageERMenu.setRoomBuilder(roomBuilder);
        }
    }
