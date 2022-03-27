package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Card;

import java.util.List;

public class WasteStack extends CardStack {
    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return false;
    }

    public List<Card> getAndClearAll(){
        List<Card> cards = this.cards;
        this.cards.clear();
        return cards;
    }
}
