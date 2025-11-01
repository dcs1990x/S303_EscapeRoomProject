package menu;

import java.rmi.NoSuchObjectException;
import java.util.Scanner;

public class ManageERMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void manageEscapeRoom() throws NoSuchObjectException {
        checkEmptyEscapeRoomList();
        System.out.println("What do you want to do?");
        System.out.println("1. CREATE A ROOM" + System.lineSeparator() +
                "2. MODIFY ROOM" + System.lineSeparator() +
                "3. DELETE ROOM");
        byte option = scanner.nextByte();
        scanner.nextLine();
    }

    public void createRoom(){

    }

    public void modifyRoom(){

    }

    public void deleteRoom(){

    }
}