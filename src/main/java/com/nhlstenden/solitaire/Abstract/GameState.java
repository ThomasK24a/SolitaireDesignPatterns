package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Enums.GameStates;

public abstract class GameState {
    public abstract void onStateEnter();

    public abstract void onStateExit();

    public abstract GameStates getNextState();
}
