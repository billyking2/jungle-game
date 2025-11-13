package Model;

public class move {
    private chess moved_chess;
    private chess captured_chess;
    private int fromRow, fromCol;
    private int toRow, toCol;
    private int round;
    private String player_name;
    private String result;

    //update the map and chess for take back
    public move undo(map game_map,String player_name,int round) {

        game_map.setChess(toRow, toCol, null);
        game_map.setChess(fromRow, fromCol, moved_chess);
        moved_chess.setRow(fromRow);
        moved_chess.setColumn(fromCol);

        if (captured_chess != null) {
            game_map.setChess(toRow, toCol, captured_chess);
            captured_chess.setRow(toRow);
            captured_chess.setColumn(toCol);
        }
        return new move (moved_chess, captured_chess, toRow, toCol, fromRow, fromCol,round,player_name,"undo");
    }

    //return a String for record or jungle file
    public String record_success() {
        String moved_type = (moved_chess != null) ? moved_chess.getType() : "unknown";
        if (captured_chess != null) {
            return String.format("Round %d, player %s, moved %s, from %d%d, to %d%d, captured %s, result %s,",
                    round, player_name, moved_type, fromRow, fromCol, toRow, toCol,
                    captured_chess.getType(), result);
        } else {
            return String.format("Round %d, player %s, moved %s, from %d%d, to %d%d, captured null, result %s,",
                    round, player_name, moved_type, fromRow, fromCol, toRow, toCol, result);
        }
    }


    public chess getMoved_chess() {
        return moved_chess;
    }

    public void setMoved_chess(chess moved_chess) {
        this.moved_chess = moved_chess;
    }

    public chess getCaptured_chess() {
        return captured_chess;
    }

    public void setCaptured_chess(chess captured_chess) {
        this.captured_chess = captured_chess;
    }

    public int getFromRow() {
        return fromRow;
    }

    public void setFromRow(int fromRow) {
        this.fromRow = fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public void setFromCol(int fromCol) {
        this.fromCol = fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public void setToRow(int toRow) {
        this.toRow = toRow;
    }

    public int getToCol() {
        return toCol;
    }

    public void setToCol(int toCol) {
        this.toCol = toCol;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public move() {
    }

    public move(chess moved_chess, chess captured_chess, int fromRow, int fromCol, int toRow, int toCol, int round, String player_name, String result) {
        this.moved_chess = moved_chess;
        this.captured_chess = captured_chess;
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
        this.round = round;
        this.player_name = player_name;
        this.result = result;
    }
}
