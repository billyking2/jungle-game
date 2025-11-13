package View;

import Model.map;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
class display_interactiveTest {

    @Test
    void display_game_menu() {
        //display game menu
        display_interactive.display_game_menu();
    }

    @Test
    void display_name_menu() {
        //display game menu
        display_interactive.display_name_menu();
    }

    @Test
    void display_record_menu() {
        //display record menu
        display_interactive.display_record_menu();
    }

    @Test
    void setup_recording() {
        String userInput = "yes\nAAAAAA\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        display_interactive myDisplay = new display_interactive();
        myDisplay.setup_recording(scanner);
    }

    @Test
    void setup_recording_wrong_input() {
        String userInput = "wronginput\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        display_interactive myDisplay = new display_interactive();
        myDisplay.setup_recording(scanner);
    }

    @Test
    void display_help() {
        //display help
        display_interactive.display_help();
    }

    @Test
    void display_game_status() {
        //display game status
        display_interactive myDisplay = new display_interactive();
        String[] playerName = {"player1","player2"};
        map myMap = new map();
        myMap.getChess(0,0).setPlayer(3);

        myDisplay.display_game_status(myMap,playerName,1);
        myDisplay.display_game_status(myMap,playerName,2);
    }
}
