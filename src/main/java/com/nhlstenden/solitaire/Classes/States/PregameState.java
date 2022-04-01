package main.java.com.nhlstenden.solitaire.Classes.States;

import main.java.com.nhlstenden.solitaire.Abstract.GameState;
import main.java.com.nhlstenden.solitaire.Classes.MainMenu;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;

public class PregameState extends GameState {
    MainMenu menu;

    @Override
    public void onStateEnter() {
        menu = new MainMenu();
    }

    @Override
    public void onStateExit() {
        menu.dispose();
    }

    @Override
    public GameStates getNextState() {
        return GameStates.RUNNING_STATE;
    }
}
