package tictactoe.services.winnercalculator;

import tictactoe.config.models.Player;
import tictactoe.services.boardmanager.IBoardService;
import tictactoe.config.GameConfiguration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class WinnerCalculatorService implements IWinnerCalculator{
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

        for (int i = 0; i < board.length; i++) {
            if (i == 0) {
                if (containsSymbol(board[0][0])
                        && isWinnerDiagonalTopLeftBottomRight(1, 1, board[0][0], board[1][1], 0)) {
                    return board[0][0];
                }

                if (containsSymbol(board[board.length - 1][0])
                        && isWinnerDiagonalBottomLeftTopRight( board.length - 2, 1, board[board.length - 1][0], board[board.length - 2][1], 0)) {
                    return board[board.length - 1][0];
                }
            }

            if (containsSymbol(board[i][0])
                    && isWinnerHorizontal(i, 1, board[i][0], board[i][1], 0)) {
                return board[i][0];
            }
            if (containsSymbol(board[0][i])
                    && isWinnerVertical(1, i, board[0][i], board[1][i], 0)) {
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
