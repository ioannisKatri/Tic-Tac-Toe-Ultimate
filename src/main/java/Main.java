import tictactoe.StartGame;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/resources/config.txt");
        Scanner sc = new Scanner(System.in);
        StartGame startGame = new StartGame(file, sc);
        startGame.initialize();
        startGame.start();
    }
}