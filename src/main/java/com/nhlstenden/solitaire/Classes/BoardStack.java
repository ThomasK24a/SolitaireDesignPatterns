package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;

public class BoardStack extends CardStack {

    public ArrayList<ICard> getAllBelow(int startingIndex){
        if(isStackInOrder(startingIndex)) {
            throw new RuntimeException(); //TODO: do something with this
        }
        ArrayList<ICard> cardsToMove = (ArrayList<ICard>) cards.subList(startingIndex, cards.size());
        cards = (ArrayList<ICard>) cards.subList(0, startingIndex);
        return cardsToMove;
    }

    private boolean isStackInOrder(int startingIndex){
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
    public boolean canAcceptStack(ArrayList<ICard> cardStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return false;
    }
}
