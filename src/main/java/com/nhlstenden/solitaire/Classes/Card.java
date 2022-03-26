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
    private static Icon backSprite;

    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;


        Icon ic0=new ImageIcon("pic/starB0.jpg");
        Icon ic10=new ImageIcon("pic/starB10.jpg");

        setBounds(40, 80, 200, 200);
        JLabel b1 = new JLabel("Button 1");
        b1.setBounds(50, 100, 80, 30);
        b1.setIcon(ic0);
        setBounds(40,80,200,200);
        setBackground(Color.black);
        JLabel b2 = new JLabel("Button 2");
        b2.setIcon(ic10);
        b2.setBounds(100, 100, 80, 30);
        add(b1);
        add(b2);
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
     * @param value value of the card to check
     * @return return true if the given value is the next value
     */
    @Override
    public boolean isNextValue(Value value) {
        int intValue = getValue().ordinal();
        int intValueToCompare = value.ordinal();
        return intValue == intValueToCompare + 1;
    }
}
