package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class moveTest {
    private move myMove;
    private map myMap;

    private chess moved_chess;
    private chess captured_chess;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private int round;
    private String playerName;
    private String result;

    @BeforeEach
    void setup(){
        myMap = new map();
        myMove = new move();
        //assume player 1 lion (0,1 -> 0,0) eat player 2 tiger (0,0)
        myMove.setMoved_chess(myMap.getChess(0,0)); //set it to lion, player 1
        myMove.setCaptured_chess(myMap.getChess(0,6)); //set it to tiger, player 2

        myMap.setChess(0,6,null); //assume lion eat tiger from (0,0 to 0,1)

        myMove.getMoved_chess(); //get the moved chess
        myMove.getCaptured_chess(); //get captured chess

        myMove.setFromRow(0);
        myMove.getFromRow();
        myMove.setFromCol(1);
        myMove.getFromCol();
        myMove.setToCol(0);
        myMove.getToCol();
        myMove.setToRow(0);
        myMove.getToRow();
        myMove.setRound(3);
        myMove.getRound();

        myMove.setPlayer_name("2");
        myMove.getPlayer_name();
        myMove.setResult("success");
        myMove.getResult();



    }

    @Test
    void undo() {
        //undo with captured chess
        myMove.undo(myMap, myMove.getPlayer_name(), 3);
    }

    @Test
    void undo_without_capture() {
        //undo without captured chess
        myMove.setCaptured_chess(null);
        myMove.undo(myMap, myMove.getPlayer_name(), 3);

    }

    @Test
    void record_success() {
        //record the successful move
        myMove.record_success();
    }

    @Test
    void record_success_without_capture() {
        //record the successful move without captured chess
        myMove.setCaptured_chess(null);
        myMove.record_success();
    }


}
