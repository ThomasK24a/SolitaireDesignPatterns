package main.java.com.nhlstenden.solitaire;

import main.java.com.nhlstenden.solitaire.Classes.States.GameStateMachine;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;

import javax.swing.*;

public class GameManager {
    public static GameManager instance;

    static GameStateMachine stateMachine;
    private JFrame currentJFrame;

    public GameManager() {
        stateMachine = GameStateMachine.getInstance();
    }

    public void restartGame() {
        stateMachine.setGameState(GameStates.PRE_GAME_STATE);
    }

    public void startGame() {
        stateMachine.setGameState(GameStates.RUNNING_STATE);
    }

    public void finishGame() {
        stateMachine.setGameState(GameStates.POST_GAME_STATE);
    }

    public static GameManager getInstance() {
        if (instance == null)
            instance = new GameManager();

        return instance;
    }

}
