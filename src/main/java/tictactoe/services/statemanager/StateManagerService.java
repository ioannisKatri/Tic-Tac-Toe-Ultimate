package tictactoe.services.statemanager;

import tictactoe.config.enums.GameState;
import tictactoe.config.utils.GameUtils;
import tictactoe.services.boardmanager.IBoardService;
import tictactoe.services.playermanager.IPlayerManager;
import tictactoe.services.winnercalculator.IWinnerCalculator;

public class StateManagerService implements IStateManager {

    private boolean gameHasFinished;
    private final IPlayerManager playerManagerService;
    private final IWinnerCalculator winnerCalculatorService;
    private final IBoardService boardService;

    public StateManagerService(final IPlayerManager playerManagerService,
                               final IWinnerCalculator winnerCalculatorService,
                               final IBoardService boardService) {
        this.playerManagerService = playerManagerService;
        this.winnerCalculatorService = winnerCalculatorService;
        this.boardService = boardService;
        this.gameHasFinished = false;
    }

    @Override
    public boolean identifyCurrentState() {
        String gameStatus = "";
        GameState gameState = identifyStateHelper();

        if (gameState == GameState.GAME_NOT_FINISHED) {
            gameStatus = GameState.GAME_NOT_FINISHED.getName();
        } else if (gameState == GameState.DRAW) {
            gameStatus = GameState.DRAW.getName();
            this.gameHasFinished = true;
        } else if (gameState == GameState.WIN) {
            gameStatus = this.playerManagerService.getCurrentPlayer().getSymbol() + " " + GameState.WIN.getName();
            this.gameHasFinished = true;
        }
        if (this.gameHasFinished) {
            System.out.println(gameStatus);
            return true;
        }
        return false;
    }

    private GameState identifyStateHelper() {
        String winner = winnerCalculatorService.findWinner();
        GameState gameState;
        if ((winner != null)) {
            gameState = GameState.WIN;
        } else if (GameUtils.isFinished(this.boardService.getBoard())) {
            gameState = GameState.DRAW;
        } else {
            gameState = GameState.GAME_NOT_FINISHED;
        }
        return gameState;
    }
}
