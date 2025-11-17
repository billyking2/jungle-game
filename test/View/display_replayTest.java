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

class display_replayTest {

    private display_replay myDisplayReplay;
    private display_map myDisplayMap;
    private map myMap;
    private List<move> movesReplay;
    private game_file gameFile;

    @BeforeEach
    void setup(){
        myMap = new map();
        myDisplayMap = new display_map(myMap);
        movesReplay = new ArrayList<move>();

    }

    @Test
    void displaymymap(){
        chess chess1 = myMap.getChess(0,6);
        System.out.println(chess1);
    }
    @Test
    void start_replay() throws IOException, InterruptedException {
        assertThrows(IOException.class, () -> {
            display_replay.start_replay("testRecordFile");
        });

        String record_file_name = "testRecordFile"+System.currentTimeMillis();
        gameFile  = new game_file("a","b",record_file_name,
                game_file.FileType.RECORD, new int[]{2, 3});


        chess chess1 = new chess("wolf", 1, 2, 4);
        move move1 = new move(chess1, null, 2, 4, 1, 4, 1, "tester1", "success");
        gameFile.record_move(move1);

        chess chess2 = new chess("wolf", 2, 6, 2);
        move move2 = new move(chess2, null, 6, 2, 7, 2, 2, "tester2", "success");
        gameFile.record_move(move2);

        display_replay.start_replay(record_file_name);

    }

    @Test
    void replay_game() throws InterruptedException {

    }

    @Test
     void update_move() {

    }

    @Test
    void display_record_line() {

    }
}