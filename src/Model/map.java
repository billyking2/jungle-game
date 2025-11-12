package Model;

import java.util.Objects;

public class map {
    private static final int Columns = 7;
    private static final int Rows = 9;

    private chess[][] Map;

    private final int[][] Dens = { {0, 3}, {8, 3} };
    private final int[][] Traps = { {0, 2}, {0, 4}, {1, 3}, {8, 2}, {8, 4}, {7, 3} };
    private final int[][] Rivers = { {3,1},{4,1},{5,1} , {3,2},{4,2},{5,2} , {3,4},{4,4},{5,4} , {3,5},{4,5},{5,5}};

    public boolean check_Dens(int row, int columns){
        for(int[] den:Dens){
            if(den[0]==row && den[1]==columns){
                return true;
            }
        }
        return false;
    }

    public boolean check_Trap(int row, int columns) {
        for (int[] trap : Traps) {
            if (trap[0] == row && trap[1] == columns) {
                return true;
            }
        }
        return false;
    }

    public boolean check_River(int row, int columns) {
        for (int[] river : Rivers) {
            if (river[0] == row && river[1] == columns) {
                return true;
            }
        }
        return false;
    }

    public map(){
        Map= new chess[Rows][Columns];
        genMap();
    }

    private void genMap(){
        for (int y = 0; y < Rows; y++) {
            for (int x = 0; x < Columns; x++) {
                Map[y][x] = null;
            }
        }

        Map[0][0]=new chess("lion",1,0,0);
        Map[0][6]=new chess("tiger",1,0,6);
        Map[1][1]=new chess("dog",1,1,1);
        Map[1][5]=new chess("cat",1,1,5);
        Map[2][0]=new chess("rat",1,2,0);
        Map[2][2]=new chess("leopard",1,2,2);
        Map[2][4]=new chess("wolf",1,2,4);
        Map[2][6]=new chess("elephant",1,2,6);

        Map[6][0] = new chess("elephant", 2, 6, 0);
        Map[6][2] = new chess("wolf", 2, 6, 2);
        Map[6][4] = new chess("leopard", 2, 6, 4);
        Map[6][6] = new chess("rat", 2, 6, 6);
        Map[8][0] = new chess("tiger", 2, 8, 0);
        Map[8][6] = new chess("lion", 2, 8, 6);
        Map[7][1] = new chess("cat", 2, 7, 1);
        Map[7][5] = new chess("dog", 2, 7, 5);


    }

    public chess getChess(int rows, int columns) {
        if (check_boundary(rows, columns)) {
            return Map[rows][columns];
        }
        return null;
    }

    public void setChess(int rows, int columns, chess chess) {
        if (check_boundary(rows, columns)) {
            Map[rows][columns] = chess;
        }
    }

    public chess getChess_by_name(String name , int player){
        for (int y = 0; y < Rows; y++) {
            for (int x = 0; x < Columns; x++) {
                chess chess = Map[y][x];
                if (chess != null &&
                        Objects.equals(chess.getType(), name.toLowerCase()) &&
                        chess.getPlayer() == player) {
                    return chess;
                }
            }
        }
        System.out.println("chess not found: " + name);
        return null;
    }

    //check if there are any other players' pieces on the map.
    public boolean getChess_by_player(int player){
        for (int y = 0; y < Rows; y++) {
            for (int x = 0; x < Columns; x++) {
                chess chess = Map[y][x];
                if (chess != null &&
                        chess.getPlayer() == player) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean check_boundary(int rows, int columns) {
        return rows >= 0 && rows < Rows && columns >= 0 && columns < Columns;
    }

}
