package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;

public class FinishStack extends CardStack {
    private final Suit suit;
    private final JButton suitButton = new JButton();

    public FinishStack(Suit suit) {
        this.suit = suit;


    }



    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        //Can only accept single cards
        if(moveStack.size() != 1){
            return false;
        }

        ICard cardToAdd = moveStack.getFirstCard();

        //Can only accept cards with the same suit as the finish stack
        if(!cardToAdd.getSuit().equals(suit)){
            return false;
        }
        return cardToAdd.isNextValue(cards.get(cards.size() - 1).getValue());
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        //only the last card is intractable
        return cardIndex == cards.size() - 1;
    }

    public boolean isComplete(){
        ICard lastCard = cards.get(cards.size() - 1);
        return lastCard.getValue().equals(Value.KING);
    }

}
