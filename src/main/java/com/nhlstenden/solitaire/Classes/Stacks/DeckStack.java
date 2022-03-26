package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;

public class DeckStack extends CardStack {
    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        //only the last card is intractable and needs to be face up
        return cardIndex == cards.size() - 1 && cards.get(cardIndex).isFaceUp();
    }
}
