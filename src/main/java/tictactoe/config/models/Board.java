package tictactoe.config.models;

public class Board {

    private final String[][] board;

    /**
     * Accepts an Equal size String Array
     */
    public Board(String[][] board) {
        this.board = board;
    }

    public String[][] getBoard() {
        return board;
    }
}
