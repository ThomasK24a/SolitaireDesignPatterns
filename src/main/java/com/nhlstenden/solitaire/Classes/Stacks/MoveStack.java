package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.util.ArrayList;

public class MoveStack implements IOrderedStack {
    protected ArrayList<ICard> cards;

    @Override
    public boolean isStackInOrder(int startingIndex) {
        return false;
    }

    public ICard getFirstCard(){
        return cards.get(0);
    }

    public int size(){
        return cards.size();
    }
}
