package main.java.com.nhlstenden.solitaire.Classes.States;

import main.java.com.nhlstenden.solitaire.Abstract.GameState;
import main.java.com.nhlstenden.solitaire.Enums.GameStates;

public class GameStateMachine {
    public static GameStateMachine instance;
    GameStates currentGameState;

    PregameState pregameState;
    RunningState runningState;
    PostgameState postgameState;

    public GameStateMachine() {
        pregameState = new PregameState();
        runningState = new RunningState();
        postgameState = new PostgameState();

        setGameState(GameStates.PRE_GAME_STATE);
    }

    public void setGameState(GameStates gameState) {
        //do nothing if state remains the same
        if (currentGameState == gameState) return;

        if (currentGameState != null)
            getCurrentGameState().onStateExit();

        currentGameState = gameState;
        getCurrentGameState().onStateEnter();
    }

    public GameState getCurrentGameState() {
        return switch (currentGameState) {
            case PRE_GAME_STATE -> this.pregameState;
            case RUNNING_STATE -> this.runningState;
            case POST_GAME_STATE -> this.postgameState;
        };
    }

    public static GameStateMachine getInstance() {
        if (instance == null)
            instance = new GameStateMachine();

        return instance;
    }
}
