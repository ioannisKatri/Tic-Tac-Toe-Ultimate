package tictactoe.config.utils;

import tictactoe.config.models.Coordinates;

import java.util.Arrays;

import static tictactoe.config.utils.CommonValues.CELL_EMPTY_CHARACTER;

public class GameUtils {

    public static boolean isFinished(String[][] board) {
        long containsEmptySymbol = Arrays.stream(board).filter(arr -> Arrays.toString(arr).contains(CELL_EMPTY_CHARACTER)).count();
        return containsEmptySymbol == 0;
    }

    public static boolean isCellEmpty(String[][] board, Coordinates coordinates) {
        return board[coordinates.getY()][coordinates.getX()].equals("_");
    }

    public static boolean verifyIfUserWantsToExit(String str) {
        return str.toLowerCase().equals("exit");
    }
}
