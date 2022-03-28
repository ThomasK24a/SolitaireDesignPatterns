package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.FinishStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.WasteStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JFrame {

    private final int BOARD_STACKS_AMOUNT = 7;
    private final int FINISH_STACKS = 4;
    private final int BOARD_START_X = 100;
    private final int BOARD_START_Y = 150;
    private final Icon DECK_ICON = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
    private final Icon ACE_ICON = new ImageIcon("src/resources/card_sprites/spade.png");


    private final ArrayList<BoardStack> boardStacks;
    private final ArrayList<FinishStack> finishStacks;
    private final DeckStack deck;
    private final WasteStack waste;
    private final BoardFactory boardFactory;
    private CardLocation selectedCardLocation;
    private final JButton playerCardsButton;

    public GameBoard() {
        super("Solitaire");

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack();
        deck = new DeckStack(waste);


        boardFactory.fillBoardStacks(boardStacks);
        boardFactory.fillDeck(deck);

        playerCardsButton = new JButton();

        JLayeredPane backGroundPanel = new JLayeredPane();
        backGroundPanel.setBackground(new ColorUIResource(0, 153, 153));
        setContentPane(backGroundPanel);


        createPlayingBoard();
        setLayout(null);
        setBackground(Color.cyan);
        setSize(900, 750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayerCardsButtonListener();
        createDeckButton();
        createFinishedDeckButtons();
    }

    private void createDeckButton() {
        playerCardsButton.setIcon(DECK_ICON);
        playerCardsButton.setVisible(true);
        playerCardsButton.setBounds(BOARD_START_X, 50, 65, 90);
        add(playerCardsButton);
        validate();
    }

    private void createFinishedDeckButtons() {
        for (int i = 0; i < FINISH_STACKS; i++) {
            JButton stackButton = new JButton();
            stackButton.setIcon(ACE_ICON);
            stackButton.setVisible(true);
            stackButton.setBounds(BOARD_START_X + (80 * (i + FINISH_STACKS)), 50, 65, 90);
            add(stackButton);

            stackButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("wu tang");
                }
            });
        }

        validate();
    }

    /**
     * create listener for the deck.
     */
    public void createPlayerCardsButtonListener() {
        playerCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ICard> drawnCards = deck.drawThree();

                for (int i = 0; i < drawnCards.size(); i++) {
                    drawnCards.get(i).setPosition(BOARD_START_X, BOARD_START_Y + (60 * i + 1));
                    drawnCards.get(i).flipCard(true);
                    add(drawnCards.get(i).getJCard(), i);
                }
                validate();
            }
        });
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
            FinishStack finishStack = new FinishStack(suit);
            finishStacks.add(finishStack);
            finishStack.setPosition(0,0);
            finishStack.setVisible(true);
            add(finishStack);
        }
        return finishStacks;
    }

    private void onSelectCard(Card card) {
        CardStack stackLocation = card.getStackLocation();
        int index = stackLocation.findCardIndex(card);
        if (index == -1) throw new RuntimeException(); // TODO: add custom exception
        selectedCardLocation = new CardLocation(stackLocation, index);
    }

    private void createPlayingBoard() {

        for (int k = boardStacks.size(); k > 0; k--) {

            for (int i = boardStacks.get(k - 1).getCards().size(); i > 0; i--) {


                boardStacks.get(k - 1).getCards().get(i - 1).setPosition(BOARD_START_X + (80 * k + 1), BOARD_START_Y + (60 * i + 1));

                add(boardStacks.get(k - 1).getCards().get(i - 1).getJCard(), boardStacks.get(k - 1).getCards().size() - i);


                if (i == boardStacks.get(k - 1).getCards().size()) {

                    boardStacks.get(k - 1).getCards().get(i - 1).flipCard(true);
                }
            }
        }
        validate();
    }
}
