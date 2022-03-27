package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.MoveStack;

import java.util.ArrayList;
import java.util.List;

public abstract class CardStack {
    protected ArrayList<Card> cards;

    public CardStack() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<Card> cards) {
        for(Card card : cards){
            card.onCardMove(this);
        }

        this.cards.addAll(cards);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public abstract boolean canAcceptStack(MoveStack moveStack);

    public abstract boolean isIntractable(int cardIndex);

    public int findCardIndex(Card cardToFind){
        int i = 0;
        for(Card card : cards){
            if(card == cardToFind) return i;
            i++;
        }
        return -1;
    }
}
