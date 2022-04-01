package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.*;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.GameManager;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;

public class GameBoard extends JFrame {

    protected static GameBoard instance;

    private final int BOARD_STACKS_AMOUNT = 7;
    private final int BOARD_START_X = 100;
    private final int BOARD_START_Y = 100;

    private final ArrayList<BoardStack> boardStacks;
    private final ArrayList<FinishStack> finishStacks;
    private final DeckStack deck;
    private final WasteStack waste;
    private final BoardFactory boardFactory;
    private CardLocation selectedCardLocation;
    private JButton currentSelectedCard;
    private JButton restartButton;

    public GameBoard() {
        super("Solitaire");
        instance = this;

        setLayout(null);
        setBackground(new ColorUIResource(0, 153, 153));
        setSize(900, 750);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack(new Coordinates(BOARD_START_X, 100));
        deck = new DeckStack(waste, new Coordinates(BOARD_START_X, 10));

        boardFactory.fillBoardStacks(boardStacks);
        boardFactory.fillDeck(deck);

        JLayeredPane backGroundPanel = new JLayeredPane();
        setContentPane(backGroundPanel);

        addCurrentCard();
        addRestartButton();
        addBoardStackCardsToPanel();
        addDeckButtonToPanel();
        addFinishStackButtonsToPanel();
        validate();
    }

    private void setCurrentCard(ICard card) {
        DecoratorLibrary dl = DecoratorLibrary.getInstance();

        String iconPath;
        ImageIcon icon;

        if (card.isBlack()) {
            iconPath = dl.getValueIconBlackMap().get(card.getValue());
        } else {
            iconPath = dl.getValueIconRedMap().get(card.getValue());
        }

        icon = dl.getIcon(iconPath);
        currentSelectedCard.setIcon(icon);
        currentSelectedCard.setVisible(true);
        validate();
    }

    private void addCurrentCard() {
        currentSelectedCard = new JButton();
        currentSelectedCard.setBounds(BOARD_START_X, 400, 65, 90);
        currentSelectedCard.setVisible(false);

        add(currentSelectedCard);
    }

    private void addRestartButton() {
        restartButton = new JButton();
        restartButton.setBounds(100, 600, 80, 50);
        restartButton.setText("Restart");
        add(restartButton, 5);

        restartButton.addActionListener(e -> GameManager.getInstance().restartGame());
    }

    private void addFinishStackButtonsToPanel() {
        for (FinishStack finishStack : finishStacks) {
            add(finishStack.getStackButton(), 0);
        }
    }

    /**
     * create listener for the deck.
     */
    public void addDeckButtonToPanel() {
        add(deck.getStackButton());
    }


    public void onCardMoved() {
        for(FinishStack finishStack : finishStacks){
            redrawFinishStack(finishStack);
        }

        if (areFinishStacksComplete()) {
            GameManager.getInstance().finishGame();
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
            boardStacks.add(new BoardStack(new Coordinates(BOARD_START_X + 110 + (80 * i), BOARD_START_Y + 50)));
        }
        return boardStacks;
    }

    private ArrayList<FinishStack> createFinishStacks() {
        ArrayList<FinishStack> finishStacks = new ArrayList<>();
        int i = 0;
        for (Suit suit : Suit.class.getEnumConstants()) {
            if(!suit.equals(Suit.NONE)){
                FinishStack finishStack = new FinishStack(suit, new Coordinates(BOARD_START_X + 320 + (80 * i), 10));
                finishStacks.add(finishStack);
                i++;
            }
        }
        return finishStacks;
    }

    public void onCardButtonClick(ICard card) {
        CardLocation cardLocation = new CardLocation(card);
        if (selectedCardLocation == null) {
            selectCard(cardLocation);
        } else {
            moveCard(cardLocation);
        }
    }

    private void selectCard(CardLocation cardLocation) {
        if (cardLocation.isIntractable()) {
            selectedCardLocation = cardLocation;
            setCurrentCard(selectedCardLocation.getCard());
            validate();
        } else {
            throw new RuntimeException("Card cannot be selected");
        }
    }

    public void moveCard(CardLocation targetCardLocation) {
        if (!targetCardLocation.isIntractable() || !selectedCardLocation.isIntractable()) {
            selectedCardLocation = null;
            throw new RuntimeException("Card cannot be selected");
        }

        MoveStack moveStack = selectedCardLocation.getStack().getAllBelow(selectedCardLocation.getIndexStack());

        if (targetCardLocation.getStack().canAcceptStack(moveStack)) {
            System.out.println("Moved a " + moveStack.getFirstCard().toString());
            targetCardLocation.getStack().addCards(moveStack.getCards());
            selectedCardLocation.getStack().removeAllBelow(selectedCardLocation.getIndexStack());
            moveStack.moveCardSprites(targetCardLocation.getStack());
        } else {
            System.out.println("Didn't move a " + moveStack.getFirstCard().toString());
        }
        currentSelectedCard.setVisible(false);

        selectedCardLocation = null;
        onCardMoved();
    }

    private void addBoardStackCardsToPanel() {
        for (BoardStack boardStack : boardStacks) {
            for (ICard card : boardStack.getCards()) {
                int cardIndex = boardStack.findCardIndex(card);
                int cardLayer = boardStack.getCards().size() - cardIndex;
                card.setCardCoordinates(boardStack.getCoordsOfCard(cardIndex));
                add(card.getJCard(), cardLayer);
            }
            boardStack.getLastCard().flipCard(true);
        }
    }

    public static GameBoard getInstance() {
        if (instance == null)
            instance = new GameBoard();

        return instance;
    }

    public void redrawWasteStack() {
        int i = waste.getCards().size();
        for (ICard card : waste.getCards()) {
            card.setCardCoordinates(waste.getCoordsOfCard(waste.findCardIndex(card)));
            remove(card.getJCard());
            add(card.getJCard(), i);
            i--;
        }
        for (ICard card : deck.getCards()) {
            remove(card.getJCard());
        }
        validate();
    }

    private void redrawFinishStack(FinishStack finishStack){
        int i = 1;
        for(ICard card : finishStack.getCards()){
            remove(card.getJCard());
            if(i == finishStack.getCards().size()){
                add(card.getJCard(), i);
            }
            i++;
        }
        validate();
    }
}
