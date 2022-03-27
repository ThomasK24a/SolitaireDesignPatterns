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

public class Card extends JPanel {
    private final int CARD_SIZE_WIDTH = 65;
    private final int CARD_SIZE_HEIGHT = 90;

    private final JButton suitButton;
    private final JLabel valueLabel;
    private final JLabel faceDownLabel;

    private final Suit suit;
    private final Value value;
    private boolean isFaceUp;

    private Icon valueSprite;
    private Icon suitSprite;

    private CardStack stackLocation;

    private static final Icon backSprite = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
    private static final Icon faceUpSprite = new ImageIcon("src/resources/card_sprites/total_blank_front.png");


    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;

        suitButton = new JButton();
        valueLabel = new JLabel();
        faceDownLabel = new JLabel();

        faceDownLabel.setBounds(0, 0, CARD_SIZE_WIDTH, CARD_SIZE_HEIGHT);
        faceDownLabel.setIcon(backSprite);

        Border border = BorderUIResource.getBlackLineBorderUIResource();
        setBorder(border);

        validate();
        setFaceDown(isFaceUp);
        createButton();

    }

    public void setValueSprite(Icon valueSprite) {
        this.valueSprite = valueSprite;
        valueLabel.setIcon(valueSprite);
        validate();
    }

    public void setSuitSprite(Icon suitSprite) {
        this.suitSprite = suitSprite;
        suitButton.setIcon(this.suitSprite);
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

    public void setFaceDown(boolean isFaceUp) {
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

        suitButton.setIcon(suitSprite);
        valueLabel.setIcon(valueSprite);

        add(valueLabel);
        add(suitButton);
        add(faceDownLabel);

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
}
