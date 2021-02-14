package tictactoe.services.playermanager;

import tictactoe.config.GameConfiguration;
import tictactoe.config.models.Player;
import tictactoe.services.ai.IAi;

import java.util.Scanner;

public class PlayerManagerService implements IPlayerManager {

    private int currentPlayerIndex;
    private final Player[] playersArray;
    private final Scanner sc;
    private final IAi aiService;

    public PlayerManagerService(GameConfiguration configuration, IAi aiService, Scanner sc) {
        this.playersArray = configuration.getPlayers();
        this.aiService = aiService;
        this.currentPlayerIndex = 0;
        this.sc = sc;
    }

    @Override
    public void pointToNextPlayer() {
        if (currentPlayerIndex < playersArray.length - 1) {
            currentPlayerIndex++;
        } else {
            currentPlayerIndex = 0;
        }
    }

    @Override
    public String getPlayerNextMove() {
        if (this.isComputersTurn()) {
            return aiService.generateNextMove();
        }
        return sc.nextLine().trim();

    }

    @Override
    public Player getCurrentPlayer() {
        return playersArray[currentPlayerIndex];
    }

    private Boolean isComputersTurn() {
        return this.getCurrentPlayer().isAI();
    }
}