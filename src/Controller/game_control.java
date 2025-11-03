package Controller;

import Model.map;
import Model.Chess;
import Model.move;
import View.display_map;
import View.display_interactive;


import java.io.IOException;
import java.util.*;

public class game_control {
    private map game_map;
    private movement mover;
    private display_map gameDisplay;
    private display_interactive displayInteractive;
    private int currentPlayer;
    private int[] take_back_counter;
    private Stack<move> tbc_stack;
    private int round_counter;

    public game_control() {
        this.game_map = new map();
        this.mover = new movement(game_map);
        this.gameDisplay = new display_map(game_map);
        this.displayInteractive = new display_interactive();
        this.currentPlayer = 1;
        this.take_back_counter = new int[2];
        this.tbc_stack = new Stack<>();
        this.round_counter = 1;
    }


    public void startGame(String[] playerNames, Scanner scanner , String record_choice) throws IOException {
        Records_file recorder=null;
        if (record_choice.equalsIgnoreCase("yes") ){
            recorder = new Records_file(playerNames[0], playerNames[1], record_choice);
        }

        gameDisplay.display_map();


        while (true) {
            // Display current player
            System.out.println(" player " + playerNames[currentPlayer - 1] + " round ");

            boolean move_made = processPlayerTurn(playerNames, scanner, recorder );

            // Display updated board
            if (move_made) {
                System.out.println("success move, updated map :");
                gameDisplay.display_map();

                // Check win condition
                if (check_win_condition()) {
                    System.out.println("player " + playerNames[currentPlayer - 1] + " win");
                    if (recorder != null) {
                        recorder.write_ending();
                    }
                    break;
                }

                // Switch to next player
                if (currentPlayer == 1) {
                    currentPlayer = 2;
                } else {
                    currentPlayer = 1;
                }
                round_counter++;
            }
        }
    }

    private boolean processPlayerTurn(String[] player_name, Scanner scanner, Records_file recorder) {
        String chess_input;
        String move_input;
        boolean move_made = false;

        // choose chess
        chessSelection:
        while (true) {
            System.out.print("Which chess you want to move ? (enter 'help' for get help) ");
            chess_input = scanner.nextLine().trim();

            if (chess_input.equalsIgnoreCase("exit")) {
                System.out.println("Game end without saving !");
                System.exit(0);
            } else if (chess_input.equalsIgnoreCase("help")) {
                display_interactive.display_help();
                continue;
            }
            else if (chess_input.equalsIgnoreCase("display")) {
                displayInteractive.display_game_status(game_map, player_name, currentPlayer);
            }

            else if (chess_input.equalsIgnoreCase("undo") && take_back_counter[currentPlayer-1] < 3){
                handle_undo(player_name, game_map);
                continue;
            }

            Chess c_chess = game_map.getChess_by_name(chess_input, currentPlayer);
            if (c_chess != null) {
                // choose move
                while (true) {
                    System.out.print("Which way you want to move ? (enter 'help' for get help) ");
                    move_input = scanner.nextLine().trim();

                    if (move_input.equalsIgnoreCase("back")) {
                        System.out.println("back to choose chess!");
                        break chessSelection;
                    }
                    if (move_input.equalsIgnoreCase("help")) {
                        display_interactive.display_help();
                        continue;
                    }

                    int[] target_location = calculate_move(c_chess.getRow(), c_chess.getColumn(), move_input);
                    if (target_location != null) {
                        Chess captured_chess = game_map.getChess(target_location[0], target_location[1]);
                        int c_row = c_chess.getRow();
                        int c_col = c_chess.getColumn();

                        if (mover.check_target_location(c_chess, target_location[0], target_location[1])) {
                            move_made = true;
                            move t_b_c = new move(c_chess, captured_chess, c_row, c_col, target_location[0], target_location[1],round_counter,player_name[currentPlayer-1],"success");
                            tbc_stack.push(t_b_c);
                            recorder.record_move(t_b_c);
                            break;
                        }
                    }
                    System.out.println("Wrong input: Please enter again (move) ");
                }
                break;
            } else {
                System.out.println("Wrong input: Please enter again (english name) ");
            }
        }

        return move_made;
    }

    private int[] calculate_move(int currentRow, int currentCol, String way) {
        int targetRow = currentRow;
        int targetCol = currentCol;

        switch (way.toLowerCase()) {
            case "up": targetRow = currentRow - 1; break;
            case "down": targetRow = currentRow + 1; break;
            case "left": targetCol = currentCol - 1; break;
            case "right": targetCol = currentCol + 1; break;
            default: return null;
        }
        return new int[]{targetRow, targetCol};
    }

    private boolean check_win_condition() {
        //player 1 go to den
        Chess player1AtDen = game_map.getChess(8, 3);
        if (player1AtDen != null && player1AtDen.getPlayer() == 1) {
            return true;
        }
        // player 2 go to den
        Chess player2AtDen = game_map.getChess(0, 3);
        if (player2AtDen != null && player2AtDen.getPlayer() == 2) {
            return true;
        }
        // player 1 no chess
        if(! game_map.getChess_by_player(1)){
            return true;
        }
        // player 2 no chess
        if (! game_map.getChess_by_player(2)){
            return true;
        }

        return false;
    }

    private boolean handle_undo(String[] playerNames, map game_map) {
        if (take_back_counter[currentPlayer-1] >= 3) {
            System.out.println("You have used all your chance to take back !");
            return false;
        }

        System.out.println("You still can take back " + (3 - take_back_counter[currentPlayer-1]) + " times");

        if (tbc_stack.size() >= 2) {
            move tbc=tbc_stack.pop();
            tbc.undo(game_map,playerNames[currentPlayer-1],round_counter);
            tbc=tbc_stack.pop();
            tbc.undo(game_map,playerNames[currentPlayer-1],round_counter);

            take_back_counter[currentPlayer-1] = take_back_counter[currentPlayer-1] + 1;
            gameDisplay.display_map();
            System.out.println("Player " + playerNames[currentPlayer - 1] + " round ");
            return true;
        } else {
            System.out.println("You cannot take back at the beginning");
            return false;
        }
    }

}

