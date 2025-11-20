package View;

import Controller.game_file;
import Model.chess;
import Model.map;
import Model.moveLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class display_jungleTest {
    private display_jungle my_display_jungle;
    private map my_map;
    private display_map my_game_display;
    private List<moveLog> moves_jungle;
    private game_file gameFile;

    @BeforeEach
    void setup(){
        my_map = new map();
        my_game_display = new display_map(my_map);
        moves_jungle = new ArrayList<moveLog>();
        // Create sample moves for testing
        chess chess1 = new chess("wolf", 1, 2, 4);
        moveLog moveLog1 = new moveLog(chess1, null, 2, 4, 1, 4, 1, "tester1", "success");
        moves_jungle.add(moveLog1);

        chess chess2 = new chess("wolf", 2, 6, 2);
        moveLog moveLog2 = new moveLog(chess2, null, 6, 2, 7, 2, 2, "tester2", "success");
        moves_jungle.add(moveLog2);

        my_display_jungle = new display_jungle(my_map, my_game_display,moves_jungle);
    }

    @Test
    //test the continue function
    void start_jungle() throws InterruptedException, IOException{
        assertThrows(IOException.class, () -> {
            display_replay.start_replay("testJungleFile");
        });

        String jungle_file_name = "testJungleFile"+System.currentTimeMillis();
        gameFile  = new game_file("a","b", jungle_file_name,
                game_file.FileType.JUNGLE, new int[]{2, 3});


        for (moveLog moves : moves_jungle ) {
            gameFile.record_move(moves);
        }

        display_jungle.start_jungle(jungle_file_name);

        //delete file
        Path path = Paths.get("././jungle/" + jungle_file_name + ".jungle");
        Files.deleteIfExists(path);
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
