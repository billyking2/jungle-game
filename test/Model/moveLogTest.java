package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class moveLogTest {
    private moveLog myMoveLog;
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
        myMoveLog = new moveLog();

        myMoveLog.setMoved_chess(myMap.getChess(0,0));
        myMoveLog.setCaptured_chess(myMap.getChess(0,6));

        myMap.setChess(0,6,null);

        myMoveLog.getMoved_chess(); //get the moved chess
        myMoveLog.getCaptured_chess(); //get captured chess

        myMoveLog.setFromRow(0);
        myMoveLog.getFromRow();
        myMoveLog.setFromCol(1);
        myMoveLog.getFromCol();
        myMoveLog.setToCol(0);
        myMoveLog.getToCol();
        myMoveLog.setToRow(0);
        myMoveLog.getToRow();
        myMoveLog.setRound(3);
        myMoveLog.getRound();

        myMoveLog.setPlayer_name("2");
        myMoveLog.getPlayer_name();
        myMoveLog.setResult("success");
        myMoveLog.getResult();

    }

    @Test
    void undo() {
        //undo with captured chess
        myMoveLog.undo(myMap, myMoveLog.getPlayer_name(), 3);
    }

    @Test
    void undo_without_capture() {
        //undo without captured chess
        myMoveLog.setCaptured_chess(null);
        myMoveLog.undo(myMap, myMoveLog.getPlayer_name(), 3);

    }

    @Test
    void record_success() {
        //record the successful move
        myMoveLog.record_success();
    }

    @Test
    void record_success_without_capture() {
        //record the successful move without captured chess
        myMoveLog.setCaptured_chess(null);
        myMoveLog.record_success();
    }


}
