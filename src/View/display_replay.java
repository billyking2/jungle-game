package View;

import Controller.game_file;
import Model.map;
import Model.move;

import java.io.IOException;
import java.util.List;

public class display_replay {
    private map game_map;
    private display_map game_display;


    public display_replay(map game_map, display_map game_display) {
        this.game_map = game_map;
        this.game_display = game_display;
    }

    //call to display replay
    public static void start_replay(String filename) throws IOException, InterruptedException, IOException {
        //load all the move class form record file
        game_file jungleManager = new game_file(filename, game_file.FileType.RECORD);
        List<move> moves_replay = jungleManager.get_moves();

        map game_map = new map();
        display_map game_display = new display_map(game_map);

        display_replay replay = new display_replay(game_map, game_display);

        replay.replay_game(moves_replay);
    }


        public void replay_game(List<move> moves_replay) throws InterruptedException {
            System.out.println("=== Starting Game Replay ===");
            game_display.display_map();


            for (move moves : moves_replay) {
                moves.setMoved_chess(game_map.getChess(moves.getFromRow(),moves.getFromCol()));
                moves.setCaptured_chess(game_map.getChess(moves.getToRow(),moves.getToCol()));

                System.out.println(display_record_line(moves));

                // 2 second delay between moves
                Thread.sleep(2000);

                // Execute the move on the game board
                update_move(moves);
                game_display.display_map();

            }
            System.out.println("Display End");
        }

        private void update_move(move moves) {
            // Update the chess piece's internal coordinates
            if (moves.getMoved_chess() != null) {
                moves.getMoved_chess().setRow(moves.getToRow());
                moves.getMoved_chess().setColumn(moves.getToCol());
            }

            game_map.setChess(moves.getToRow(), moves.getToCol(), moves.getMoved_chess());
            game_map.setChess(moves.getFromRow(), moves.getFromCol(), null);
        }


        public String display_record_line (move moves) {
            return String.format("Round %d, player %s, moved %s, from (%d,%d), to (%d,%d), captured %s, result %s",
                    moves.getRound(), moves.getPlayer_name(), moves.getMoved_chess().getType(), moves.getFromRow() + 1, moves.getFromCol() + 1, moves.getToRow() + 1, moves.getToCol() + 1, moves.getCaptured_chess(), moves.getResult());

    }




}
