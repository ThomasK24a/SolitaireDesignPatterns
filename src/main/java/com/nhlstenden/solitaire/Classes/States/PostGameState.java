package main.java.com.nhlstenden.solitaire.Classes.States;

import main.java.com.nhlstenden.solitaire.Abstract.GameState;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;
import main.java.com.nhlstenden.solitaire.FinishedGame;

public class PostGameState extends GameState {
    FinishedGame finishedGame;
    @Override
    public void onStateEnter() {
        finishedGame = new FinishedGame();
    }

    @Override
    public void onStateExit() {
        finishedGame.dispose();
    }

    @Override
    public GameStates getNextState() {
        return GameStates.PRE_GAME_STATE;
    }
}
