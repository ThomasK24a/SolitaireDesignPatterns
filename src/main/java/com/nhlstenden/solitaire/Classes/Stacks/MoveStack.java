package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.util.ArrayList;

public class MoveStack implements IOrderedStack {
    protected ArrayList<ICard> cards;

    public MoveStack() {
        this.cards = new ArrayList<>();
    }

    @Override
    public boolean isStackInOrder(int startingIndex) {
        return false;
    }

    public ICard getFirstCard() {
        return cards.get(0);
    }

    public int size() {
        return cards.size();
    }

    public ArrayList<ICard> getCards() {
        return cards;
    }

    public void moveCardSprites(CardStack targetStack) {
        int count = targetStack.getCards().size() - cards.size();
        for (ICard card : cards) {
            Coordinates cardCoordinates = targetStack.getCoordsOfCard(count);
            card.setPosition(cardCoordinates.getX(), cardCoordinates.getY());
            count++;
        }
    }
}
