package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class mapTest {
    private map myMap;

    @BeforeEach
    void setup(){
        myMap = new map();
    }


    @Test
    void check_Dens_True() {
        //check whether a square is dens or not
        assertTrue(myMap.check_Dens(0,3));
    }

    @Test
    void check_Dens_False() {
        //check whether a square is dens or not
        assertFalse(myMap.check_Dens(0,4));
    }

    @Test
    void check_Trap_True() {
        //check whether a square is trap or not
        assertTrue(myMap.check_Trap(0,2));
    }

    @Test
    void check_Trap_False() {
        //check whether a square is trap or not
        assertFalse(myMap.check_Trap(0,3));
    }

    @Test
    void check_River_True() {
        //check whether a square is river or not
        assertTrue(myMap.check_River(3,1));
    }

    @Test
    void check_River_False() {
        //check whether a square is river or not
        assertFalse(myMap.check_River(3,0));
    }

    @Test
    void getChess() {
        //get a chess from the map
        assertNull(myMap.getChess(0,1));
    }

    @Test
    void getChessOutOfBoundary() {
        //get a chess from the map
        assertNull(myMap.getChess(-1,1));
    }


    @Test
    void setChess() {
        //set a chess from the map
        chess myChess = new chess("rat",1,2,0);
        myMap.setChess(2,0,myChess);
    }

    @Test
    void setChessOutOfBoundary() {
        //set a chess from the map out of boundary
        chess myChess = new chess("rat",1,2,-1);
        myMap.setChess(2,-1,myChess);
    }

    @Test
    void getChess_by_name() {
        // get a chess by its name
        myMap.getChess_by_name("rat",1);
    }

    @Test
    void getChess_by_name_Not_Found_Type() {
        // get a unknown chess name by it name
        myMap.getChess_by_name("Unknown",1);
    }

    @Test
    void getChess_by_name_Not_Found_Player() {
        // get a chess by a unknown name
        myMap.getChess_by_name("rat",999);
    }

    @Test
    void getChess_by_player() {
        // get a chess by a player's name
        myMap.getChess_by_player(1);
    }

    @Test
    void getChess_by_player_Not_Found() {
        // get a chess by a unknown player's name
        myMap.getChess_by_player(999);
    }

    @Test
    void check_boundary() {
        //check error boundary
        myMap.check_boundary(-1,0);
        myMap.check_boundary(0,-1);
        myMap.check_boundary(99,0);
        myMap.check_boundary(0,99);
    }
}
