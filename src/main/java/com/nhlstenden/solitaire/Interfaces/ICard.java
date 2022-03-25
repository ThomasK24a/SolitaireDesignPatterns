package main.java.com.nhlstenden.solitaire.Interfaces;

import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

public interface ICard {
    public Suit getSuit();

    public Value getValue();

    public boolean isFaceUp();
}
