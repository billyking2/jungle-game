package Controller;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;
import View.display_interactive;

public class player_name {


    public static String[] setupPlayerNames(Scanner scanner) {
        String[] playerNames = new String[2];
        String choice;

        name_loop:
        while (true) {
            display_interactive.display_name_menu();
            choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    playerNames[0] = "1";
                    playerNames[1] = "2";
                    break name_loop;
                case "2":
                    playerNames[0] = random_name();
                    playerNames[1] = random_name();
                    break name_loop;
                case "3":
                    System.out.println("Please enter player 1 name");
                    playerNames[0] = scanner.nextLine().trim();
                    System.out.println("Please enter player 2 name");
                    playerNames[1] = scanner.nextLine().trim();
                    break name_loop;
                default:
                    System.out.println("Wrong input, enter again(1,2,3)");
                    break;
            }
        }

        return playerNames;
    }

    public static String random_name(){
        byte[] array = new byte[5];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
}

