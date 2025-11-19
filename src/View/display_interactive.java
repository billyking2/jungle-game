package View;
import Model.map;
import Model.chess;

import java.util.*;

public class display_interactive {

    public static void display_game_menu() {
        System.out.println("=== The Jungle Game ===");
        System.out.println("1: New Game");
        System.out.println("2: Continue");
        System.out.println("3: Replay");
    }

    public static void display_name_menu() {
        System.out.println("What do you want to name the player ?");
        System.out.println("1: Default");
        System.out.println("2: Random");
        System.out.println("3: Named by yourself");
    }

    public static void display_record_menu(){
        System.out.println("Enter the record filename to replay :");
    }

    public static String setup_recording(Scanner scanner) {
        System.out.println("Do you want to record this match?(yes/no) ");
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("please name the record ");
            return scanner.nextLine().trim();
        }
        return null;
    }

    public static void display_help() {
        System.out.println("\n--- Help ---");
        System.out.println(" chess: 鼠(rat), 貓(cat), 狗(dog), 狼(wolf), 豹(leopard), 虎(tiger), 獅(lion), 象(elephant)");
        System.out.println(" way: up, down, left, right");
        System.out.println(" red: your chess");
        System.out.println(" blue: other player chess");
        System.out.println(" green '阱': Trap");
        System.out.println(" yellow '穴': Den");
        System.out.println(" purple '水': river");
        System.out.println(" victory conditions: move a piece onto the den on the opponent's side of the board or capture all the opponent's pieces");
        System.out.println(" special command: exit, back, display, undo, save \n");
    }


    public void display_game_status(map gameMap, String[] player_name, int currentPlayer) {
        display_map gameDisplay = new display_map(gameMap);
        gameDisplay.display_map();

        ArrayList<String> player1_chess = new ArrayList<>();
        ArrayList<String> player2_chess = new ArrayList<>();

        //get all the chess from map
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 7; col++) {
                chess chess = gameMap.getChess(row, col);
                //Classification the class to player
                if (chess != null) {
                    switch (chess.getPlayer()) {
                        case 1 -> player1_chess.add(chess.getType());
                        case 2 -> player2_chess.add(chess.getType());
                    }
                }
            }
        }

        player1_chess.sort(Comparator.naturalOrder());
        player2_chess.sort(Comparator.naturalOrder());

        System.out.println("player " + player_name[0] + " chess : " + player1_chess);
        System.out.println("player " + player_name[1] + " chess : " + player2_chess);

        int next_player = (currentPlayer == 1 ? 2 : 1);
        System.out.println("the next player to make a move : " + player_name[next_player - 1]);
    }


}
