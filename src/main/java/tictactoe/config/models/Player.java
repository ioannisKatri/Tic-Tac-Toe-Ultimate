package tictactoe.config.models;


public class Player {
    private final String playerSymbol;
    private final boolean isAI;


    public Player(final String playerSymbol, final boolean isAI) {
        this.playerSymbol = playerSymbol;
        this.isAI = isAI;
    }

    public String getSymbol() {
        return playerSymbol;
    }

    public boolean isAI() {
        return isAI;
    }
}
