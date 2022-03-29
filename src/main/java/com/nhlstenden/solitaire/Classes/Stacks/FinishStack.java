package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Classes.DecoratorLibrary;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FinishStack extends CardStack {
    private final Suit suit;

    private StackButton stackButton;

    public FinishStack(Suit suit, Coordinates stackCoordinates) {
        super(stackCoordinates);
        this.suit = suit;

        String iconString = DecoratorLibrary.getInstance().getSuitIconMap().get(getSuit());
        Icon icon = DecoratorLibrary.getInstance().getIcon(iconString);

        this.stackButton = new StackButton(this, icon);
        stackButton.setBounds(stackCoordinates.getX(), stackCoordinates.getY(), 65, 90);
    }

    public Suit getSuit() {
        return suit;
    }

    public StackButton getStackButton() {
        return stackButton;
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        //Can only accept single cards
        if (moveStack.size() != 1) {
            return false;
        }

        ICard cardToAdd = moveStack.getFirstCard();

        //Can only accept cards with the same suit as the finish stack
        if (!cardToAdd.getSuit().equals(suit)) {
            return false;
        }
        Value nextValue;
        if(cards.size() == 0){
            nextValue = Value.NONE;
        }else{
            nextValue = cards.get(cards.size() - 1).getValue();
        }
        return cardToAdd.isNextValue(nextValue);
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        //only the last card is intractable
        return cardIndex == cards.size() - 1;
    }

    @Override
    protected Coordinates getOffset() {
        return new Coordinates(0,0);
    }

    public boolean isComplete() {
        ICard lastCard = cards.get(cards.size() - 1);
        return lastCard.getValue().equals(Value.KING);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
