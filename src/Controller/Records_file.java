package Controller;

import Model.move;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Records_file {
    private List<move> moves;
    private String player1_name;
    private String player2_name;
    private File recordFile;
    private boolean recordingEnabled;


    public Records_file(String player1_name, String player2_name, String fileName) throws IOException {
        this.player1_name = player1_name;
        this.player2_name = player2_name;
        this.moves = new ArrayList<>();
        this.recordingEnabled = true;

        if (fileName != null && !fileName.trim().isEmpty()) {
            this.recordFile = create_file(fileName);
            write_opening();
        } else {
            this.recordingEnabled = false;
            System.out.println("Recording disabled - no filename provided");
        }
    }

    public File create_file(String fileName) throws IOException {
        String dirName = "record";
        File dir = new File(dirName);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new IOException("Failed to create record directory");
            }
        }

        String name = fileName + ".record";
        File file = new File(dir, name);
        if (!file.createNewFile()) {
            throw new IOException("Record file already exists or cannot be created: " + name);
        }
        return file;
    }

    private void write_opening() throws IOException {
        if (!recordingEnabled) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(recordFile, true))) {
            writer.println("Player1:" + player1_name);
            writer.println("Player2:" + player2_name);
            writer.println("Start");
        }
    }

    public void write_ending() throws IOException {
        if (!recordingEnabled) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(recordFile, true))) {
            writer.println("END");
        }
    }

    public void record_move(move move) {
        moves.add(move);
        if (recordingEnabled) {
            writeMoveToFile(move);
        }
    }

    private void writeMoveToFile(move move) {
        if (!recordingEnabled) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(recordFile, true))) {
            writer.println(move.record_success());
        } catch (IOException e) {
            System.out.println("Error writing move to record file: " + e.getMessage());
        }
    }

    public static List<move> load_record_file(String filename) throws IOException {
        List<move> moves = new ArrayList<>();
        File file = new File("record", filename + ".record");

        if (!file.exists()) {
            throw new IOException("Record file not found: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {


                if ( line.startsWith("Round")) {
                    move move = read_record_file(line);
                    if (move != null) {
                        moves.add(move);
                    }
                }

                if (line.equals("END")) {
                    break;
                }
            }
        }

        return moves;
    }

    public static move read_record_file(String line ) {
        String[] key_words = {"Round", "player", "moved", "from", "to", "captured", "result"};
        move moves=new move();
        int index_1 = 0;

        for (String keyword : key_words) {
            int keywordStart = line.indexOf(keyword, index_1);
            if (keywordStart == -1) continue;

            int valueStart = keywordStart + keyword.length();

            int valueEnd = line.indexOf(",", valueStart);
            if (valueEnd == -1) continue;

            String value = line.substring(valueStart, valueEnd).trim();

            if(keyword.equals("player")){
                moves.setPlayer_name(value);
            }
            else if (keyword.equals("Round")){
                moves.setRound(Integer.parseInt(value));
            }
            else if (keyword.equals("from")){
                moves.setFromRow(Integer.parseInt(value.substring(0,1)));
                moves.setFromCol(Integer.parseInt(value.substring(1)));
            }
            else if (keyword.equals("to")){
                moves.setToRow(Integer.parseInt(value.substring(0,1)));
                moves.setToCol(Integer.parseInt(value.substring(1)));
            } else if (keyword.equals("result")) {
                moves.setResult(value);
            }
            index_1 = valueEnd + 1;
        }

        return moves;
    }

    public List<move> getMoves() {
        return moves;
    }

    public void setMoves(List<move> moves) {
        this.moves = moves;
    }

    public String getPlayer1_name() {
        return player1_name;
    }

    public void setPlayer1_name(String player1_name) {
        this.player1_name = player1_name;
    }

    public String getPlayer2_name() {
        return player2_name;
    }

    public void setPlayer2_name(String player2_name) {
        this.player2_name = player2_name;
    }

    public File getRecordFile() {
        return recordFile;
    }

    public void setRecordFile(File recordFile) {
        this.recordFile = recordFile;
    }

    public boolean isRecordingEnabled() {
        return recordingEnabled;
    }

    public void setRecordingEnabled(boolean recordingEnabled) {
        this.recordingEnabled = recordingEnabled;
    }

    public Records_file() {
    }

    public Records_file(List<move> moves, String player1_name, String player2_name, File recordFile, boolean recordingEnabled) {
        this.moves = moves;
        this.player1_name = player1_name;
        this.player2_name = player2_name;
        this.recordFile = recordFile;
        this.recordingEnabled = recordingEnabled;
    }
}