package tictactoe.services.boardmanager;

import tictactoe.config.models.Board;
import tictactoe.config.models.Coordinates;
import tictactoe.config.models.Player;

import java.util.Arrays;

public class BoardService implements IBoardService{

    private final Board board;

    public BoardService(final Board board) {
        this.board = board;
    }

    @Override
    public void generateBoard() {
        prePopulateCells();
    }

    @Override
    public void printBoard() {
        String[][] board = this.board.getBoard();
        for (String[] strings : board) {
            System.out.print("| ");
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.print("|\n");
        }
    }

    @Override
    public void setBoardCellValue(Coordinates coordinates, Player player) {
        String[][] board = this.board.getBoard();
        board[coordinates.getY()][coordinates.getX()] = player.getSymbol();
    }

    @Override
    public String[][] getBoard() {
        return this.board.getBoard();
    }

    private void prePopulateCells() {
        String[][] board = this.board.getBoard();

        for (String[] strings : board) {
            Arrays.fill(strings, "_");
        }
    }
}
