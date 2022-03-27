package main.java.com.nhlstenden.solitaire.Classes;

import java.awt.*;
import java.util.ArrayList;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.FinishStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.WasteStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class GameBoard extends JFrame {

    private final int BOARD_STACKS_AMOUNT = 7;


    private ArrayList<BoardStack> boardStacks;
    private ArrayList<FinishStack> finishStacks;
    private DeckStack deck;
    private WasteStack waste;
    private BoardFactory boardFactory;

    private JButton playerCardsButton;

    public GameBoard() {
        super("Solitaire");

        Card card0 = new Card(Suit.SPADE, Value.ACE, true);
        Card card1 = new Card(Suit.SPADE, Value.ACE, true);
        Card card2 = new Card(Suit.SPADE, Value.ACE, true);

        card0.setPosition(10, 60);
        card1.setPosition(10, 0);

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack();
        deck = new DeckStack(waste);

        boardFactory.fillDeck(deck);
        boardFactory.fillBoardStacks(boardStacks);

        Icon cardsButton = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
        playerCardsButton = new JButton();
        playerCardsButton.setIcon(cardsButton);
        playerCardsButton.setVisible(true);
        playerCardsButton.setBounds(100, 50, 65, 90);

        JLayeredPane backGroundPanel = new JLayeredPane();
        backGroundPanel.setBackground(new ColorUIResource(0, 153, 153));
        setContentPane(backGroundPanel);

        add(playerCardsButton);
        createPlayingBoard();

        setLayout(null);
        setBackground(Color.cyan);
        setSize(900, 750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void onCardMoved() {
        if (areFinishStacksComplete()) {
            //set state to post game state
        }
    }

    public boolean areFinishStacksComplete() {
        for (FinishStack finishStack : finishStacks) {
            if (!finishStack.isComplete()) return false;
        }
        return true;
    }

    private ArrayList<BoardStack> createBoardStacks() {
        ArrayList<BoardStack> boardStacks = new ArrayList<>();
        for (int i = 0; i < BOARD_STACKS_AMOUNT; i++) {
            boardStacks.add(new BoardStack());
        }
        return boardStacks;
    }

    private ArrayList<FinishStack> createFinishStacks() {
        ArrayList<FinishStack> finishStacks = new ArrayList<>();
        for (Suit suit : Suit.class.getEnumConstants()) {
            finishStacks.add(new FinishStack(suit));
        }
        return finishStacks;
    }

    private void createPlayingBoard() {

        System.out.println(boardStacks.size());
        for (BoardStack stack : boardStacks) {
            System.out.println(stack.getCards().size());
            for (Card card : stack.getCards()) {
                card.setPosition(100, 100);
                this.add(card, 1);
            }
        }
        validate();
    }
}
