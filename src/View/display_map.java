package View;
import Model.map;
import Model.chess;

public class display_map {
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";
    public static final String PURPLE = "\u001B[35m";
    private map game_map;

    public display_map(map game_map){
        this.game_map= game_map;
    }

    public void display_map() {
        String separator_line = "--------------------------------------------------";
        String string_line = "|";
        String space_3 = "   ";
        String space_2 = "  ";


        System.out.println(separator_line);
        for (int row = 0; row < 9; row++) {
            System.out.println();
            System.out.print(string_line);

            for (int col = 0; col < 7; col++) {
                chess chess = game_map.getChess(row, col);
                String cell ;

            if (chess != null){
                cell= trans_chess(chess.getType());
                System.out.print(space_2+colour_chess(cell, chess.getPlayer())+space_2+string_line);
            }
            else if (game_map.check_Dens(row, col)) {
                System.out.print(space_2+colour_den()+space_2+string_line);
            } else if (game_map.check_Trap(row, col)) {
                System.out.print(space_2+colour_trap()+space_2+string_line);
            } else if (game_map.check_River(row, col)) {
                System.out.print(space_2+colour_river()+space_2+string_line);
            }else{
                System.out.print(space_3+space_3+string_line);
            }

            }

            if (row < 8) {
                System.out.println();
                System.out.println(separator_line);
            }
        }
        System.out.println();
        System.out.println(separator_line);
    }

    //change the name from en to cn
    private String trans_chess(String type) {
        switch (type.toLowerCase()) {
            case "elephant": return "象";
            case "lion": return "獅";
            case "tiger": return "虎";
            case "leopard": return "豹";
            case "wolf": return "狼";
            case "dog": return "狗";
            case "cat": return "貓";
            case "rat": return "鼠";
            default: return "?";
        }
    }

    //add the colour for display
    public String colour_chess(String transed_chess, int player){
        if (player == 1){
            return RED+transed_chess+RESET;
        }
        else {
            return BLUE+transed_chess+RESET;
        }
    }
    public String colour_trap(){
        return GREEN+"阱"+RESET;
    }
    public String colour_den(){
        return YELLOW+"穴"+RESET;
    }
    public String colour_river(){
        return PURPLE+"水"+RESET;
    }
}
