package Controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

class game_controlTest {
    game_control my_game_control;

    @BeforeEach
    void setup(){
        this.my_game_control = new game_control();
    }

    @Test
    void continue_game1 () throws IOException, InterruptedException {
        //Testing continue the game from a saved .jungle file
        String userInput = "no\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        try{
            my_game_control.continue_game(scanner, "Jfile1");
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void continue_game2 () throws IOException, InterruptedException {
        //Testing continue the game from a saved .jungle file
        String userInput = "no\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        try{
            my_game_control.continue_game(scanner, "Jfile2");
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void continue_game3 () throws IOException, InterruptedException {
        //Testing continue the game from a saved .jungle file
        String userInput = "no\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        try{
            my_game_control.continue_game(scanner, "Jfile3");
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void startGame1() throws IOException{
        //test the startGame method
        String[] player_name = {"1","2"};
        String userInput = "";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = "Rfile1";
        String jungle_file_name = null;
        boolean continue_record = false;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }

    @Test
    void startGame2() throws IOException{
        //test the startGame method
        String[] player_name = {"1","2"};
        String userInput = "";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = "Rfile1";
        String jungle_file_name = "Jfile1";
        boolean continue_record = true;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }

    @Test
    void startGame3() throws IOException{
        //test the startGame method
        String[] player_name = {"1","2"};
        String userInput = "";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = "Rfile1";
        String jungle_file_name = "Jfile1";
        boolean continue_record = false;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }

    @Test
    void startGame4() throws IOException{
        //test the startGame method
        String[] player_name = {"1","2"};
        String userInput = "";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = "Rfile1";
        String jungle_file_name = null;
        boolean continue_record = true;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }




    @Test
    void startGame5() throws IOException{
        //test the startGame method with winning condition and save
        String[] player_name = {"1","2"};
        String userInput = "undo\n" +
                "wolf\n" +
                "right\n" +
                "cat\n" +
                "back\n" +
                "WrongName\n" +
                "wolf\n" +
                "wrongDirection\n" +
                "right\n" +
                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "down\n" +
                "wolf\n" +
                "up\n" +
                "yes\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = "Rfile1";
        String jungle_file_name = null;
        boolean continue_record = false;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }

    @Test
    void startGame6() throws IOException{
        //test the startGame method with winning condition and dont save
        String[] player_name = {"1","2"};
        String userInput = "wolf\n" +
                "right\n" +
                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "back\n"+

                "wolf\n" +
                "display\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +

                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "undo\n" +
                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +

                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "undo\n" +
                "wolf\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +

                "wolf\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "no\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_control my_game_control = new game_control();
        String record_name = null;
        String jungle_file_name = null;
        boolean continue_record = false;

        //delete Rfile1 if it exist
        Path path = Paths.get("././record/Rfile1.record");
        Files.deleteIfExists(path);

        try{
            my_game_control.startGame(player_name,scanner,record_name,jungle_file_name,continue_record);
        }
        catch (NoSuchElementException e){

        }
    }


}
