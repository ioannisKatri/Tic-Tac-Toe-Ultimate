package tictactoe.services.boardmanager;

import tictactoe.config.models.Coordinates;
import tictactoe.config.models.Player;

public interface IBoardService {

    void generateBoard();

    void printBoard();

    void setBoardCellValue(Coordinates coordinates, Player player);

    String[][] getBoard();
}
