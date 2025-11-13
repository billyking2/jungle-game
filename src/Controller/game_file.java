package Controller;

import Model.move;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class game_file {
    public enum FileType {
        JUNGLE, RECORD
    }

    private List<move> moves;
    private String player1_name;
    private String player2_name;
    private File currentFile;
    private FileType fileType;
    private boolean enabled;
    private int[] takeBackCounters;


    public game_file(String player1_name, String player2_name, String fileName,
                     FileType fileType, int[] takeBackCounters) throws IOException {
        this.player1_name = player1_name;
        this.player2_name = player2_name;
        this.moves = new ArrayList<>();
        this.fileType = fileType;
        this.enabled = true;
        this.takeBackCounters = takeBackCounters;

        if (fileName != null && !fileName.trim().isEmpty()) {
            this.currentFile = create_file(fileName, fileType);
            write_opening();
        } else {
            this.enabled = false;
            System.out.println(fileType + " disabled - no filename provided");
        }
    }


    public game_file(String fileName, FileType fileType) throws IOException {
        this.fileType = fileType;
        this.takeBackCounters=new int[2];
        read_file(fileName);
    }

    // create dir or file with type
    private File create_file(String fileName, FileType type) throws IOException {
        String dirName = type == FileType.JUNGLE ? "jungle" : "record";
        String extension = type == FileType.JUNGLE ? ".jungle" : ".record";

        File dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new IOException("Failed to create " + dirName + " directory");
            }
        }

        String name = fileName + extension;
        File file = new File(dir, name);
        if (!file.createNewFile()) {
            throw new IOException(type + " file already exists or cannot be created: " + name);
        }
        return file;
    }

    // write the opening of file
    private void write_opening() throws IOException {
        if (!enabled) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(currentFile, true))) {
            if (fileType == FileType.JUNGLE) {
                writer.println("Player1_name " + player1_name);
                writer.println("Player1_take_back_counter " + takeBackCounters[0]);
                writer.println("Player2_name " + player2_name);
                writer.println("Player2_take_back_counter " + takeBackCounters[1]);
            } else {
                writer.println("Player1:" + player1_name);
                writer.println("Player2:" + player2_name);
            }
            writer.println("Start");
        }
    }

    // call to write the move
    public void record_move(move move) {
        moves.add(move);
        if (enabled) {
            write_move_to_file(move);
        }
    }

    // write the move
    private void write_move_to_file(move move) {
        if (!enabled) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(currentFile, true))) {
            writer.println(move.record_success());
        } catch (IOException e) {
            System.out.println("Error writing move to " + fileType + " file: " + e.getMessage());
        }
    }

    // read the file
    private void read_file(String fileName) throws IOException {
        String dirName = fileType == FileType.JUNGLE ? "jungle" : "record";
        String extension = fileType == FileType.JUNGLE ? ".jungle" : ".record";

        File file = new File(dirName, fileName + extension);
        if (!file.exists()) {
            throw new IOException(fileType + " file not found: " + fileName);
        }

        this.currentFile = file;
        this.moves = load_moves(file);

        if (fileType == FileType.JUNGLE) {
            load_jungle_file(file);
        } else {
            load_record_file(file);
        }
    }

    // load all the move
    private List<move> load_moves(File file) throws IOException {
        List<move> loadedMoves = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Round")) {
                    move move = read_line(line);
                    loadedMoves.add(move);
                }
            }
        }
        return loadedMoves;
    }

    // read the data from special format
    private move read_line(String line) {
        String[] key_words = {"Round", "player", "moved", "from", "to", "captured", "result"};
        move moveObj = new move();
        int index_1 = 0;

        for (String keyword : key_words) {
            int keywordStart = line.indexOf(keyword, index_1);
            if (keywordStart == -1) continue;

            int valueStart = keywordStart + keyword.length();
            int valueEnd = line.indexOf(",", valueStart);
            if (valueEnd == -1) continue;

            String value = line.substring(valueStart, valueEnd).trim();

            switch (keyword) {
                case "player":
                    moveObj.setPlayer_name(value);
                    break;
                case "Round":
                    moveObj.setRound(Integer.parseInt(value));
                    break;
                case "from":
                    moveObj.setFromRow(Integer.parseInt(value.substring(0, 1)));
                    moveObj.setFromCol(Integer.parseInt(value.substring(1)));
                    break;
                case "to":
                    moveObj.setToRow(Integer.parseInt(value.substring(0, 1)));
                    moveObj.setToCol(Integer.parseInt(value.substring(1)));
                    break;
                case "result":
                    moveObj.setResult(value);
                    break;
            }
            index_1 = valueEnd + 1;
        }
        return moveObj;
    }

    // load the data of jungle file
    private void load_jungle_file(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), "Start")) {
                if (line == null) break;

                if (line.startsWith("Player1_name ")) {
                    player1_name = line.substring(13);
                } else if (line.startsWith("Player2_name ")) {
                    player2_name = line.substring(13);
                } else if (line.startsWith("Player1_take_back_counter ")) {
                    takeBackCounters[0] = Integer.parseInt(line.substring(26));
                } else if (line.startsWith("Player2_take_back_counter ")) {
                    takeBackCounters[1] = Integer.parseInt(line.substring(26));
                }
            }
        }
    }

    // load record
    private void load_record_file(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while (!Objects.equals(line = reader.readLine(), "Start")) {
                if (line == null) break;

                if (line.startsWith("Player1:")) {
                    player1_name = line.substring(8);
                } else if (line.startsWith("Player2:")) {
                    player2_name = line.substring(8);
                }
            }
        }
    }

    // when continue the game and want to record, clone jungle file
    public static game_file create_record_from_jungle(String jungleFileName, String recordFileName) throws IOException {

        game_file jungleManager = new game_file(jungleFileName, FileType.JUNGLE);

        game_file record_manager = new game_file(
                jungleManager.getPlayer1_name(),
                jungleManager.getPlayer2_name(),
                recordFileName,
                FileType.RECORD,
                jungleManager.getTakeBackCounters()
        );

        // copy move from jungle file
        for (move m : jungleManager.get_moves()) {
            record_manager.record_move(m);
        }

        return record_manager;
    }


    public List<move> get_moves() { return moves; }
    public String getPlayer1_name() { return player1_name; }
    public String getPlayer2_name() { return player2_name; }
    public int[] getTakeBackCounters() { return takeBackCounters; }
    public FileType getFileType() { return fileType; }
}