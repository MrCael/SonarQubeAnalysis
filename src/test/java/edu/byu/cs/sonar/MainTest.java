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
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(originalOut);
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
}