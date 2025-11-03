package View;

import Model.map;
import Model.move;
import Controller.Records_file;

import java.io.IOException;
import java.util.List;

public class display_replay {
    private map game_map;
    private display_map game_display;
    private List<move> moves_replay;
    private int current_move;

    public display_replay(map game_map, display_map game_display, List<move> moves_replay, int current_move) {
        this.game_map = game_map;
        this.game_display = game_display;
        this.moves_replay = moves_replay;
        this.current_move = current_move;
    }

    public static void startReplay(String filename) throws IOException, InterruptedException, IOException {

        List<move> moves = Records_file.load_record_file(filename);

        map game_map = new map();
        display_map game_display = new display_map(game_map);

        display_replay replay = new display_replay(game_map, game_display, moves, 0);
        replay.replay_game();
    }


        public void replay_game() throws InterruptedException {
            System.out.println("=== Starting Game Replay ===");
            game_display.display_map();

            for (move moves : moves_replay) {
                moves.setMoved_chess(game_map.getChess(moves.getFromRow(),moves.getFromCol()));
                moves.setCaptured_chess(game_map.getChess(moves.getToRow(),moves.getToCol()));

                System.out.println(display_record_line(moves));

                Thread.sleep(2000); // 2 second delay between moves

                // Execute the move on the game board
                update_move(moves);
                game_display.display_map();
            }

            System.out.println("=== END ===");
        }

        private void update_move(move moves) {
            game_map.setChess(moves.getToRow(), moves.getToCol(), moves.getMoved_chess());
            game_map.setChess(moves.getFromRow(), moves.getFromCol(), null);
        }



        public String display_record_line (move moves) {
            return String.format("Round %d, player %s, moved %s, from (%d,%d), to (%d,%d), captured %s, result %s",
                    moves.getRound(), moves.getPlayer_name(), moves.getMoved_chess().getType(), moves.getFromRow() + 1, moves.getFromCol() + 1, moves.getToRow() + 1, moves.getToCol() + 1, moves.getCaptured_chess(), moves.getResult());

    }




}
