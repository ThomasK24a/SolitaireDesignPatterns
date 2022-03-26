package main.java.com.nhlstenden.solitaire.Interfaces;

import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

public interface ICard {
    Suit getSuit();

    Value getValue();

    boolean isFaceUp();
}
