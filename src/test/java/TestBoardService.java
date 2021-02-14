import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import tictactoe.config.models.Board;
import tictactoe.config.models.Coordinates;
import tictactoe.config.models.Player;
import tictactoe.services.boardmanager.BoardService;
import tictactoe.services.boardmanager.IBoardService;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static tictactoe.config.utils.CommonValues.CELL_EMPTY_CHARACTER;

public class TestBoardService {

    @Test
    @DisplayName("expects 9 " + CELL_EMPTY_CHARACTER + " in a 3x3 empty array")
    public void expectEmptySymbolInCellsOfInitializedArray() {
        String[][] boardArray = new String[3][3];
        Board board = new Board(boardArray);
        IBoardService boardService = new BoardService(board);
        boardService.generateBoard();

        long total = 0;

        for (String[] strings : boardArray) {
            long count = Arrays.stream(strings)
                    .filter(c -> c.equals(CELL_EMPTY_CHARACTER))
                    .count();
            total += count;
        }

        assertEquals(9, total);
    }

    @Test
    @DisplayName("Expects 0,0 to be equal to A when testing setBoardCellValue()")
    public void expectArrayCellToContainCorrectValue() {
        String[][] boardArray = new String[3][3];
        Board board = new Board(boardArray);
        IBoardService boardService = new BoardService(board);
        boardService.generateBoard();
        Player player = new Player("A", false);

        boardService.setBoardCellValue(new Coordinates(0,0), player);

        assertEquals(boardService.getBoard()[0][0], player.getSymbol());

    }
}
