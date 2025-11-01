package menu;

import model.EscapeRoom;
import model.EscapeRoomManager;

import java.rmi.NoSuchObjectException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static EscapeRoomManager escapeRoomManager;

    public static void showInitialScreen(){
        System.out.println("\n<========WELCOME TO THE ESCAPE ROOM MANAGER APP========>\n");
    }

    public static void showMainMenu(){
        System.out.println("1. CREATE ESCAPE ROOM" + System.lineSeparator() +
                "2. MANAGE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "3. DELETE EXISTING ESCAPE ROOM" + System.lineSeparator() +
                "0. EXIT THE PROGRAMME\n");
        System.out.print("Please select an option: ");
    }

    public static void executeMenuOption(){
        byte option = -1;


        EscapeRoomManager escapeRoomManager = EscapeRoomManager.getInstance();//Faltaba instanciar el manager.


        while (option != 0){
            try{
                option = scanner.nextByte();
                scanner.nextLine();

                if (option == 1){
                    EscapeRoom escapeRoom = escapeRoomManager.createEscapeRoom();
                    System.out.println("The escape room \"" + escapeRoom.getEscapeRoomName() + "\" has been created successfully. ");
                } else if (option == 2){
                    escapeRoomManager.getEscapeRoomByConsole();
                    //manageEscapeRoom();
                } else if (option == 3){
                    Optional<EscapeRoom> escapeRoomToDelete = escapeRoomManager.getEscapeRoomByConsole();
                    escapeRoomManager.deleteEscapeRoom(escapeRoomToDelete);
                    System.out.println("The escape room \"" +/* escapeRoomToDelete.getEscapeRoomName() +*/ "\" has been deleted successfully. ");
                } else if (option == 0){
                    System.out.println("\nYou exited the programme. \n");
                    return;
                } else {
                    throw new InputMismatchException("Invalid option. ");
                }
            } catch (InputMismatchException ime){
                System.out.println(ime.getMessage());
            } catch (NoSuchObjectException nsoe) {
                System.out.println(nsoe.getMessage());
            }
        }
    }
}