package View;

import Controller.game_file;
import Model.chess;
import Model.map;
import Model.move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class display_jungleTest {
    private display_jungle my_display_jungle;
    private map my_map;
    private display_map my_game_display;
    private List<move> moves_jungle;
    private game_file gameFile;

    @BeforeEach
    void setup(){
        my_map = new map();
        my_game_display = new display_map(my_map);
        moves_jungle = new ArrayList<move>();
        // Create sample moves for testing
        chess chess1 = new chess("wolf", 1, 2, 4);
        move move1 = new move(chess1, null, 2, 4, 1, 4, 1, "tester1", "success");
        moves_jungle.add(move1);

        chess chess2 = new chess("wolf", 2, 6, 2);
        move move2 = new move(chess2, null, 6, 2, 7, 2, 2, "tester2", "success");
        moves_jungle.add(move2);

        my_display_jungle = new display_jungle(my_map, my_game_display,moves_jungle);

    }

    @Test
    //test the continue function
    void start_jungle() throws IOException, InterruptedException, IOException{
        assertThrows(IOException.class, () -> {
            display_replay.start_replay("testJungleFile");
        });

        String record_file_name = "testJungleFile"+System.currentTimeMillis();
        gameFile  = new game_file("a","b",record_file_name,
                game_file.FileType.JUNGLE, new int[]{2, 3});


        for (move moves : moves_jungle ) {
            gameFile.record_move(moves);
        }

        display_jungle.start_jungle(record_file_name);

    }


    @Test
    void continue_game() throws InterruptedException{
        //continue the game
        my_display_jungle.continue_game() ;
    }

    @Test
    void getGame_map() {
        //get the game map
        my_display_jungle.getGame_map();
    }
}
