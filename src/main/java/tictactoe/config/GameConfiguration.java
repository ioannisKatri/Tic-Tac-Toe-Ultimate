package tictactoe.config;

import tictactoe.config.models.Player;
import tictactoe.config.utils.ConfigurationUtils;

import java.io.*;
import java.util.Random;

import static tictactoe.config.utils.CommonValues.CELL_EMPTY_CHARACTER;

public class GameConfiguration {

    private int boardSize;
    private Player[] players;
    private final File file;

    public GameConfiguration(File file) {
        this.file = file;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setup() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(this.file.getAbsolutePath()))) {

            String line = reader.readLine();
            setBoardSize(line);

            String numberOfPlayers = reader.readLine();
            setPlayers(numberOfPlayers);

            String getAiCharacter = reader.readLine();
            addPlayerAi(getAiCharacter);

            for (int index = 0; index < this.players.length; index++) {
                if (this.players[index] != null) {
                    continue;
                }

                line = reader.readLine();
                if (line == null) {
                    throw new IllegalArgumentException("Player value is missing please check documentation");
                }
                addPlayer(line, index);
            }

        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND please add a file to resource folder");
            System.exit(1);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addPlayerAi(String line) {
        String playerSymbol = ConfigurationUtils.extractValue(line, "COMPUTER_CHARACTER");

        Random ran = new Random();
        int index = ran.nextInt(this.players.length);

        if (playerSymbol.length() != 1 || playerSymbol.equals(CELL_EMPTY_CHARACTER)) {
            throw new IllegalArgumentException("COMPUTER_CHARACTER" + " Computer Symbol value must be one character and not " + CELL_EMPTY_CHARACTER);
        }
        this.players[index] = new Player(playerSymbol, true);
    }

    private void addPlayer(String line, int index) {
        String playerSymbol = ConfigurationUtils.extractValue(line);

        if (playerSymbol.length() != 1 || playerSymbol.equals(CELL_EMPTY_CHARACTER)) {
            throw new IllegalArgumentException("Players Symbol value must be one character and not " + CELL_EMPTY_CHARACTER);
        }
        this.players[index] = new Player(playerSymbol, false);
    }

    private void setPlayers(String playersArraySize) {
        int size = Integer.parseInt(ConfigurationUtils.extractValue(playersArraySize, "NUM_OF_PLAYERS"));
        this.players = new Player[size];
    }

    private void setBoardSize(String boardSize) {
        int value = Integer.parseInt(ConfigurationUtils.extractValue(boardSize, "BOARD_SIZE"));

        if (value < 3 || value > 10) {
            throw new IllegalArgumentException("Board value must be between 3 and 10");
        }

        this.boardSize = Integer.parseInt(ConfigurationUtils.extractValue(boardSize, "BOARD_SIZE"));
    }
}
