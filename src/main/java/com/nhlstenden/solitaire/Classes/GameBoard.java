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
    private final int FINISH_STACKS = 4;
    private final int BOARD_START_X = 100;
    private final int BOARD_START_Y = 100;
    private final Icon DECK_ICON = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
    private final Icon ACE_ICON = new ImageIcon("src/resources/card_sprites/spade.png");

    private final ArrayList<BoardStack> boardStacks;
    private final ArrayList<FinishStack> finishStacks;
    private final DeckStack deck;
    private final WasteStack waste;
    private final BoardFactory boardFactory;
    private CardLocation selectedCardLocation;
    private final JButton playerCardsButton;
    private final JButton currentSelectedCard;

    public GameBoard() {
        super("Solitaire");

        boardStacks = createBoardStacks();
        finishStacks = createFinishStacks();
        boardFactory = new BoardFactory();
        waste = new WasteStack(new Coordinates(0,0));
        deck = new DeckStack(waste, new Coordinates(0,0));

        boardFactory.fillBoardStacks(boardStacks);
        boardFactory.fillDeck(deck);

        playerCardsButton = new JButton();

        currentSelectedCard = new JButton();
        currentSelectedCard.setBounds(BOARD_START_X, BOARD_START_Y * 4, 65, 90);
        currentSelectedCard.setVisible(false);
        add(currentSelectedCard);
        JLayeredPane backGroundPanel = new JLayeredPane();
        setContentPane(backGroundPanel);

        createPlayingBoard();
        setLayout(null);
        setBackground(new ColorUIResource(0, 153, 153));
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
        playerCardsButton.setBounds(BOARD_START_X, 10, 65, 90);
        add(playerCardsButton);
        validate();
    }

    private void createFinishedDeckButtons() {
        for (int i = 0; i < finishStacks.size(); i++) {

            Stack stack = new Stack(finishStacks.get(i));

            String iconString = DecoratorLibrary.getInstance().suitIconMap.get(finishStacks.get(i).getSuit());

            Icon icon = DecoratorLibrary.getInstance().getIcon(iconString);
            stack.setIconImage(icon);
            stack.setVisible(true);
            stack.setBounds(BOARD_START_X + (80 * (i + finishStacks.size())), 10, 65, 90);
            add(stack);

            stack.getStackButton().addActionListener(e ->  {
                moveCard(new CardLocation(stack.getCardStack(), -1));
            });
        }

        validate();
    }

    /**
     * create listener for the deck.
     */
    public void createPlayerCardsButtonListener() {
        playerCardsButton.addActionListener(e -> {
            List<ICard> drawnCards = deck.drawThree();

            for (int i = 0; i < drawnCards.size(); i++) {
                drawnCards.get(i).setPosition(BOARD_START_X, BOARD_START_Y + (60 * i));
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
            boardStacks.add(new BoardStack(new Coordinates(0,0)));
        }
        return boardStacks;
    }

    private ArrayList<FinishStack> createFinishStacks() {
        ArrayList<FinishStack> finishStacks = new ArrayList<>();
        for (Suit suit : Suit.class.getEnumConstants()) {
            finishStacks.add(new FinishStack(suit, new Coordinates(0,0)));
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
        } else {
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }
    }

    private void moveCard(CardLocation targetCardLocation) {
        if (!targetCardLocation.isIntractable() || !selectedCardLocation.isIntractable()) {
            selectedCardLocation = null;
            throw new RuntimeException("Card cannot be selected"); //TODO: add custom exception
        }

        MoveStack moveStack = selectedCardLocation.getStack().getAllBelow(selectedCardLocation.getIndexStack());
        if(targetCardLocation.getStack().canAcceptStack(moveStack)){
            System.out.println("Moved a " + moveStack.getFirstCard().toString());
            //System.out.println("Moved a " + moveStack.getFirstCard().toString() + " to a " + targetCardLocation.getCard().toString());
            targetCardLocation.getStack().addCards(moveStack.getCards());
            selectedCardLocation.getStack().removeAllBelow(selectedCardLocation.getIndexStack());
            moveStack.moveCardSprites(targetCardLocation.getStack());

            selectedCardLocation.getStack().getLastCard().flipCard(true);
        }else{
            System.out.println("Didn't move a " + moveStack.getFirstCard().toString());
//            System.out.println("Can't move a " + moveStack.getFirstCard().toString() + " to a " + targetCardLocation.getCard().toString());
        }
        selectedCardLocation = null;
    }

    private void createPlayingBoard() {
        for (int k = boardStacks.size(); k > 0; k--) {
            for (int i = boardStacks.get(k - 1).getCards().size(); i > 0; i--) {
                boardStacks.get(k - 1).getCards().get(i - 1).setPosition(BOARD_START_X + (80 * k), (BOARD_START_Y - 50) + (60 * i));
                add(boardStacks.get(k - 1).getCards().get(i - 1).getJCard(), boardStacks.get(k - 1).getCards().size() - i);
                if (i == boardStacks.get(k - 1).getCards().size()) {
                    boardStacks.get(k - 1).getCards().get(i - 1).flipCard(true);
                }
            }
        }
        validate();
    }

    public static GameBoard getInstance() {
        if (instance == null)
            instance = new GameBoard();

        return instance;
    }

}
