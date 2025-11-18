package Controller;

import Model.map;
import Model.chess;
import Model.move;
import View.display_jungle;
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
    private List<move> jungle_list;


    public game_control() {
        this.game_map = new map();
        this.mover = new movement(game_map);
        this.gameDisplay = new display_map(game_map);
        this.displayInteractive = new display_interactive();
        this.currentPlayer = 1;
        this.take_back_counter = new int[2];
        this.tbc_stack = new Stack<>();
        this.round_counter = 1;
        this.jungle_list = new ArrayList<>();
    }

    public void continue_game( Scanner scanner, String jungle_file_name) throws IOException, InterruptedException {
        game_file jungle_manager = new game_file(jungle_file_name, game_file.FileType.JUNGLE);
        // Use display_jungle to load and set up the game state
        display_jungle jungleDisplay = loadGameState(jungle_file_name);

        // Copy the loaded state to game_control
        this.game_map = jungleDisplay.getGame_map();
        this.gameDisplay = new display_map(this.game_map); // Update display with loaded map
        this.mover = new movement(this.game_map);

        // Load other game state
        String[] player_name ={jungle_manager.getPlayer1_name(), jungle_manager.getPlayer2_name()};
        this.take_back_counter=jungle_manager.getTakeBackCounters();
        List<move> allMoves = jungle_manager.get_moves();

        if (!allMoves.isEmpty()) {
            move latestMove = allMoves.getLast();

            if (latestMove.getResult().equals("undo")) {
                if (latestMove.getRound() % 2 == 0) {
                    this.currentPlayer = 1;
                } else {
                    this.currentPlayer = 2;
                }
            }
            // Rebuild stacks
            rebuildStacks(allMoves);

            System.out.println("Game loaded successfully! Continuing from round " + round_counter);

            String record_name = display_interactive.setup_recording(scanner);
            // Continue the game
            startGame(player_name, scanner, record_name, jungle_file_name, true);
        }
    }
    private void rebuildStacks(List<move> moves) {
        this.tbc_stack.clear();
        this.jungle_list.clear();

        // rebuild stacks from loaded moves
        for (move m : moves) {
            // set the chess pieces using the current game board
            chess movedChess = this.game_map.getChess(m.getFromRow(), m.getFromCol());
            chess capturedChess = this.game_map.getChess(m.getToRow(), m.getToCol());

            m.setMoved_chess(movedChess);
            m.setCaptured_chess(capturedChess);

            if(m.getResult().equals("success")) {
                tbc_stack.push(m);
            }
            jungle_list.add(m);
        }
    }


    private display_jungle loadGameState(String filename) throws IOException, InterruptedException {
        game_file jungleManager = new game_file(filename, game_file.FileType.JUNGLE);
        List<move> moves = jungleManager.get_moves();
        map game_map = new map();
        display_map game_display = new display_map(game_map);
        display_jungle jungleDisplay = new display_jungle(game_map, game_display, moves);

        // This applies all the moves to reconstruct the board
        jungleDisplay.continue_game();

        return jungleDisplay;
    }

    public void startGame(String[] player_name, Scanner scanner , String record_name,String jungle_file_name, boolean continue_record) throws IOException {
        game_file recorder=null;
        if (record_name != null && !continue_record){
            recorder =  new game_file(player_name[0], player_name[1], record_name, game_file.FileType.RECORD, take_back_counter);
        }
        else if(record_name!=null && jungle_file_name !=null){
            recorder = game_file.create_record_from_jungle( jungle_file_name , record_name);
        }

        gameDisplay.display_map();


        while (true) {
            // Display current player

            System.out.println(" player " + player_name[currentPlayer - 1] + " round ");

            boolean move_made = processPlayerTurn(player_name, scanner, recorder );

            // Display updated board
            if (move_made) {
                System.out.println("success move, updated map :");
                gameDisplay.display_map();

                // Check win condition
                if (check_win_condition()) {
                    System.out.println("player " + player_name[currentPlayer - 1] + " win");
                    //ask to save
                    System.out.println("Do you want to save this game ?(yes/no)");
                    String save_choice=scanner.nextLine().trim();
                    if(save_choice.equalsIgnoreCase("yes")){
                        ask_save_file(scanner, player_name, game_file.FileType.RECORD);
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

    private boolean processPlayerTurn(String[] player_name, Scanner scanner, game_file recorder) throws IOException {
        String chess_input;
        String move_input;
        boolean move_made = false;

        // choose chess
        chessSelection:
        while (true) {
            System.out.print("Which chess you want to move ? (enter 'help' for get help) ");
            chess_input = scanner.nextLine().trim();

            int process_input_result = process_input(chess_input,player_name,scanner,1,recorder);
            if (process_input_result == 1 || process_input_result == 3 ){
                continue ;
            }

            chess c_chess = game_map.getChess_by_name(chess_input, currentPlayer);

            if (c_chess != null) {
                // choose move
                while (true) {
                    System.out.print("Which way you want to move ? (enter 'help' for get help) ");
                    move_input = scanner.nextLine().trim();
                    //back to choose chess
                    int processed_input = process_input(move_input,player_name,scanner,2,recorder);
                    if (processed_input == 2) {
                        System.out.println("back to choose chess!");
                        break chessSelection;
                    }
                    else if (processed_input == 1){
                        continue ;
                    }
                    int[] target_location = calculate_move(c_chess.getRow(), c_chess.getColumn(), move_input);
                    if (target_location != null) {

                        chess captured_chess = game_map.getChess(target_location[0], target_location[1]);
                        int c_row = c_chess.getRow();
                        int c_col = c_chess.getColumn();

                        //check movement and apply it
                        if (mover.check_target_location(c_chess, target_location[0], target_location[1])) {

                            move_made = true;
                            //record success movement
                            move t_b_c = new move(c_chess, captured_chess, c_row, c_col, target_location[0], target_location[1],round_counter,player_name[currentPlayer-1],"success");
                            tbc_stack.push(t_b_c);
                            jungle_list.add(t_b_c);
                            if(recorder != null) {
                                recorder.record_move(t_b_c);
                            }
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
        //base on way to calculate the target location
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
        chess player1AtDen = game_map.getChess(8, 3);
        if (player1AtDen != null) {
            return true;
        }
        // player 2 go to den
        chess player2AtDen = game_map.getChess(0, 3);
        if (player2AtDen != null) {
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

    private boolean handle_undo(String[] playerNames, map game_map, Stack<move> tbc_stack, game_file recorders) {
        if (take_back_counter[currentPlayer-1] >= 3) {
            System.out.println("You have used all your chance to take back !");
            return false;
        }

        if (tbc_stack.size() >= 2) {
            //apply take back and update data of map and chess
            move tbc=tbc_stack.pop();
            move tbc_undo1=tbc.undo(game_map,playerNames[currentPlayer-1],round_counter);

            tbc=tbc_stack.pop();
            move tbc_undo2=tbc.undo(game_map,playerNames[currentPlayer-1],round_counter);

            //record take back
            if (recorders != null) {
                recorders.record_move(tbc_undo1);
                recorders.record_move(tbc_undo2);
            }
            //add take back to jungle list
            jungle_list.add(tbc_undo1);
            jungle_list.add(tbc_undo2);

            //show the time of take back
            take_back_counter[currentPlayer-1] = take_back_counter[currentPlayer-1] + 1;
            gameDisplay.display_map();

            System.out.println("You still can take back " + (3 - take_back_counter[currentPlayer-1]) + " times");
            System.out.println("Player " + playerNames[currentPlayer - 1] + " round ");
            return true;
        } else {
            System.out.println("You cannot take back at the beginning");
            return false;
        }
    }

    public void ask_save_file(Scanner scanner, String[] player_name, game_file.FileType fileType) throws IOException {
        boolean fileCreated = false;
        String file_name = "";

        while (!fileCreated) {
            try {
                //ask file name
                System.out.println("Please enter file name");
                file_name = scanner.nextLine().trim();

                game_file jungle_file = new game_file(player_name[0], player_name[1], file_name, fileType, take_back_counter);

                // write move to file
                for (move m : jungle_list) {
                    jungle_file.record_move(m);
                }

                fileCreated = true;
                System.out.println("Game saved successfully!");

            } catch (IOException e) {
                if (e.getMessage().contains("already exists")) {
                    System.out.println("File '" + file_name + "' already exists ");
                } else {
                    // Re-throw other IO exceptions
                    throw e;
                }
            }
        }
    }

    public int process_input(String input, String[] player_name,Scanner scanner, int choice, game_file recorder) throws IOException {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Game end without saving !");
            System.exit(0);
        }
        else if (input.equalsIgnoreCase("help")) {
            display_interactive.display_help();
            return 1 ;
        }
        else if (input.equalsIgnoreCase("display")) {
            displayInteractive.display_game_status(game_map, player_name, currentPlayer);
            return 1;
        }
        else if (input.equalsIgnoreCase("undo") ){
            boolean undo_result =handle_undo(player_name, game_map, tbc_stack, recorder);
            if (undo_result) {
                return 1;
            }
            else {
                return 3;
            }
        }
        else if (input.equalsIgnoreCase("save")){
            ask_save_file(scanner,player_name, game_file.FileType.JUNGLE);
            System.exit(0);
        }

        if (choice == 2){
            if ( input.equalsIgnoreCase("back")){
                return 2;
            }
        }
        return -1;
    }
}

