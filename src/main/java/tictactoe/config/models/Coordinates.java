package tictactoe.config.models;

public final class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

