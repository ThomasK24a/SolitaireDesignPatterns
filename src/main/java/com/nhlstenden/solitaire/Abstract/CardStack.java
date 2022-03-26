package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;

public abstract class CardStack {
    protected ArrayList<ICard> cards;

    public CardStack() {
        this.cards = new ArrayList<>();
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }

    public abstract boolean canAcceptStack(ArrayList<ICard> cardStack);

    public abstract boolean isIntractable(int cardIndex);
}
