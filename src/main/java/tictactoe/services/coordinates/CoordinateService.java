package tictactoe.services.coordinates;

import tictactoe.config.models.Coordinates;
import tictactoe.config.utils.CommonValues;
import tictactoe.services.boardmanager.IBoardService;

public class CoordinateService {

    private final IBoardService boardService;
    private Coordinates coordinates;

    public CoordinateService(IBoardService boardService) {
        this.boardService = boardService;
    }

    public void setCoordinates(String coordinatesString) {
        try {
            String[] coordinates = coordinatesString.split(CommonValues.COORDINATE_SEPARATOR);
            int y = Integer.parseInt(coordinates[0]);
            int x = Integer.parseInt(coordinates[1]);
            this.coordinates = new Coordinates(y, x);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Coordinates");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean areNotValidCoordinates() {
        if (this.coordinates == null) {
            throw new IllegalStateException("Coordinates not Set");
        }

        return coordinatesNotOutOfBounds(this.boardService.getBoard(),
                coordinates.getY(),
                coordinates.getX()
        );
    }

    private boolean coordinatesNotOutOfBounds(String[][] board, int y, int x) {
        return (y > board.length - 1 || y < 0) && (x > board.length - 1 || x < 0);
    }
}
