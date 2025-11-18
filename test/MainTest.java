import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() throws IOException, InterruptedException{
        String userInput = "WrongInput\n1\n1\nno\n";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(userKeyboard);
        try{
            Main.main(new String[]{});
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void main2() throws IOException, InterruptedException{
        String userInput = "2\nJfile1";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(userKeyboard);
        try{
            Main.main(new String[]{});
        }
        catch (NoSuchElementException e){
        }
    }

    @Test
    void main3() throws IOException, InterruptedException{
        String userInput = "3\nRfile1";
        ByteArrayInputStream userKeyboard = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(userKeyboard);
        try{
            Main.main(new String[]{});
        }
        catch (NoSuchElementException e){
        }
    }

}
