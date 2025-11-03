package test;
import org.junit.Test;
import Controller.Records_file;

import java.io.File;
import java.io.IOException;

public class test {

    @Test
    public void test_create_file_with_no_dir() throws IOException {
        Records_file recordsFile = new Records_file();
        File file1 = recordsFile.create_file("test1");
        System.out.println("File exists: " + file1.exists());
        System.out.println("File name: " + file1.getName());
    }

    @Test
    public void test_create_file_with_dir() throws IOException {
        Records_file recordsFile = new Records_file();
        File file2 = recordsFile.create_file("test2");
        System.out.println("File exists: " +file2.exists());
        System.out.println("File name: " + file2.getName());
    }

    @Test
    public void test_record_write(){

    }
}
