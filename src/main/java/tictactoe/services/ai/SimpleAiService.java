package tictactoe.services.ai;


import tictactoe.config.utils.CommonValues;
import tictactoe.services.boardmanager.IBoardService;

import static tictactoe.config.utils.CommonValues.CELL_EMPTY_CHARACTER;

public class SimpleAiService implements IAi {

    private final IBoardService boardService;

    public SimpleAiService(IBoardService boardService) {
        this.boardService = boardService;
    }

    @Override
    public String generateNextMove() {
        for (int i = 0; i < boardService.getBoard().length; i++) {
            for (int y = 0; y < boardService.getBoard()[i].length; y++) {
                if (CELL_EMPTY_CHARACTER.equals(boardService.getBoard()[i][y])) {
                    System.out.println(i + " " + y);
                    return i + CommonValues.COORDINATE_SEPARATOR + y;
                }
            }
        }
        return null;
    }
}
