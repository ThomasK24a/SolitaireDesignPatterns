package main.java.com.nhlstenden.solitaire.Classes;

import java.awt.*;
import java.util.ArrayList;
import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.FinishStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.WasteStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
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

        Card cardPanel = new Card(Suit.SPADE, Value.ACE, true);

//        boardStacks = createBoardStacks();
//        finishStacks = createFinishStacks();
//        boardFactory = new BoardFactory();
//        waste = new WasteStack();
//        deck = new DeckStack();
//
//        boardFactory.fillDeck(deck);
//        boardFactory.fillBoardStacks(boardStacks);
        Icon cardsButton = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
        playerCardsButton = new JButton();
        playerCardsButton.setIcon(cardsButton);
        playerCardsButton.setVisible(true);
        playerCardsButton.setBounds(100,50,65,90);

        JPanel backGroundPanel = new JPanel();
        backGroundPanel.setBackground(new ColorUIResource(0,153,153));
        setContentPane(backGroundPanel);

        add(playerCardsButton);
        add(cardPanel);
        
        setLayout(null);
        setBackground(Color.darkGray);
        setSize(900,750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
//
//    private ArrayList<BoardStack> createBoardStacks(){
//        ArrayList<BoardStack> boardStacks = new ArrayList<>();
//        for(int i = 0; i < BOARD_STACKS_AMOUNT; i++){
//            boardStacks.add(new BoardStack());
//        }
//        return boardStacks;
//    }
//
//    private ArrayList<FinishStack> createFinishStacks(){
//        ArrayList<FinishStack> finishStacks = new ArrayList<>();
//        for(Suit suit : Suit.class.getEnumConstants()){
//            finishStacks.add(new FinishStack(suit));
//        }
//        return finishStacks;
//    }

}
