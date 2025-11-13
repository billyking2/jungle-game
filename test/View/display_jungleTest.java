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

    @BeforeEach
    void setup(){
        my_map = new map();
        my_game_display = new display_map(my_map);
        moves_jungle = new ArrayList<move>();
        chess chess1= new chess("1",1,1,1);
        move move1 = new move();
        move1.setRound(1);
        move1.setMoved_chess(chess1);
        moves_jungle.add(move1);


        my_display_jungle = new display_jungle(my_map,my_game_display,moves_jungle);
    }

    @Test
    //test the continue function
    void start_jungle() throws IOException, InterruptedException, IOException{
        display_jungle.start_jungle("testFile");
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
