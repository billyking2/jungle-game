package View;

import Controller.game_file;
import Model.map;
import Model.move;

import java.io.IOException;
import java.util.List;

public class display_jungle {
    private map game_map;
    private display_map game_display;
    private List<move> moves_jungle;

    public display_jungle(map game_map, display_map game_display, List<move> moves_jungle) {
        this.game_map = game_map;
        this.game_display = game_display;
        this.moves_jungle = moves_jungle;
    }



    public static display_jungle start_jungle(String filename) throws IOException, InterruptedException, IOException {


        game_file jungleManager = new game_file(filename, game_file.FileType.JUNGLE);
        List<move> moves = jungleManager.get_moves();

        map game_map = new map();
        display_map game_display = new display_map(game_map);

        display_jungle jungle = new display_jungle(game_map, game_display, moves);


        return jungle;
    }


    public void continue_game() throws InterruptedException {
        System.out.println("=== Continue Game  ===");
        for (move moves : moves_jungle) {
            moves.setMoved_chess(game_map.getChess(moves.getFromRow(),moves.getFromCol()));
            moves.setCaptured_chess(game_map.getChess(moves.getToRow(),moves.getToCol()));
            update_move(moves);

        }

    }

    private void update_move(move moves) {
        game_map.setChess(moves.getToRow(), moves.getToCol(), moves.getMoved_chess());
        game_map.setChess(moves.getFromRow(), moves.getFromCol(), null);

        moves.getMoved_chess().setRow(moves.getToRow());
        moves.getMoved_chess().setColumn(moves.getToCol());

    }


    public map getGame_map() {
        return game_map;
    }



}
