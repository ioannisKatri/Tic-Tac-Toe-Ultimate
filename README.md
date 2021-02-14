## Description

Learn more about the classic version of [Tic-Tac-Toe](https://en.wikipedia.org/wiki/Tic-tac-toe).

An extended version of Tic-Tac-Toe with:
- configurable number of players
- a simple AI
- configurable size of board


#### Requires
- Java 8
- Maven

#### Setup
```
mvn install
```

#### Run
```
mvn exec:java -Dexec.mainClass=Main
```

### Tic-Tac-Toe Configuration
- Under `src/main/resources` path the configuration file `config.txt` can be found.
- The application requires `config.txt` in order to set up.
  
#### Configuration file Variables

- `BOARD_SIZE=3`: Size of board, must be bigger than three
- `NUM_OF_PLAYERS=3`: The number of players must be provided (included AI)
- `COMPUTER_CHARACTER=C`: Character of AI
- `PLAYER1=$`: Character of First Player
- `PLAYER2=G`:Character of Second Player

#### Additional information
- Variables must be provided in a specific order. A `config_example.txt` is provided. 
- If extra players needs to change `NUM_OF_PLAYERS=3` and add more `PLAYER2` with their corresponding symbol.
- AI always plays at the end of each round.
- Coordinate 0,0 always start from top-left.
- Minimum size of board is 3x3
- Write `exit` to exit the game

###### Player input Sequence Diagram
![Player input Sequence Diagram](https://i.ibb.co/DM3rdjr/Player-Input.png)
Link [here](https://swimlanes.io/u/_NMfi_vYD).

### How to play

#### Example of 3x3 board

| _ _ _ | <br>
| _ _ _ | <br>
| _ _ _ | <br>

#### Example of first player input 0,0

| $ _ _ | <br>
| _ _ _ | <br>
| _ _ _ | <br>

#### Example of second player input 1,2

| $ _ _ | <br>
| _ _ B | <br>
| _ _ _ | <br>


#### Other
- Temporarily limitation of board size up to 10x10

#### ToDo
- Advanced AI.
- More Testing, current coverage 88%.
- Verify that every player has a different symbol.