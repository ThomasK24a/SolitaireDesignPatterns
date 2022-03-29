package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;
import main.java.com.nhlstenden.solitaire.Interfaces.IOrderedStack;

import java.awt.event.ActionEvent;
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
        }
        return true;
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        if(getLastCard() == null){
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

    /**
     * Will get the last card from this board stack
     * @return the last card, null if the stack is empty
     */
    private ICard getLastCard(){
        if(cards.size() == 0) return null;
        return cards.get(cards.size() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
