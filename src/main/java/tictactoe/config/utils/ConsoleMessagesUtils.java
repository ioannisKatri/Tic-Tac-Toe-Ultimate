package tictactoe.config.utils;

import static tictactoe.config.logging.LoggingMessages.*;

public class ConsoleMessagesUtils {

    public static void enterCoordinates(String message) {
        System.out.print(ENTER_THE_COORDINATES + "  Player:" + message);
    }

    public static void enterANumberBetween(int number) {
        System.out.println(ENTER_NUMBER_BETWEEN + number);
    }

    public static void theCellIsOccupied() {
        System.out.println(CELL_OCCUPIED);
    }

}


