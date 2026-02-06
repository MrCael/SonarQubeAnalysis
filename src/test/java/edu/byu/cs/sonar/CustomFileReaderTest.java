package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void testSetNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testEquals_negative_nullObject() {
        boolean actual = sut == null;
        assertFalse(actual);
    }

    @Test
    void testEquals_negative_differentSentence() {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.setNewSentence("This is my new sentence");

        assertNotEquals(sut, other);
    }

    @Test
    void testEquals_negative_differentCount() throws FileNotFoundException {
        CustomFileReader other = new CustomFileReader("readMe1.txt");

        sut.howManyWordsInFile();

        other.count = 5;
        other.howManyWordsInFile();

        assertNotEquals(sut, other);
    }

    @Test
    void testEquals_positive() {
        assertEquals(new CustomFileReader("readMe1.txt"), sut);
    }
}