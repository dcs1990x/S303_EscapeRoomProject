package model;

import java.rmi.NoSuchObjectException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static EscapeRoomManager escapeRoomManager;

    public static void showInititalScreen(){
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
        while (option != 0){
            try{
                option = scanner.nextByte();
                scanner.nextLine();

                if (option == 1){
                    //createEscapeRoom();
                } else if (option == 2){
                    checkEmptyEscapeRoom();

                    //manageEscapeRoom();
                } else if (option == 3){
                    checkEmptyEscapeRoom();

                    //deleteEscapeRoom();
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

    public static void checkEmptyEscapeRoom() throws NoSuchObjectException{
        if (escapeRoomManager.getRooms() == null || escapeRoomManager.getRooms().isEmpty()){
            throw new NoSuchObjectException("There are no previously created escape rooms. ");
        }
    }

    public Room getEscapeRoomByIndex(int index){
        for (int i = 0; i < escapeRoomManager.getRooms().size(); i++){
            if (escapeRoomManager.getRooms().get(i) == index){

            }
        }
    }
}