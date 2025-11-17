package Controller;

import Model.chess;
import Model.move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
        gameFile  = new game_file(player1Name, player2Name, getUniqueFileName(),
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1= new chess("1",1,1,1);
        move move1 = new move();
        move1.setRound(1);
        move1.setMoved_chess(chess1);
        gameFile.record_move(move1);

        chess chess2 = new chess("2",2,2,2);
        move move2 = new move();
        move2.setRound(2);
        move2.setMoved_chess(chess2);
        gameFile.record_move(move2);

        List<move> moves = gameFile.get_moves();
        assertEquals(2, moves.size());
        assertEquals(1, moves.get(0).getRound());
        assertEquals(2, moves.get(1).getRound());


    }


    @Test
    void create_record_from_jungle() throws IOException {
        String File_name= getUniqueFileName();
        gameFile  = new game_file(player1Name, player2Name, File_name,
                game_file.FileType.JUNGLE, takeBackCounters);

        chess chess1 = new chess("rat", 1, 1, 1);
        move move1 = new move(chess1,null,1,1,2,2,1,"tester","success");
        gameFile.record_move(move1);

        // Create record from jungle
        game_file recordFile = game_file.create_record_from_jungle(File_name, File_name);

        assertEquals(player1Name, recordFile.getPlayer1_name());
        assertEquals(player2Name, recordFile.getPlayer2_name());
        assertArrayEquals(takeBackCounters, recordFile.getTakeBackCounters());
        assertEquals(game_file.FileType.RECORD, recordFile.getFileType());
        assertEquals(1, recordFile.get_moves().size());
    }

    @Test
    void get_moves() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        move move1 = new move(chess1, null, 1, 1, 2, 2, 1, "tester", "success");
        gameFile.record_move(move1);


        List<move> moves = gameFile.get_moves();
        assertEquals(1, moves.size());
        assertEquals(1, moves.get(0).getRound());
        assertEquals("rat", moves.get(0).getMoved_chess().getType());

    }

    @Test
    void getPlayer1_name() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        move move1 = new move(chess1, null, 1, 1, 2, 2, 1, "tester", "success");
        gameFile.record_move(move1);

        List<move> moves = gameFile.get_moves();
        assertEquals("tester", moves.get(0).getPlayer_name());

    }

    @Test
    void getPlayer2_name() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);


        chess chess1 = new chess("rat", 1, 1, 1);
        move move1 = new move(chess1, null, 1, 1, 2, 2, 1, "tester1", "success");
        gameFile.record_move(move1);

        chess chess2 = new chess("cat", 2, 2, 2);
        move move2 = new move(chess1, null, 2, 2, 3, 3, 1, "tester2", "success");
        gameFile.record_move(move2);


        List<move> moves = gameFile.get_moves();
        assertEquals("tester2", moves.get(1).getPlayer_name());
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
    }


    @Test
    void getFileType() throws IOException {
        String fileName = getUniqueFileName();
        gameFile = new game_file(player1Name, player2Name, fileName,
                game_file.FileType.JUNGLE, takeBackCounters);

        assertEquals(game_file.FileType.JUNGLE, gameFile.getFileType());

    }
}