package java.com.gojek.parkinglot.main;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @org.junit.Test
    public void mainNoFileTest() throws Exception {
        //File Input but no file found
        Main.main(new String[] {"noFile"});
        assertEquals("File not found in the path specified.\n",outContent.toString());
    }

    @org.junit.Test
    public void mainInvalidInputTest() throws Exception {
        //Invalid Input
        Main.main(new String[] {"arg1", "arg2", "arg3"});
        assertEquals("Invalid input. Usage: java -jar <jar_file_path> <input_file_path>\n",outContent.toString());
    }


    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}