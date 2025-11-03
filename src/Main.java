import Controller.player_name;
import Controller.game_control;
import Controller.player_name;
import View.display_interactive;
import View.display_replay;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        display_interactive.display_game_menu();
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            String[] playerNames = player_name.setupPlayerNames(scanner);

            game_control gameController = new game_control();

            String record_choice = display_interactive.setup_recording(scanner);

            gameController.startGame(playerNames, scanner , record_choice);
        }

        if (choice.equals("2")){

        }

        if(choice.equals("3")){
            display_interactive.display_record_menu();
            String replayFile = scanner.nextLine().trim();

            try {
                display_replay.startReplay(replayFile);
            } catch (Exception e) {
                System.out.println("Error loading replay: " + e.getMessage());
            }
        }
        scanner.close();
    }


}