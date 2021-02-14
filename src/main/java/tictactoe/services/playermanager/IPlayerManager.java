package tictactoe.services.playermanager;

import tictactoe.config.models.Player;

public interface IPlayerManager {

    void pointToNextPlayer();

    Player getCurrentPlayer();

    String getPlayerNextMove();
}
