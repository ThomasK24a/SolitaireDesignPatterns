package main.java.com.nhlstenden.solitaire.Interfaces;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

public interface ICard {
    Suit getSuit();

    Value getValue();

    boolean isFaceUp();

    void flipCard(boolean isFaceUp);

    boolean isBlack();

    boolean isNextValue(Value value);

    void setPosition(int x, int y);

    void onCardMove(CardStack cardStack);

    CardStack getStackLocation();
}
