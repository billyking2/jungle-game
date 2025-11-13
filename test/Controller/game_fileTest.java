package Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class game_fileTest {

    @TempDir
    Path tempDir;

    private game_file gameFile;
    private String testFileName = "testGame";
    private String player1Name = "Player1";
    private String player2Name = "Player2";
    private int[] takeBackCounters = {2, 3};

    @BeforeEach
    void setUp() throws IOException {
        // Override the directory creation to use temp directory for tests
        System.setProperty("user.dir", tempDir.toString());
    }


    @Test
    void record_move() {
    }

    @Test
    void create_record_from_jungle() {
    }

    @Test
    void get_moves() {
    }

    @Test
    void getPlayer1_name() {
    }

    @Test
    void getPlayer2_name() {
    }

    @Test
    void getTakeBackCounters() {
    }

    @Test
    void isEnabled() {
    }

    @Test
    void getFileType() {
    }
}