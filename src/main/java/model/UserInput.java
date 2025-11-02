package model;

import java.util.Scanner;

public final class UserInput {

    private static final Scanner SCANNER = new Scanner(System.in);

    private UserInput() {}

    public static String readLine(String message) {
        System.out.print(message);
        return SCANNER.nextLine().trim();
    }

    public static int readInt(String message) {
        while (true) {
            System.out.print(message);
            try {
                String input = SCANNER.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number. ");
            }
        }
    }

    public static byte readByte(String message) {
        while (true) {
            System.out.print(message);
            try {
                String input = SCANNER.nextLine().trim();
                return Byte.parseByte(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 0 and 3. ");
            }
        }
    }

    public static void close() {
        SCANNER.close();
    }
}