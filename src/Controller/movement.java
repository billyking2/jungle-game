package Controller;
import Model.Chess;
import Model.map;

public class movement {
        private map game_map;

        public movement(map gameMap) {
                this.game_map = gameMap;
        }


        public boolean check_target_location(Chess chess, int row, int column){
                int c_row = chess.getRow();
                int c_column = chess.getColumn();
                Chess target_chess = game_map.getChess(row,column);

                //check boundary
                if (!game_map.check_boundary(row, column)) {
                        System.out.println("error: out of boundary");
                        return false;
                }

                //if rat in river
                if (game_map.check_River(c_row,c_column)){

                        if(target_chess != null) {
                                // if both rat are in the river
                                if (game_map.check_River(row, column) && chess.capture(target_chess)) {
                                        update_map_after_move(chess, row, column);
                                        return true;
                                //if rat in river, other chess on land
                                } else if (!game_map.check_River(row, column) && game_map.getChess(row, column) != null) {
                                        System.out.println("error: you can not eat other chess from river");
                                        return false;
                                }
                        }
                        // rat can move anywhere
                        if (target_chess == null){
                                update_map_after_move(chess,row,column);
                                return true;
                        }
                }

                //if target location is river
                if (game_map.check_River(row,column)){
                        //rat can enter river
                        if (chess.getType() == "rat"){
                                update_map_after_move(chess,row,column);
                                return true;
                        }
                        //"lion" and "tiger" can jump over the river
                        else if (chess.getType() == "lion" || chess.getType() == "tiger"){
                                        return check_jump_over(chess,c_row,c_column,row,column);
                        }
                        //other chess can not go into river
                        else {
                                System.out.println("error:this chess cannot enter water");
                                return false;
                        }
                }

                // chess can not enter their own dens
                if (game_map.check_Dens(row,column)) {
                        if ((chess.getPlayer() == 1 && row == 0 && column == 3) ||
                                (chess.getPlayer() == 2 && row == 8 && column == 3)) {
                                System.out.println("error: cannot enter your own den");
                                return false;
                        }
                }
                //A piece may capture any enemy piece in one of the player's trap squares
                if (game_map.check_Trap(row,column)){
                        update_map_after_move(chess,row,column);
                        return true;

                }
                // if target location have chess, check if it can be captured
                if (target_chess != null) {
                        if (chess.capture(target_chess)) {
                                update_map_after_move(chess,  row, column);
                                return true;
                        }
                }
                // target location is empty and land
                if(target_chess == null ){
                        update_map_after_move(chess,row,column);
                        return true;
                }

                else {
                        return false;
                }
        }

        // update the map and chess
        public void update_map_after_move(Chess chess, int t_row, int t_column){
                game_map.setChess(t_row, t_column, chess);
                game_map.setChess(chess.getRow(), chess.getColumn(), null);
                chess.setRow(t_row);
                chess.setColumn(t_column);
        }

        // check lion and tiger can jump over the river
        public boolean check_jump_over(Chess start_chess, int c_row,int c_col, int t_row,int t_col){
                //jump hor
                int end_location;
                int target_row;
                if (c_row == t_row ){
                        //change the target location become other side of river
                        if (c_col > t_col){
                                end_location =  t_col-2;
                        }
                        else {
                                 end_location = t_col+2;
                        }
                        
                        int start_location= Math.min(c_row,end_location);
                        Chess target_Chess = game_map.getChess(c_row, end_location);

                        //check there is a rat on any of the intervening water squares.
                        while (start_location+1 < end_location) {
                                if (game_map.getChess(c_row, start_location + 1) != null) {
                                        System.out.println("there are a rat on the river");
                                        return false;
                                }
                                start_location++;
                        }

                        //check capture enemy pieces by such jumping moves.
                        if (target_Chess != null) {
                                if ( start_chess.capture(target_Chess)){
                                        update_map_after_move(start_chess,t_row,end_location);
                                        return true;
                                }
                                else {
                                        return false;
                                }
                        }
                        update_map_after_move(start_chess,t_row,end_location);
                        return true;
                }


                //jump vert
                else if(c_col == t_col ){
                        if (c_row > t_row){
                                end_location =  t_row-3;
                        }
                        else {
                                end_location = t_row+3;
                        }

                        int start_location= Math.min(c_row,end_location);
                        Chess target_Chess = game_map.getChess(end_location, t_col);

                        while (start_location+1 < end_location) {
                                if (game_map.getChess(start_location+1, c_col) != null) {
                                        System.out.println("there are a rat on the river");
                                        return false;
                                }
                                start_location++;
                        }
                        if (target_Chess != null) {
                                if (start_chess.capture(target_Chess)){
                                        update_map_after_move(start_chess,end_location,t_col);
                                        return true;
                                }
                                else {
                                        return false;
                                }
                        }
                        update_map_after_move(start_chess,end_location,t_col);
                        return true;
                }
                else{
                        System.out.println("error: unknown error in check_jump_over");
                        return false;
                }

        }

}
