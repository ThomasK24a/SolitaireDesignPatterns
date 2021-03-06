package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.util.List;

public class BoardStack extends CardStack implements IOrderedStack {

    public BoardStack(Coordinates stackCoordinates) {
        super(stackCoordinates);
    }

    public boolean isStackInOrder(int startingIndex){
        List<ICard> toCheckStack = cards.subList(startingIndex, cards.size());
        //set is last card black to the opposite of the first card, so it always passes the check;
        boolean isLastCardBlack = !cards.get(startingIndex).isBlack();
        for(ICard card : toCheckStack){
            //will be true if both cards are black or both cards are red
            if(card.isBlack() == isLastCardBlack){
                return false;
            }
            isLastCardBlack = card.isBlack();
        }
        return true;
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        if(getLastCard().getSuit().equals(Suit.NONE)){
            return moveStack.getFirstCard().isNextValue(Value.NONE);
        }
        return getLastCard().isNextValue(moveStack.getFirstCard().getValue()) &&
             moveStack.getFirstCard().isBlack() != getLastCard().isBlack();
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        if(cards.get(cardIndex).isFaceUp()){
            return isStackInOrder(cardIndex);
        }
        return false;
    }

    @Override
    protected Coordinates getOffset() {
        return new Coordinates(0, 60);
    }
}
