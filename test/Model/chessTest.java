package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class chessTest {

    @Test
    void getRat() {
        //get the rank of a rat chess
        chess rat = new chess("rat",1,1,1);
        assertEquals(1,rat.getrank(rat.getType()));
    }
    @Test
    void getCat() {
        //get the rank of a cat chess
        chess cat = new chess("cat",1,1,1);
        assertEquals(2,cat.getrank(cat.getType()));
    }
    @Test
    void getDog() {
        //get the rank of a dog chess
        chess dog = new chess("dog",1,1,1);
        assertEquals(3,dog.getrank(dog.getType()));
    }

    @Test
    void getWolf() {
        //get the rank of a wolf chess
        chess wolf = new chess("wolf",1,1,1);
        assertEquals(4,wolf.getrank(wolf.getType()));
    }

    @Test
    void getLeopard() {
        //get the rank of a leopard chess
        chess leopard = new chess("leopard",1,1,1);
        assertEquals(5,leopard.getrank(leopard.getType()));
    }

    @Test
    void getTiger() {
        //get the rank of a tiger chess
        chess tiger = new chess("tiger",1,1,1);
        assertEquals(6,tiger.getrank(tiger.getType()));
    }

    @Test
    void getLion() {
        //get the rank of a lion chess
        chess lion = new chess("lion",1,1,1);
        assertEquals(7,lion.getrank(lion.getType()));
    }

    @Test
    void getElephant() {
        //get the rank of a elephant chess
        chess elephant = new chess("elephant",1,1,1);
        assertEquals(8,elephant.getrank(elephant.getType()));
    }

    @Test
    void getUnknown() {
        //get the rank of a unknown type chess
        chess unknown = new chess("unknown",1,1,1);
        assertEquals(-1,unknown.getrank(unknown.getType()));
    }

    @Test
    void captureOwnChess() {
        //same player cannot capture his own chess
        chess myChess1 = new chess("rat",1,0,1);
        chess myChess2 = new chess("lion",1,0,0);
        assertFalse(myChess1.capture(myChess2));
    }

    @Test
    void ratCaptureElephant(){
        //rat can capture elephant
        chess rat = new chess("rat",1,1,1);
        chess elephant = new chess("elephant",2,2,1);
        assertTrue(rat.capture(elephant));
    }

    @Test
    void captureEnemyWithHigherRank(){
        //player capture enemy chess with higher rank chess
        chess myChess = new chess("cat",1,0,0);
        chess oppChess = new chess("rat",2,0,1);
        assertTrue(myChess.capture(oppChess));
    }

    @Test
    void captureEnemyWithLowerRank(){
        //player cannot capture enemy chess with lower rank chess
        chess myChess = new chess("rat",1,0,0);
        chess oppChess = new chess("cat",2,0,1);
        assertFalse(myChess.capture(oppChess));
    }

    @Test
    void testToString() {
       chess myChess = new chess("rat",1,0,0);
       assertEquals("Chess{type='rat', player=1, row=0, column=0}",myChess.toString());
    }

    @Test
    void getColumn() {
        chess myChess = new chess("rat",1,2,3);
        assertEquals(3,myChess.getColumn());
    }

    @Test
    void setColumn() {
        chess myChess = new chess();
        myChess.setColumn(5);
        assertEquals(5,myChess.getColumn());
    }

    @Test
    void getRow() {
        chess myChess = new chess("rat",1,2,3);
        assertEquals(2,myChess.getRow());
    }

    @Test
    void setRow() {
        chess myChess = new chess();
        myChess.setRow(4);
        assertEquals(4,myChess.getRow());
    }

    @Test
    void getType() {
        chess myChess = new chess("rat",1,2,3);
        assertEquals("rat",myChess.getType());
    }

    @Test
    void setType() {
        chess myChess = new chess();
        myChess.setType("rat");
        assertEquals("rat",myChess.getType());
    }

    @Test
    void getPlayer() {
        chess myChess = new chess("rat",1,2,3);
        assertEquals(1,myChess.getPlayer());
    }

    @Test
    void setPlayer() {
        chess myChess = new chess();
        myChess.setPlayer(1);
        assertEquals(1,myChess.getPlayer());
    }
}
