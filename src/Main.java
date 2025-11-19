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
        while (true) {
        display_interactive.display_game_menu();
        String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                String[] playerNames = player_name.setupPlayerNames(scanner);

                game_control gameController = new game_control();
                    String record_name = display_interactive.setup_recording(scanner);
                try {
                    gameController.startGame(playerNames, scanner, record_name, null, false);
                    break;
                }   catch (IOException e ){
                    System.out.println("RECORD file already exists or cannot be created: " + record_name);
                    continue;
                }
            }

            if (choice.equals("2")) {
                System.out.println("Enter the jungle filename to continue :");
                String jungle_file_name = scanner.nextLine().trim();
                try {
                    game_control gameController = new game_control();
                    display_jungle.start_jungle(jungle_file_name);
                    gameController.continue_game(scanner, jungle_file_name);
                    break;
                }   catch (IOException e){
                    System.out.println("JUNGLE file already exists or cannot be created: " + jungle_file_name);
                    continue;
                }
            }

            if (choice.equals("3")) {
                display_interactive.display_record_menu();
                String replayFile = scanner.nextLine().trim();
                
                try {
                    display_replay.start_replay(replayFile);
                    break;
                } catch (Exception e) {
                    System.out.println("Error loading replay: " + e.getMessage());
                }
                
            }
            else{
                System.out.println("Invalid choice, please choose again");

            }
        }
        scanner.close();
    }

}
