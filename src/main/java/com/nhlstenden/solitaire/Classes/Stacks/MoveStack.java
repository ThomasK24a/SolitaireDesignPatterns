package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.util.ArrayList;


public class MoveStack implements IOrderedStack {
    protected ArrayList<Card> cards;

    @Override
    public boolean isStackInOrder(int startingIndex) {
        return false;
    }

    public Card getFirstCard(){
        return cards.get(0);
    }

    public int size(){
        return cards.size();
    }
}
