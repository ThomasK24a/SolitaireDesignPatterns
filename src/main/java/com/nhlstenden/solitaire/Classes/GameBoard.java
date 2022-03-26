package main.java.com.nhlstenden.solitaire.Classes;

import java.util.ArrayList;
import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.FinishStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.WasteStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;

public class GameBoard {
    private final int BOARD_STACKS_AMOUNT = 7;

    private ArrayList<BoardStack> boardStacks;
    private ArrayList<FinishStack> finishStacks;
    private DeckStack deck;
    private WasteStack waste;
    private BoardFactory boardFactory;

    public GameBoard() {
        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack();
        deck = new DeckStack();

        boardFactory.fillDeck(deck);
        boardFactory.fillBoardStacks(boardStacks);
    }

    private ArrayList<BoardStack> createBoardStacks(){
        ArrayList<BoardStack> boardStacks = new ArrayList<>();
        for(int i = 0; i < BOARD_STACKS_AMOUNT; i++){
            boardStacks.add(new BoardStack());
        }
        return boardStacks;
    }

    private ArrayList<FinishStack> createFinishStacks(){
        ArrayList<FinishStack> finishStacks = new ArrayList<>();
        for(Suit suit : Suit.class.getEnumConstants()){
            finishStacks.add(new FinishStack(suit));
        }
        return finishStacks;
    }
}
