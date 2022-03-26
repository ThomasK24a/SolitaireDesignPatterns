package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import java.awt.*;

public class Card extends JPanel implements ICard {
    private final Suit suit;
    private final Value value;
    private boolean isFaceUp;
    private Icon valueSprite;
    private Icon suitSprite;

    private final JLabel suitLabel;
    private final JLabel valueLabel;

    private static Icon backSprite;

    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;

        suitLabel = new JLabel();
        valueLabel = new JLabel();

        valueSprite = new ImageIcon("pic/starB20.jpg");
        suitSprite = new ImageIcon("pic/starB10.jpg");

        this.setPosition(0, 0);

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

    public boolean isBlack() {
        return suit.equals(Suit.CLUB) || suit.equals(Suit.SPADE);
    }

    /**
     * Check if the given value is the next value after this card's value,
     * if this card is an ace the next value will be a two, three is after two, etc.
     * Jack is after ten, queen after jack, king and queen and after king there is no next value
     *
     * @param value value of the card to check
     * @return return true if the given value is the next value
     */
    @Override
    public boolean isNextValue(Value value) {
        int intValue = getValue().ordinal();
        int intValueToCompare = value.ordinal();
        return intValue == intValueToCompare + 1;
    }

    @Override
    public void setPosition(int x, int y) {

        this.valueLabel.setBounds(50, 100, 10, 20);
        this.suitLabel.setBounds(0, 100, 10, 20);

        suitLabel.setIcon(valueSprite);
        valueLabel.setIcon(suitSprite);

        add(suitLabel);
        add(valueLabel);

        setBounds(x, y, 100, 100);
        setBackground(Color.darkGray);
        revalidate();
    }
}
