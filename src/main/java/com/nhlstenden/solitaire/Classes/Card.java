package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

/**
 * Card class holds the logic for the JPanel component, and game object functionality.
 */
public class Card extends JPanel implements ICard {
    private final int CARD_SIZE_WIDTH = 65;
    private final int CARD_SIZE_HEIGHT = 90;
    private static final Icon BACK_SPRITE = new ImageIcon("src/resources/card_sprites/back_red_basic.png");

    private final JButton suitButton = new JButton();
    private final JLabel valueLabel = new JLabel();
    private final JLabel faceDownLabel = new JLabel();

    private final Suit suit;
    private final Value value;
    private boolean isFaceUp;

    private Icon valueSprite;
    private Icon suitSprite;

    private CardStack stackLocation;

    /**
     * Initialize the Card with the proper Suit, Value, and if it is facing up or down.
     *
     * @param suit Suit enum.
     * @param value Value enum.
     * @param isFaceUp boolean.
     */
    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;
        setFaceDownLabel();
        setBorder();
        flipCard(isFaceUp);

        createButton();
    }

    /**
     * set the value Icon of the card.
     *
     * @param valueSprite Icon to be displayed as the Value of the card.
     */
    public void setValueSprite(Icon valueSprite) {
        this.valueSprite = valueSprite;
        valueLabel.setIcon(valueSprite);
        add(valueLabel);
        validate();
    }

    /**
     * set the suit Icon of the card.
     *
     * @param suitSprite Icon to be displayed as the Suit of the card.
     */
    public void setSuitSprite(Icon suitSprite) {
        this.suitSprite = suitSprite;
        suitButton.setIcon(this.suitSprite);
        add(suitButton);
        validate();
    }

    /**
     * create listener for the suit button.
     */
    public void createButton() {
        suitButton.addActionListener(e -> {
            Object obj = e.getSource();
            if (obj instanceof JButton cb) {
                Component component = cb.getParent();
                if (component instanceof Card card) {
                    GameBoard.getInstance().onCardButtonClick(card);
                }
            }
        });
    }

    public int getCardWidth() {
        return CARD_SIZE_WIDTH;
    }

    public int getCardHeight() {
        return CARD_SIZE_HEIGHT;
    }

    /**
     * returns the Suit enum of the card.
     *
     * @return Suit enum.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * returns the Value enum of the card.
     *
     * @return Value enum.
     */
    public Value getValue() {
        return value;
    }

    /**
     * returns true if the card is facing up.
     *
     * @return boolean
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * returns the JPanel component that makes up the card.
     *
     * @return JComponent
     */
    public JPanel getJCard(){
        return this;
    }

    /**
     * set the card to face up or down.
     *
     * @param isFaceUp card is face up or down.
     */
    public void flipCard(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;

        suitButton.setVisible(isFaceUp);

        valueLabel.setVisible(isFaceUp);

        if (isFaceUp) {
            faceDownLabel.setVisible(false);

            return;
        }

        suitButton.setVisible(false);
        valueLabel.setVisible(false);
        faceDownLabel.setVisible(true);

        validate();
    }

    /**
     * return true if the card has a black suit.
     *
     * @return boolean.
     */
    public boolean isBlack() {
        return suit.equals(Suit.CLUB) || suit.equals(Suit.SPADE);
    }

    /**
     * Check if the given value is the next value after this card's value,
     * if this card is an ace the next value will be a two, three is after two, etc.
     * Ace is after None, jack is after ten, queen after jack, king and queen
     * for king the method will return true if the next value is none
     *
     * @param value value of the card to check
     * @return return true if the given value is the next value
     */
    public boolean isNextValue(Value value) {
        int intValue = getValue().ordinal();
        int intValueToCompare = value.ordinal();
        return intValue == intValueToCompare + 1;
    }

    /**
     * Move the panel to the given x and y coordinates.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void setPosition(int x, int y) {
        valueLabel.setBounds(0, 0, 50, 20);
        suitButton.setBounds(50, 50, 50, 20);

        setBounds(x, y, CARD_SIZE_WIDTH, CARD_SIZE_HEIGHT);
        setBackground(Color.lightGray);
        revalidate();
    }

    /**
     * Change the stack where the card is located. e.g. BoardStack or FinishStack.
     *
     * @param cardStack CardStack Abstract Class.
     */
    public void onCardMove(CardStack cardStack) {
        this.stackLocation = cardStack;
    }

    /**
     * Returns the stack where the card is located. e.g. BoardStack.
     *
     * @return CardStack abstract class.
     */
    public CardStack getStackLocation() {
        return this.stackLocation;
    }

    @Override
    public void setPosition(Coordinates coordsOfCard) {
        setPosition(coordsOfCard.getX(), coordsOfCard.getY());
    }

    /**
     * Set the boarder of the JPanel.
     */
    private void setBorder(){
        Border border = BorderUIResource.getBlackLineBorderUIResource();
        this.setBorder(border);
        validate();
    }

    /**
     * Set the Icon for the back of the card.
     */
    private void setFaceDownLabel(){
        faceDownLabel.setBounds(0, 0, CARD_SIZE_WIDTH, CARD_SIZE_HEIGHT);
        faceDownLabel.setIcon(BACK_SPRITE);
        this.add(faceDownLabel);
    }

    @Override
    public String toString(){
        return this.getValue() + " of " + this.getSuit() + "s";
    }
}
