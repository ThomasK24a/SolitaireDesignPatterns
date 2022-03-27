package main.java.com.nhlstenden.solitaire.Classes.States;

import main.java.com.nhlstenden.solitaire.Abstract.GameState;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;

public class RunningState extends GameState {
    @Override
    public void onStateEnter() {

    }

    @Override
    public void onStateExit() {

    }

    @Override
    public GameStates getNextState() {
        return GameStates.POST_GAME_STATE;
    }
}
