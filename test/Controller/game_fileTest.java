package Controller;

import Model.chess;
import Model.moveLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class game_fileTest {

    @TempDir
    Path tempDir;

    private game_file gameFile;
    private String testFileName = "testGame";
    private String player1Name = "Player1";
    private String player2Name = "Player2";
    private int[] takeBackCounters = {2, 3};
    private int fileCounter=0;
    @BeforeEach
    void setUp() throws IOException {
        System.setProperty("user.dir", tempDir.toString());
    }

    private String getUniqueFileName() {
        return "testFile_"+System.currentTimeMillis();
    }


    @Test
    void record_move() throws IOException {
        String uniqueFileName = getUniqueFileName();
        gameFile  = new game_file(player1Name, player2Name, uniqueFileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1= new chess("1",1,1,1);
        moveLog moveLog1 = new moveLog();
        moveLog1.setRound(1);
        moveLog1.setMoved_chess(chess1);
        gameFile.record_move(moveLog1);

        chess chess2 = new chess("2",2,2,2);
        moveLog moveLog2 = new moveLog();
        moveLog2.setRound(2);
        moveLog2.setMoved_chess(chess2);
        gameFile.record_move(moveLog2);

        List<moveLog> moveLogs = gameFile.get_moves();
        assertEquals(2, moveLogs.size());
        assertEquals(1, moveLogs.get(0).getRound());
        assertEquals(2, moveLogs.get(1).getRound());

        Path path = Paths.get("././jungle/" + uniqueFileName + ".jungle");
        Files.deleteIfExists(path);
    }


    @Test
    void create_record_from_jungle() throws IOException {
        String File_name= getUniqueFileName();
        gameFile  = new game_file(player1Name, player2Name, File_name,
                game_file.FileType.JUNGLE, takeBackCounters);

        chess chess1 = new chess("rat", 1, 1, 1);
        moveLog moveLog1 = new moveLog(chess1,null,1,1,2,2,1,"tester","success");
        gameFile.record_move(moveLog1);

        // Create record from jungle
        game_file recordFile = game_file.create_record_from_jungle(File_name, File_name);

        assertEquals(player1Name, recordFile.getPlayer1_name());
        assertEquals(player2Name, recordFile.getPlayer2_name());
        assertArrayEquals(takeBackCounters, recordFile.getTakeBackCounters());
        assertEquals(game_file.FileType.RECORD, recordFile.getFileType());
        assertEquals(1, recordFile.get_moves().size());

        Path path1 = Paths.get("././record/" + File_name + ".record");
        Files.deleteIfExists(path1);

        Path path2 = Paths.get("././jungle/" + File_name + ".jungle");
        Files.deleteIfExists(path2);
    }

    @Test
    void get_moves() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        moveLog moveLog1 = new moveLog(chess1, null, 1, 1, 2, 2, 1, "tester", "success");
        gameFile.record_move(moveLog1);


        List<moveLog> moveLogs = gameFile.get_moves();
        assertEquals(1, moveLogs.size());
        assertEquals(1, moveLogs.get(0).getRound());
        assertEquals("rat", moveLogs.get(0).getMoved_chess().getType());

        Path path = Paths.get("././jungle/" + fileName + ".jungle");
        Files.deleteIfExists(path);
    }

    @Test
    void getPlayer1_name() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        moveLog moveLog1 = new moveLog(chess1, null, 1, 1, 2, 2, 1, "tester", "success");
        gameFile.record_move(moveLog1);

        List<moveLog> moveLogs = gameFile.get_moves();
        assertEquals("tester", moveLogs.get(0).getPlayer_name());

        Path path = Paths.get("././jungle/" + fileName + ".jungle");
        Files.deleteIfExists(path);
    }

    @Test
    void getPlayer2_name() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        moveLog moveLog1 = new moveLog(chess1, null, 1, 1, 2, 2, 1, "tester1", "success");
        gameFile.record_move(moveLog1);

        chess chess2 = new chess("cat", 2, 2, 2);
        moveLog moveLog2 = new moveLog(chess1, null, 2, 2, 3, 3, 1, "tester2", "success");
        gameFile.record_move(moveLog2);


        List<moveLog> moveLogs = gameFile.get_moves();
        assertEquals("tester2", moveLogs.get(1).getPlayer_name());

        Path path = Paths.get("././jungle/" + fileName + ".jungle");
        Files.deleteIfExists(path);
    }

    @Test
    void getTakeBackCounters() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);

        int[] counters = gameFile.getTakeBackCounters();
        assertArrayEquals(takeBackCounters, counters);
        assertEquals(2, counters[0]);
        assertEquals(3, counters[1]);

        Path path = Paths.get("././jungle/" + fileName + ".jungle");
        Files.deleteIfExists(path);
    }


    @Test
    void getFileType() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);

        assertEquals(game_file.FileType.JUNGLE, gameFile.getFileType());

        Path path = Paths.get("././jungle/" + fileName + ".jungle");
        Files.deleteIfExists(path);
    }
}