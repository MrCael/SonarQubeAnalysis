package edu.byu.cs.sonar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void testMain() {
        String[] args = {"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};

        assertDoesNotThrow(() -> Main.main(args));
    }

    @Test
    void testMain_testOutput() {
      String[] args = {"readMe1.txt", "readMe2.txt", "readMe3.txt", "5"};
      String expected = "was times was It It \n";

      Main.main(args);

      assertEquals(expected, outContent.toString());
    }

    @Test
    void testMain_testError() {
      String[] args = {"readMe5.txt", "readMe2.txt", "readMe3.txt", "5"};
      String expected = "Did not find dictionary file. readMe5.txt (No such file or directory)\n";

      Main.main(args);

      assertEquals(expected, errContent.toString());
    }
}