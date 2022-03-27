package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;

public class CardLocation {
    private final CardStack stack;
    private final int indexStack;

    public CardLocation(CardStack stack, int indexStack) {
        this.stack = stack;
        this.indexStack = indexStack;
    }

    public CardStack getStack() {
        return stack;
    }

    public int getIndexStack() {
        return indexStack;
    }
}
