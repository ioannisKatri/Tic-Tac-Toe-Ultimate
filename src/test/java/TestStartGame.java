import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import tictactoe.StartGame;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TestStartGame {

    private ByteArrayOutputStream testOut;

    @Test
    @DisplayName("Expects Player C to win the game in a 3x3 player game first row")
    public void computerWinsTheGameInFirstRow() {
        initialize("1,0 \n 1,1 \n 1,2 \n 2,0 \n 2,1  \n 2,2");
        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        String zeroCommaZeroCell = consoleOutputArray[consoleOutputArray.length - 5].split(" ")[1];
        String oneCommaOneCell = consoleOutputArray[consoleOutputArray.length - 5].split(" ")[2];
        String twoCommaTwoCell = consoleOutputArray[consoleOutputArray.length - 5].split(" ")[3];

        assertEquals("C Win", winner);
        assertEquals("CCC", zeroCommaZeroCell + oneCommaOneCell + twoCommaTwoCell);

        assertEquals("FINISHED", finished);
    }

    @Test
    @DisplayName("Expects Player $ to win the game in a 3x3 player game Diagonal top left to bottom right")
    public void humanPlayer$WinsTheGameDiagonal() {
        initialize("0,0 \n 1,0 \n 1,1 \n 2,0 \n 2,2");

        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        String zeroCommaZeroCell = consoleOutputArray[consoleOutputArray.length - 5].split(" ")[1];
        String oneCommaOneCell = consoleOutputArray[consoleOutputArray.length - 4].split(" ")[2];
        String twoCommaTwoCell = consoleOutputArray[consoleOutputArray.length - 3].split(" ")[3];

        assertEquals("$$$", zeroCommaZeroCell + oneCommaOneCell + twoCommaTwoCell);
        assertEquals("$ Win", winner);
        assertEquals("FINISHED", finished);
    }

    @Test
    @DisplayName("Expects Player $ to win the game in a 3x3 player game Diagonal top left to bottom right")
    public void humanPlayerGWinsTheGameVertical() {
        initialize("0,0 \n 0,2 \n 1,0 \n 1,2 \n 2,1, \n 2,2");

        String output = testOut.toString();
        String[] consoleOutputArray = output.trim().split("\n");
        String finished = consoleOutputArray[consoleOutputArray.length - 1];
        String winner = consoleOutputArray[consoleOutputArray.length - 2];

        String zeroCommaZeroCell = consoleOutputArray[consoleOutputArray.length - 5].split(" ")[3];
        String oneCommaOneCell = consoleOutputArray[consoleOutputArray.length - 4].split(" ")[3];
        String twoCommaTwoCell = consoleOutputArray[consoleOutputArray.length - 3].split(" ")[3];

        assertEquals("GGG", zeroCommaZeroCell + oneCommaOneCell + twoCommaTwoCell);
        assertEquals("G Win", winner);
        assertEquals("FINISHED", finished);
    }

    private void initialize(String testString){
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
