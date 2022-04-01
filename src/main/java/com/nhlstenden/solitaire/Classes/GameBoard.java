package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Classes.Factory.BoardFactory;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.*;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.util.ArrayList;
import java.util.List;

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
    private final JButton currentSelectedCard;

    public GameBoard() {
        super("Solitaire");

        setLayout(null);
        setBackground(new ColorUIResource(0, 153, 153));
        setSize(900, 750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack(new Coordinates(BOARD_START_X + 80, 10));
        deck = new DeckStack(waste, new Coordinates(BOARD_START_X, 10));

        boardFactory.fillBoardStacks(boardStacks);
        boardFactory.fillDeck(deck);

        currentSelectedCard = new JButton();
        currentSelectedCard.setBounds(BOARD_START_X, BOARD_START_Y * 2, 65, 90);
        currentSelectedCard.setVisible(false);
        add(currentSelectedCard);
        JLayeredPane backGroundPanel = new JLayeredPane();
        setContentPane(backGroundPanel);

        addBoardStackCardsToPanel();
        addDeckButtonToPanel();
        addFinishStackButtonsToPanel();
        validate();
    }

    private void addFinishStackButtonsToPanel() {
        for (FinishStack finishStack : finishStacks) {
            add(finishStack.getStackButton());
        }
    }

    /**
     * create listener for the deck.
     */
    public void addDeckButtonToPanel() {
        add(deck.getButton());

        deck.getButton().getStackButton().addActionListener(e ->{
            onDeckButtonClick();
        });
    }

    public void onDeckButtonClick() {0
        List<ICard> drawnCards = deck.drawThree();

        for (int i = 0; i < drawnCards.size(); i++) {
            drawnCards.get(i).setPosition(BOARD_START_X, BOARD_START_Y + (60 * i));
            drawnCards.get(i).flipCard(true);
            add(drawnCards.get(i).getJCard(), i);
        }
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
            boardStacks.add(new BoardStack(new Coordinates(BOARD_START_X + 110 + (80 * i), BOARD_START_Y + 50)));
        }
        return boardStacks;
    }

    private ArrayList<FinishStack> createFinishStacks() {
        ArrayList<FinishStack> finishStacks = new ArrayList<>();
        int i = 0;
        for (Suit suit : Suit.class.getEnumConstants()) {
            FinishStack finishStack = new FinishStack(suit, new Coordinates(BOARD_START_X + 320 + (80 * i), 10));
            finishStacks.add(finishStack);
            i++;
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
        System.out.println(cardLocation.getCard().toString());
        if (cardLocation.isIntractable()) {
            selectedCardLocation = cardLocation;
            currentSelectedCard.setVisible(true);
            validate();
        } else {
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }
    }

    public void moveCard(CardLocation targetCardLocation) {

        Coordinates previousLocation;

        if (!targetCardLocation.isIntractable() || !selectedCardLocation.isIntractable()) {
            selectedCardLocation = null;
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }

        MoveStack moveStack = selectedCardLocation.getStack().getAllBelow(selectedCardLocation.getIndexStack());

        if (targetCardLocation.getStack().canAcceptStack(moveStack)) {
            System.out.println("Moved a " + moveStack.getFirstCard().toString());

            //System.out.println("Moved a " + moveStack.getFirstCard().toString() + " to a " + targetCardLocation.getCard().toString());

            targetCardLocation.getStack().addCards(moveStack.getCards());

            selectedCardLocation.getStack().removeAllBelow(selectedCardLocation.getIndexStack());

            moveStack.moveCardSprites(targetCardLocation.getStack());
        } else {
            System.out.println("Didn't move a " + moveStack.getFirstCard().toString());
//            System.out.println("Can't move a " + moveStack.getFirstCard().toString() + " to a " + targetCardLocation.getCard().toString());
        }

        selectedCardLocation = null;
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
}
