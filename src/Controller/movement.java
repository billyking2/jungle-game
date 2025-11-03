package Controller;
import Model.Chess;
import Model.map;

public class movement {

        private map game_map;
        private int current_player;

        public movement(map gameMap) {
                this.game_map = gameMap;
                this.current_player = 1; // Player 1 starts
        }


        public boolean check_target_location(Chess chess, int row, int column){
                int c_row = chess.getRow();
                int c_column = chess.getColumn();
                Chess target_chess = game_map.getChess(row,column);
                if (!game_map.check_boundary(row, column)) {
                        System.out.println("error: out of boundary");
                        return false;
                }


                if (game_map.check_River(c_row,c_column)){
                        if(target_chess != null) {
                                if (game_map.check_River(row, column) && chess.capture(target_chess)) {
                                        update_map_after_move(chess, row, column);
                                        return true;
                                } else if (!game_map.check_River(row, column) && game_map.getChess(row, column) != null) {
                                        System.out.println("error: you can not eat other chess from river");
                                        return false;
                                }
                        }
                        if (target_chess == null){
                                update_map_after_move(chess,row,column);
                        }
                }

                if (game_map.check_River(row,column)){
                        if (chess.getType() == "rat"){
                                update_map_after_move(chess,row,column);
                                return true;
                        }
                        else if (chess.getType() == "lion" || chess.getType() == "tiger"){
                                        return check_jump_over(chess,c_row,c_column,row,column);
                        }
                        else {
                                System.out.println("error:this chess cannot enter water");
                                return false;
                        }
                }

                if (game_map.check_Dens(row,column)) {
                        if ((chess.getPlayer() == 1 && row == 0 && column == 3) ||
                                (chess.getPlayer() == 2 && row == 8 && column == 3)) {
                                System.out.println("error: cannot enter your own den");
                                return false;
                        }
                }
                if (game_map.check_Trap(row,column)){
                        if(target_chess != null){
                                update_map_after_move(chess,row,column);
                                return true;
                        }
                }

                if (target_chess != null) {
                        if (chess.capture(target_chess)) {
                                update_map_after_move(chess,  row, column);
                                return true;
                        }
                }
                if(target_chess == null ){
                        update_map_after_move(chess,row,column);
                        return true;
                }

                else {
                        return false;
                }
        }

        public void update_map_after_move(Chess chess, int t_row, int t_column){
                game_map.setChess(t_row, t_column, chess);
                game_map.setChess(chess.getRow(), chess.getColumn(), null);
                chess.setRow(t_row);
                chess.setColumn(t_column);
        }


        public boolean check_jump_over(Chess start_chess, int row1,int column1, int row2,int column2){
                //jump hor
                int target_column;
                int target_row;
                if (row1==row2 ){
                        if (column1 > column2){
                                 target_column =  column2-2;
                        }
                        else {
                                 target_column = column2+2;
                        }

                        int start_location= Math.min(column1,target_column);
                        int end_location = Math.max(column1,target_column);

                        Chess target_Chess = game_map.getChess(row1, target_column);

                        while (start_location+1 < end_location) {
                                if (game_map.getChess(row1, start_location + 1) != null) {
                                        System.out.println("there are a rat on the river");
                                        return false;
                                }
                                start_location++;
                        }

                        if (target_Chess != null) {
                                if ( start_chess.capture(target_Chess)){
                                        update_map_after_move(start_chess,row2,target_column);
                                        return true;
                                }
                                else {
                                        return false;
                                }
                        }
                        update_map_after_move(start_chess,row2,target_column);
                        return true;
                }


                //jump vert
                else if(column1 == column2 ){
                        if (row1 > row2){
                                target_row =  row2-3;
                        }
                        else {
                                target_row = row2+3;
                        }

                        int start_location= Math.min(row1,target_row);
                        int end_location = Math.max(row1,target_row);

                        Chess target_Chess = game_map.getChess(target_row, column2);

                        while (start_location+1 < end_location) {
                                if (game_map.getChess(start_location+1, column1) != null) {
                                        System.out.println("there are a rat on the river");
                                        return false;
                                }
                                start_location++;
                        }
                        if (target_Chess != null) {
                                if (start_chess.capture(target_Chess)){
                                        update_map_after_move(start_chess,target_row,column2);
                                        return true;
                                }
                                else {
                                        return false;
                                }
                        }
                        update_map_after_move(start_chess,target_row,column2);
                        return true;
                }
                else{
                        System.out.println("error: unknown error in check_jump_over");
                        return false;
                }

        }

}
