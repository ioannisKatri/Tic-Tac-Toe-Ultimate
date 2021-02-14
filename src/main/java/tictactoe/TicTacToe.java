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
            boolean success = coordinateService.setCoordinates(playerInput);
            boolean isCellEmpty = GameUtils.isCellEmpty(board, coordinateService.getCoordinates());

            if (!success) {
                printErrorMessageAndBoard(board);
                return false;
            }

            if (!isCellEmpty) {
                ConsoleMessagesUtils.theCellIsOccupied();
                this.boardService.printBoard();
            } else {
                this.boardService.setBoardCellValue(coordinateService.getCoordinates(), this.playerManagerService.getCurrentPlayer());
                this.boardService.printBoard();
                boolean hasFinished = this.stateManagerService.identifyCurrentState();
                if (hasFinished) {
                    return true;
                }
                this.playerManagerService.pointToNextPlayer();
            }
            ConsoleMessagesUtils.enterCoordinates(playerManagerService.getCurrentPlayer().getSymbol());

        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e ) {
            printErrorMessageAndBoard(board);
        }
        return false;
    }

    private void printErrorMessageAndBoard(String[][] board){
        ConsoleMessagesUtils.enterANumberBetween(board.length - 1);
        System.out.println();
        this.boardService.printBoard();
        ConsoleMessagesUtils.enterCoordinates(playerManagerService.getCurrentPlayer().getSymbol());
    }

    private void verifyIfUserWantsToExit(String str) {
        if (GameUtils.verifyIfUserWantsToExit(str)) {
            System.exit(0);
        }
    }
}