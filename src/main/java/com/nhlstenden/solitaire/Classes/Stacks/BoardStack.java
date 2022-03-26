package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.util.ArrayList;

public class BoardStack extends CardStack implements IOrderedStack {

    public MoveStack getAllBelow(int startingIndex){
        if(isStackInOrder(startingIndex)) {
            throw new RuntimeException(); //TODO: do something with this
        }
        MoveStack moveStack = new MoveStack();
        moveStack.cards.addAll(cards.subList(startingIndex, cards.size()));
        cards = (ArrayList<ICard>) cards.subList(0, startingIndex);
        return moveStack;
    }

    public boolean isStackInOrder(int startingIndex){
        ArrayList<ICard> toCheckStack = (ArrayList<ICard>) cards.subList(startingIndex, cards.size());
        //set is last card black to the opposite of the first card, so it always passes the check;
        boolean isLastCardBlack = !cards.get(startingIndex).isBlack();
        for(ICard card : toCheckStack){
            //will be true if both cards are black or both cards are red
            if(card.isBlack() == isLastCardBlack){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return moveStack.getFirstCard().isBlack() != cards.get(cards.size() - 1).isBlack();
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        if(cards.get(cardIndex).isFaceUp()){
            return isStackInOrder(cardIndex);
        }
        return false;
    }
}
