package main.java.com.nhlstenden.solitaire.Classes;

import java.util.ArrayList;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.FinishStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.WasteStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

import javax.swing.*;

public class GameBoard extends JFrame {

    private final int BOARD_STACKS_AMOUNT = 7;

    private ArrayList<BoardStack> boardStacks;
    private ArrayList<FinishStack> finishStacks;
    private DeckStack deck;
    private WasteStack waste;
    private BoardFactory boardFactory;

    public GameBoard() {
        super("Solitaire");

        Card cardPanel = new Card(Suit.SPADE, Value.ACE, true);
        add(cardPanel);
        setLayout(null);
        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack();
        deck = new DeckStack(waste);

        boardFactory.fillDeck(deck);
        boardFactory.fillBoardStacks(boardStacks);

    }

    public void onCardMoved(){
        if(areFinishStacksComplete()){
            //set state to post game state
        }
    }

    public boolean areFinishStacksComplete(){
        for(FinishStack finishStack : finishStacks){
            if(!finishStack.isComplete()) return false;
        }
        return true;
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
