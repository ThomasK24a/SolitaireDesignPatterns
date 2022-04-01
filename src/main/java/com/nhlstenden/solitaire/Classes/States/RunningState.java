package main.java.com.nhlstenden.solitaire.Classes.States;

import main.java.com.nhlstenden.solitaire.Abstract.GameState;
import main.java.com.nhlstenden.solitaire.Classes.GameBoard;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;

public class RunningState extends GameState {
    GameBoard gameBoard;
    @Override
    public void onStateEnter() {
        gameBoard = new GameBoard();
    }

    @Override
    public void onStateExit() {
        gameBoard.dispose();
    }

    @Override
    public GameStates getNextState() {
        return GameStates.POST_GAME_STATE;
    }
}
