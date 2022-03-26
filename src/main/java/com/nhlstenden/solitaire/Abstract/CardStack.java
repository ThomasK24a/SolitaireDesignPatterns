package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Card;

import java.util.ArrayList;

public abstract class CardStack {
    private ArrayList<Card> cards;

    public CardStack() {
        this.cards = new ArrayList<>();
    }

    public void addCards(ArrayList<Card> cards) {
        this.cards.addAll(cards);
    }
}
