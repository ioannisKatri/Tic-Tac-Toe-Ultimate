package tictactoe.config.enums;

public enum GameState {
    GAME_NOT_FINISHED("Game Not Finished"),
    DRAW("Draw"),
    WIN("Win");

    String name;

    GameState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
