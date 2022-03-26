package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;

public class FinishStack extends CardStack {
    private Suit suit;

    public FinishStack(Suit suit) {

    }

    @Override
    public boolean canAcceptStack(ArrayList<ICard> cardStack) {
        //Can only accept single cards
        if(cardStack.size() != 1){
            return false;
        }
        ICard cardToAdd = cardStack.get(0);
        //Can only accept cards with the same suit as the finish stack
        if(!cardToAdd.getSuit().equals(suit)){
            return false;
        }
        return cardToAdd.isNextValue(cards.get(cards.size() - 1).getValue());
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return false;
    }
}
