import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import tictactoe.config.models.Board;
import tictactoe.config.models.Coordinates;
import tictactoe.config.models.Player;
import tictactoe.services.ai.IAi;
import tictactoe.services.ai.SimpleAiService;
import tictactoe.services.boardmanager.BoardService;
import tictactoe.services.boardmanager.IBoardService;
import tictactoe.services.coordinates.CoordinateService;

import static org.junit.Assert.assertEquals;
import static tictactoe.config.utils.CommonValues.CELL_EMPTY_CHARACTER;

public class TestAiService {

    private IBoardService boardService;
    private CoordinateService coordinateService;
    private final String[][] boardArray = new String[3][3];
    private Board board;
    private final Player playerOne = new Player("A", false);
    private final Player playerTwo = new Player("B", false);
    private final Player computerAi = new Player("C", true);

    @Test
    @DisplayName("SimpleAIService occupies the 0,2 cell which is empty if plays third in a 3x3 game")
    public void simpleAiOccupiesCorrectlyACell() {
        this.board = new Board(boardArray);
        this.boardService = new BoardService(board);
        this.coordinateService = new CoordinateService(boardService);

        IAi simpleAi = new SimpleAiService(boardService);
        this.boardService.generateBoard();

        boardService.setBoardCellValue(new Coordinates(0,0), this.playerOne);
        boardService.setBoardCellValue(new Coordinates(0,1), this.playerTwo);

        coordinateService.setCoordinates(simpleAi.generateNextMove());
        coordinateService.areNotValidCoordinates();

        assertEquals(this.boardService.getBoard()[0][2], CELL_EMPTY_CHARACTER);

        this.boardService.setBoardCellValue(this.coordinateService.getCoordinates(), this.computerAi);

        assertEquals(this.boardService.getBoard()[0][2], computerAi.getSymbol());
    }
}
