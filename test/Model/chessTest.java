package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class chessTest {

    @Test
    void getrank() {
        //get the rank of a rat chess
        chess rat = new chess("rat",1,1,1);
        assertEquals(1,rat.getrank(rat.getType()));
    }

    @Test
    void capture() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getColumn() {
    }

    @Test
    void setColumn() {
    }

    @Test
    void getRow() {
    }

    @Test
    void setRow() {
    }

    @Test
    void getType() {
    }

    @Test
    void setType() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void setPlayer() {
    }
}