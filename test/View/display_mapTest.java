package View;

import Model.chess;
import Model.map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class display_mapTest {

    private display_map myDisplayMap;
    private map myMap;

    @BeforeEach
    void setup(){
        myMap = new map();
        myDisplayMap = new display_map(myMap);
    }

    @Test
    void display_map() {
        //display the map
        //make a chess to unknown chess
        myMap.setChess(0,0,new chess("unknown",1,1,1));
        myDisplayMap.display_map();

    }
}
