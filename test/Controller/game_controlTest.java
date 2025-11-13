package Controller;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class player_nameTest {

    @Test
    void setupPlayerNamesByDefault() {
        String userInput = "1\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        player_name.setupPlayerNames(scanner);
    }

    @Test
    void setupPlayerNamesByRandom() {
        String userInput = "2\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        player_name.setupPlayerNames(scanner);
    }

    @Test
    void setupPlayerNamesByPlayer() {
        String userInput = "3\nPLAYER111\nPLAYER222";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        player_name.setupPlayerNames(scanner);
    }

    @Test
    void setupPlayerNamesByDefaultWithWrongInput() {
        String userInput = "wrongInput\n1\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(userKeyboard);
        player_name.setupPlayerNames(scanner);
    }

    @Test
    void random_name() {
    }
}
