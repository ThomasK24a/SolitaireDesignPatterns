package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Stacks.MoveStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public abstract class CardStack {
    protected List<ICard> cards;

    public CardStack() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<ICard> cards) {
        for(ICard card : cards){
            card.onCardMove(this);
        }

        this.cards.addAll(cards);
    }

    public List<ICard> getCards() {
        return cards;
    }

    public abstract boolean canAcceptStack(MoveStack moveStack);

    public abstract boolean isIntractable(int cardIndex);

    public int findCardIndex(ICard cardToFind){
        int i = 0;
        for(ICard card : cards){
            if(card == cardToFind) return i;
            i++;
        }
        return -1;
    }

    public MoveStack getAllBelow(int startingIndex){
        MoveStack moveStack = new MoveStack();
        moveStack.getCards().addAll(cards.subList(startingIndex, cards.size()));
        cards = cards.subList(0, startingIndex);
        return moveStack;
    }
}
