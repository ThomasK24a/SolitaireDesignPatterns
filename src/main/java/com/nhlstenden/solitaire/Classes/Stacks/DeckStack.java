package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public class DeckStack extends CardStack {
    WasteStack wasteStack;

    public DeckStack(WasteStack wasteStack) {
        this.wasteStack = wasteStack;
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        //only the last card is intractable and needs to be face up
        return cardIndex == cards.size() - 1 && cards.get(cardIndex).isFaceUp();
    }

    public void drawThree(){
        List<ICard> newDeck = cards.subList(0, cards.size() - 3);
        List<ICard> drawnCards = cards.subList(cards.size() - 3, cards.size());
        wasteStack.addCards(drawnCards);
        cards = (ArrayList<ICard>) newDeck;
    }

    public void addWasteStack(){
        cards.addAll(wasteStack.getAndClearAll());
    }
}
