import Controller.player_name;
import Controller.game_control;
import View.display_interactive;
import View.display_replay;
import View.display_jungle;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        display_interactive.display_game_menu();
        String choice = scanner.nextLine().trim();

        if (choice.equals("1")) {
            String[] playerNames = player_name.setupPlayerNames(scanner);

            game_control gameController = new game_control();

            String record_name = display_interactive.setup_recording(scanner);
                gameController.startGame(playerNames, scanner , record_name);

        }

        if (choice.equals("2")){
            System.out.println("Enter the jungle filename to continue :");
            String jungle_file = scanner.nextLine().trim();
            game_control gameController = new game_control();
            display_jungle display_jungler=display_jungle.start_jungle(jungle_file);

            gameController.continue_game(display_jungler,scanner,jungle_file);
        }

        if(choice.equals("3")){
            display_interactive.display_record_menu();
            String replayFile = scanner.nextLine().trim();

            try {
                display_replay.start_replay(replayFile);
            } catch (Exception e) {
                System.out.println("Error loading replay: " + e.getMessage());
            }
        }
        scanner.close();
    }


}