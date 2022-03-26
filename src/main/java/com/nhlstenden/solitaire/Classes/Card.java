package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;

public class Card extends JPanel implements ICard {
    private final Suit suit;
    private final Value value;
    private boolean isFaceUp;
    private static ImageIcon backSprite;

    public Card(Suit suit, Value value, boolean isFaceUp) {
        this.suit = suit;
        this.value = value;
        this.isFaceUp = isFaceUp;
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
