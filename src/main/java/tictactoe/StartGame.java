package tictactoe;

import tictactoe.config.models.Board;
import tictactoe.services.coordinates.CoordinateService;
import tictactoe.services.statemanager.IStateManager;
import tictactoe.services.statemanager.StateManagerService;
import tictactoe.services.winnercalculator.IWinnerCalculator;
import tictactoe.services.winnercalculator.WinnerCalculatorService;
import tictactoe.services.ai.IAi;
import tictactoe.services.ai.SimpleAiService;
import tictactoe.services.boardmanager.BoardService;
import tictactoe.services.boardmanager.IBoardService;
import tictactoe.services.playermanager.IPlayerManager;
import tictactoe.services.playermanager.PlayerManagerService;
import tictactoe.config.GameConfiguration;

import java.io.File;
import java.util.Scanner;

public class StartGame {

    private final File file;
    private TicTacToe ticTacToe;
    private final Scanner sc;
    public StartGame(final File file, final Scanner sc) {
        this.file = file;
        this.sc = sc;
    }

    public void initialize() {

        GameConfiguration configuration = new GameConfiguration(file);
        configuration.setup();

        Board board = new Board(new String[configuration.getBoardSize()][configuration.getBoardSize()]);

        IBoardService boardService = new BoardService(board);

        IAi aiService = new SimpleAiService(boardService);

        IWinnerCalculator winnerCalculatorService = new WinnerCalculatorService(boardService, configuration);

        IPlayerManager playerManagerService = new PlayerManagerService(configuration, aiService, this.sc);

        IStateManager stateManagerService = new StateManagerService(playerManagerService, winnerCalculatorService, boardService);

        CoordinateService coordinateService = new CoordinateService(boardService);

        this.ticTacToe = new TicTacToe(playerManagerService, boardService, stateManagerService, coordinateService);
        ticTacToe.initialize();
    }

    public void start() {
        boolean gameHasFinished = false;
        while (!gameHasFinished) {
            gameHasFinished = ticTacToe.play();
        }
            System.out.println("FINISHED");
        sc.close();
    }

}
