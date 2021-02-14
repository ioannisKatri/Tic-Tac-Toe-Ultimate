package tictactoe.services.winnercalculator;

import tictactoe.config.models.Player;
import tictactoe.services.boardmanager.IBoardService;
import tictactoe.config.GameConfiguration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WinnerCalculatorService implements IWinnerCalculator {
    private final IBoardService boardService;
    private final Map<String, Player> allSymbols;

    public WinnerCalculatorService(IBoardService boardService, GameConfiguration configuration) {
        this.boardService = boardService;
        this.allSymbols = Arrays.stream(configuration.getPlayers())
                .collect(Collectors.toMap(Player::getSymbol, p -> p));
    }

    @Override
    public String findWinner() {
        String[][] board = this.boardService.getBoard();
        int row;
        int column;
        String current;
        String next;

        for (int i = 0; i < board.length; i++) {

            if (i == 0) {
                row = 1;
                column = 1;
                current = board[0][0];
                next = board[1][1];

                if (containsSymbol(board[0][0])
                        && isWinnerDiagonalTopLeftBottomRight(row, column, current, next, 0)) {
                    return board[0][0];
                }

                row = board.length - 2;
                column = 1;
                current = board[board.length - 1][0];
                next = board[board.length - 2][1];

                if (containsSymbol(board[board.length - 1][0])
                        && isWinnerDiagonalBottomLeftTopRight(row, column, current, next, 0)) {
                    return board[board.length - 1][0];
                }
            }

            row = i;
            column = 1;
            current = board[i][0];
            next = board[i][1];

            if (containsSymbol(board[i][0])
                    && isWinnerHorizontal(row, column, current, next, 0)) {
                return board[i][0];
            }

            row = 1;
            column = i;
            current = board[0][i];
            next = board[1][i];

            if (containsSymbol(board[0][i])
                    && isWinnerVertical(row, column, current, next, 0)) {
                return board[0][i];
            }
        }
        return null;
    }

    private boolean containsSymbol(String symbol) {
        return allSymbols.containsKey(symbol);
    }

    private boolean isWinnerDiagonalBottomLeftTopRight(int row, int column, String previous, String current, int count) {
        String[][] board = this.boardService.getBoard();
        boolean endOfColumn = column == board.length - 1;
        boolean endOfRow = row == 0;
        boolean isPreviousEqualWithCurrent = previous.equals(current);

        if (isPreviousEqualWithCurrent) {
            count += 1;
            column += 1;
            row -= 1;
            if (!endOfColumn && !endOfRow) {
                return isWinnerDiagonalBottomLeftTopRight(row, column, current, board[row][column], count);
            }
        }
        return count == this.boardService.getBoard().length - 1;
    }

    private boolean isWinnerDiagonalTopLeftBottomRight(int row, int column, String previous, String current, int count) {
        String[][] board = this.boardService.getBoard();
        boolean endOfColumn = column == board.length - 1;
        boolean endOfRow = row == board.length - 1;
        boolean isPreviousEqualWithCurrent = previous.equals(current);

        if (isPreviousEqualWithCurrent) {
            count += 1;
            column += 1;
            row += 1;
            if (!endOfColumn && !endOfRow) {
                return isWinnerDiagonalTopLeftBottomRight(row, column, current, board[row][column], count);
            }
        }
        return count == board.length - 1;
    }

    private boolean isWinnerHorizontal(int row, int column, String previous, String current, int count) {
        String[][] board = this.boardService.getBoard();
        boolean endOfColumn = column == board.length - 1;
        boolean isPreviousEqualWithCurrent = previous.equals(current);

        if (isPreviousEqualWithCurrent) {
            count += 1;
            column += 1;

            if (!endOfColumn) {
                return isWinnerHorizontal(row, column, current, board[row][column], count);
            }
        }
        return count == board.length - 1;
    }

    private boolean isWinnerVertical(int row, int column, String previous, String current, int count) {
        String[][] board = this.boardService.getBoard();
        boolean endOfRow = row == board.length - 1;
        boolean isPreviousEqualWithCurrent = previous.equals(current);
        if (isPreviousEqualWithCurrent) {
            count += 1;
            row += 1;

            if (!endOfRow) {
                return isWinnerVertical(row, column, current, board[row][column], count);
            }
        }
        return count == board.length - 1;
    }
}
