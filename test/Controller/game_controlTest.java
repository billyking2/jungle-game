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
        //test the startGame method with winning condition （player2 get into the dens) and save
        String[] player_name = {"1","2"};
        String userInput = "undo\n" + //test undo at the first step
                "help\n" + //test the help command
                "tiger\n" +
                "back\n" +  //test the back command
                "invalidName\n" + //test wrong animal name input
                "wolf\n" +
                "left\n" + //test invalid move
                "rat\n" +
                "display\n" + //test the display
                "invalidMove\n" + //test invalid move
                "up\n" +

                "wolf \n" +
                "down\n" +
                "rat\n" +
                "up\n" +
                "undo\n"+ //player 1 undo
                "wolf \n" +
                "down\n" +
                "rat\n" +
                "up\n" +
                "undo\n"+
                "wolf \n" +
                "down\n" +
                "rat\n" +
                "up\n" +
                "undo\n"+
                "wolf \n" +
                "down\n" +
                "rat\n" +
                "up\n" +
                "undo\n"+ //test using all undo


                "wolf\n" +
                "DOWN\n" +// test uppercase letter
                "rat\n" +
                "up\n" +
                "wolf\n" +
                "down\n" +
                "rat\n" +
                "up\n" +
                "wolf\n" +
                "left\n" +
                "down\n" +

                "rat\n" +
                "left\n" +
                "wolf\n" +
                "down\n" +
                "undo\n" + //player 2 undo
                "rat\n" +
                "left\n" +
                "wolf\n" +
                "down\n" +

                "rat\n" +
                "right\n" +
                "wolf\n" +
                "down\n" +
                "yes\n" +
                "Rfile2\n";
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
        Path path2 = Paths.get("././record/Rfile2.record");
        Files.deleteIfExists(path2);
    }

    @Test
    void startGame6() throws IOException{
        //test the startGame method with winning condition (player 2 get into the dens) and dont save
        String[] player_name = {"1","2"};
        String userInput ="rat\n" +
                "down\n" +
                "wolf\n" +
                "right\n" +
                "rat\n" +
                "down\n" +
                "wolf\n" +
                "up\n" +
                "rat\n" +
                "down\n" +
                "wolf\n" +
                "up\n" +
                "rat\n" +
                "down\n" +
                "wolf\n" +
                "up\n" +
                "rat\n" +
                "right\n" +
                "wolf\n" +
                "up\n" +
                "rat\n" +
                "left\n" +
                "wolf\n" +
                "up\n" +
                "rat\n" +
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

    @Test
    void startGame7() throws IOException{
        //test the startGame method with winning condition (player 1 eat all the chess) and dont save
        String[] player_name = {"1","2"};

        String userInput =  "rat\nup\n" +
                "elephant\nup\n" +
                "tiger\nleft\n" +
                "elephant\nup\n" +
                "leopard\nleft\n" +
                "elephant\nup\n" +
                "lion\nright\n" +
                "elephant\nup\n" +
                "elephant\nleft\n" +
                "elephant\nright\n" +
                "wolf\nleft\n" +
                "elephant\nup\n" +
                "tiger\nleft\n" +
                "elephant\nleft\n" + //elephant cant eat rat
                "elephant\nup\n" +
                "tiger\ndown\n" +
                "elephant\nright\n" +
                "rat\ndown\n" +
                "elephant\ndown\n" +
                "rat\ndown\n" +
                "elephant\nright\n" +
                "rat\ndown\n" +
                "elephant\ndown\n" +
                "rat\ndown\n" +
                "elephant\nright\n" +
                "rat\ndown\n" +
                "elephant\nup\n" +
                "rat\ndown\n" +
                "elephant\nright\n" +
                "rat\nup\n" +
                "elephant\ndown\n" +
                "rat\ndown\n" +
                "cat\nleft\n" +
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

    @Test
    void startGame8() throws IOException{
        //test the startGame method with winning condition (player 1 eat all the chess) and dont save
        String[] player_name = {"1","2"};

        String userInput =  "elephant\ndown\n" +
                "rat\ndown\n" +
                "elephant\ndown\n" +
                "tiger\nright\n" +
                "elephant\ndown\n" +
                "elephant\nright\n" +
                "elephant\ndown\n" +
                "leopard\nright\n" +
                "elephant\nleft\n" +
                "lion\nleft\n" +
                "elephant\ndown\n" +
                "rat\nup\n" +
                "elephant\ndown\n" +
                "tiger\nright\n" +
                "elephant\nleft\n" +
                "tiger\nup\n" +
                "elephant\nup\n" +
                "rat\nup\n" +
                "elephant\nleft\n" +
                "rat\nup\n" +
                "elephant\nleft\n" +
                "rat\nup\n" +
                "elephant\nleft\n" +
                "rat\nup\n" +
                "elephant\nup\n" +
                "rat\nup\n" +
                "elephant\nright\n" +
                "rat\ndown\n" +
                "wolf\nright\n" +
                "rat\nup\n" +
                "cat\nright\n" +
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



    @Test
    void ask_save_file() throws IOException{
        //test the save file
        String[] player_name = {"1","2"};
        String userInput = "Jfile4";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_file.FileType fileType = game_file.FileType.JUNGLE;
        try {
            my_game_control.ask_save_file(scanner, player_name, fileType);
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void ask_save_file2() throws IOException{
        //test the save file
        String[] player_name = {"1","2"};
        String userInput = "Jfile5";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        game_file.FileType fileType = game_file.FileType.JUNGLE;
        //delete Rfile1 if it exist
        Path path = Paths.get("././jungle/Jfile5.record");
        Files.deleteIfExists(path);
        try {
            my_game_control.ask_save_file(scanner, player_name, fileType);
        }
        catch (NoSuchElementException e){
        }
    }




}
