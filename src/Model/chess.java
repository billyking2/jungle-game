package Model;

import java.util.Objects;

public class chess {
    private String type;
    private Integer player;
    private Integer row;
    private Integer column;

    // base on type to get the rank (rat =1 , cat=2 .......elephant =8)
    public Integer getrank(String type){
        switch (type.toLowerCase()){
            case ("elephant"):
                return 8;
            case ("lion"):
                return 7;
            case "tiger":
                return 6;
            case "leopard":
                return 5;
            case "wolf":
                return 4;
            case "dog":
                return 3;
            case "cat":
                return 2;
            case "rat":
                return 1;
        }
        System.out.println("error:unknown type");
        return -1;
    }

    //check the condition of capture
    public boolean capture(chess target_chess){
        //same player's chess
        if (Objects.equals(this.getPlayer(), target_chess.getPlayer())){
            System.out.println("error: you can not capture your chess");
            return false;
        }
        //rat can capture elephant
        if(getrank(this.getType()) == 1 && getrank(target_chess.getType())==8){
            System.out.println("your chess "+this.getType()+" success capture "+target_chess.getType());
            return true;
        }
        //elephant cannot capture rat
        if(getrank(this.getType()) == 8 && getrank(target_chess.getType())== 1){
            System.out.println("error: Elephant can not capture rat");
            return false;
        }
        //check the rank to see can capture
        if (getrank(this.getType()) >= getrank(target_chess.getType())) {
            System.out.println("your chess " + this.getType() + " success capture " + target_chess.getType());
            return true;
        }
        else{
                System.out.println("you can not eat that chess");
                return false;
        }

    }


    public chess(String type, Integer player, Integer row, Integer column) {
        this.type = type;
        this.player = player;
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "type='" + type + '\'' +
                ", player=" + player +
                ", row=" + row +
                ", column=" + column +
                '}';
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }



    public chess() {
    }
}
