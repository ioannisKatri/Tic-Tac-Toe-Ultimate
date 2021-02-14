package tictactoe;

import tictactoe.config.utils.GameUtils;

import tictactoe.services.boardmanager.IBoardService;
import tictactoe.services.coordinates.CoordinateService;
import tictactoe.services.playermanager.IPlayerManager;
import tictactoe.config.utils.ConsoleMessagesUtils;
import tictactoe.services.statemanager.IStateManager;

public class TicTacToe {

    private final IPlayerManager playerManagerService;
    private final IBoardService boardService;
    private final IStateManager stateManagerService;
    private final CoordinateService coordinateService;

    public TicTacToe(final IPlayerManager playerManagerService,
                     final IBoardService boardService,
                     final IStateManager stateManagerService,
                     final CoordinateService coordinateService) {
        this.playerManagerService = playerManagerService;
        this.boardService = boardService;
        this.stateManagerService = stateManagerService;
        this.coordinateService = coordinateService;
    }

    public void initialize() {
        boardService.generateBoard();
        boardService.printBoard();

        ConsoleMessagesUtils.enterCoordinates(playerManagerService.getCurrentPlayer().getSymbol());
    }

    public boolean play() {
        String[][] board = this.boardService.getBoard();

        try {
            System.out.println();
            String playerInput = this.playerManagerService.getPlayerNextMove();
            verifyIfUserWantsToExit(playerInput);
            coordinateService.setCoordinates(playerInput);

            if (coordinateService.areNotValidCoordinates()) {
                ConsoleMessagesUtils.enterANumberBetween(board.length - 1);
                this.boardService.printBoard();
                return false;
            }
            boolean isCellEmpty = GameUtils.isCellEmpty(board, coordinateService.getCoordinates());

            if (!isCellEmpty) {
                ConsoleMessagesUtils.theCellIsOccupied();
            } else {
                this.boardService.setBoardCellValue(coordinateService.getCoordinates(), this.playerManagerService.getCurrentPlayer());
                this.boardService.printBoard();
                boolean hasFinished = this.stateManagerService.identifyCurrentState();
                if (hasFinished) {
                    return true;
                }
                this.playerManagerService.pointToNextPlayer();
                return false;
            }
            ConsoleMessagesUtils.enterCoordinates(playerManagerService.getCurrentPlayer().getSymbol());

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e ) {
            ConsoleMessagesUtils.enterANumberBetween(board.length - 1);
        }
        return false;
    }

    private void verifyIfUserWantsToExit(String str) {
        if (GameUtils.verifyIfUserWantsToExit(str)) {
            System.exit(0);
        }
    }
}