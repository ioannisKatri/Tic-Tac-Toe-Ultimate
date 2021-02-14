import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import tictactoe.StartGame;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStartGame {

    private ByteArrayOutputStream testOut;

    @Test
    @DisplayName("Expects Computer to win the game in a 3x3 player game first row")
    public void computerWinsTheGameInFirstRow() {
        initialize("1,0 \n 1,1 \n 1,2 \n 2,0 \n 2,1  \n 2,2");
        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        assertTrue(winner.indexOf("Win") > 1);
        assertEquals("FINISHED", finished);
    }

    @Test
    @DisplayName("Expects First Player to win the game in a 3x3 player game Diagonal bottom left to top right")
    public void humanPlayerFirstWinsTheGameDiagonal() {
        initialize("0,2 \n 1,0 \n 1,1 \n 2,2 \n 2,0  \n 2,1");

        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        assertTrue(winner.indexOf("Win") > 1);
        assertEquals("FINISHED", finished);
    }

    @Test
    @DisplayName("Expects Second Player to win the game in a 3x3 player game Vertically")
    public void humanPlayerSecondWinsTheGameVertical() {
        initialize("0,0 \n 0,2 \n 1,0 \n 1,2 \n 2,1, \n 2,2");

        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        assertTrue(winner.indexOf("Win") > 1);
        assertEquals("FINISHED", finished);
    }

    private void initialize(String testString) {
        provideInput(testString);
        File file = new File("src/main/resources/config_test.txt");
        Scanner sc = new Scanner(System.in);
        StartGame startGame = new StartGame(file, sc);
        startGame.initialize();
        setUpOutput();
        startGame.start();
    }

    private void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    private void provideInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }
}
