package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

public class CardLocation {
    private final CardStack stack;
    private final int indexStack;

    public CardLocation(CardStack stack, int index) {
        this.stack = stack;
        this.indexStack = index;
    }

    public CardLocation(ICard card){
        CardStack stackLocation = card.getStackLocation();
        int index = stackLocation.findCardIndex(card);
        if (index == -1) throw new RuntimeException();

        this.stack = stackLocation;
        this.indexStack = index;
    }

    public CardStack getStack() {
        return stack;
    }

    public int getIndexStack() {
        return indexStack;
    }

    public ICard getCard(){
        return stack.getCards().get(indexStack);
    }

    public boolean isIntractable(){
        return stack.isIntractable(indexStack);
    }
}
