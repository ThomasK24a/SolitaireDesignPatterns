package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.*;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard extends JFrame {

    protected static GameBoard instance;

    private final int BOARD_STACKS_AMOUNT = 7;
    private final int BOARD_START_X = 100;
    private final int BOARD_START_Y = 150;

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

        Icon cardsButton = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
        playerCardsButton = new JButton();
        playerCardsButton.setIcon(cardsButton);
        playerCardsButton.setVisible(true);
        playerCardsButton.setBounds(BOARD_START_X, 50, 65, 90);

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

        createPlayerCardsButtonListener();
    }

    /**
     * create listener for the deck.
     */
    public void createPlayerCardsButtonListener() {
        playerCardsButton.addActionListener(e -> {
            List<ICard> drawnCards = deck.drawThree();

            for (int i = 0; i < drawnCards.size(); i++) {

                drawnCards.get(i).setPosition(BOARD_START_X, BOARD_START_Y + (60 * i + 1));
                drawnCards.get(i).flipCard(true);
                add(drawnCards.get(i).getJCard(), i);
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
            finishStacks.add(new FinishStack(suit));
        }
        return finishStacks;
    }

    public void onSelectCard(ICard card) {
        CardLocation cardLocation = new CardLocation(card);
        if(selectedCardLocation == null){
            selectCard(cardLocation);
        }else{
            moveCard(cardLocation);
        }
    }

    private void selectCard(CardLocation cardLocation){
        if(cardLocation.isIntractable()){
            selectedCardLocation = cardLocation;
        }else{
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }
    }

    private void moveCard(CardLocation targetCardLocation){
        if(!targetCardLocation.isIntractable() || !selectedCardLocation.isIntractable()){
            selectedCardLocation = null;
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }

        MoveStack moveStack = selectedCardLocation.getStack().getAllBelow(selectedCardLocation.getIndexStack());
        if(targetCardLocation.getStack().canAcceptStack(moveStack)){
            System.out.println("Moved a " + moveStack.getFirstCard().getValue() + " of " + moveStack.getFirstCard().getSuit() + " to a " + targetCardLocation.getCard().getValue() + " of " + targetCardLocation.getCard().getSuit());
            targetCardLocation.getStack().addCards(moveStack.getCards());
            selectedCardLocation.getStack().removeAllBelow(selectedCardLocation.getIndexStack());
        }else{
            System.out.println("Can't move a " + moveStack.getFirstCard().getValue() + " of " + moveStack.getFirstCard().getSuit() + " to a " + targetCardLocation.getCard().getValue() + " of " + targetCardLocation.getCard().getSuit());
        }
        selectedCardLocation = null;
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

    public static GameBoard getInstance(){
        if (instance == null)
            instance = new GameBoard();

        return instance;
    }

}
