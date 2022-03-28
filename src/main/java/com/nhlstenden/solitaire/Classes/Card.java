package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;

        setFaceDownLabel();
        setBorder();
        flipCard(isFaceUp);

        createButton();
    }

    public void setValueSprite(Icon valueSprite) {
        this.valueSprite = valueSprite;
        valueLabel.setIcon(valueSprite);
        add(valueLabel);
        validate();
    }

    public void setSuitSprite(Icon suitSprite) {
        this.suitSprite = suitSprite;
        suitButton.setIcon(this.suitSprite);
        add(suitButton);
        validate();
    }

    public void createButton() {
        suitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPosition(400, 400);
            }
        });
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

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


    public void setPosition(int x, int y) {
        valueLabel.setBounds(0, 0, 50, 20);
        suitButton.setBounds(50, 50, 50, 20);

        setBounds(x, y, CARD_SIZE_WIDTH, CARD_SIZE_HEIGHT);
        setBackground(Color.lightGray);
        revalidate();
    }

    public void onCardMove(CardStack cardStack) {
        this.stackLocation = cardStack;
    }

    public CardStack getStackLocation() {
        return this.stackLocation;
    }

    private void setBorder(){
        Border border = BorderUIResource.getBlackLineBorderUIResource();
        this.setBorder(border);
        validate();
    }

    private void setFaceDownLabel(){
        faceDownLabel.setBounds(0, 0, CARD_SIZE_WIDTH, CARD_SIZE_HEIGHT);
        faceDownLabel.setIcon(BACK_SPRITE);
        this.add(faceDownLabel);
    }
}
