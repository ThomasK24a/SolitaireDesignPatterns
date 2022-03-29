package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.CardLocation;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Classes.DecoratorLibrary;
import main.java.com.nhlstenden.solitaire.Classes.GameBoard;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DeckStack extends CardStack {
    WasteStack wasteStack;
    StackButton stackButton;

    public DeckStack(WasteStack wasteStack, Coordinates stackCoordinates) {
        super(stackCoordinates);
        this.wasteStack = wasteStack;
        initializeStackButton(stackCoordinates);
    }

    private void initializeStackButton(Coordinates stackCoordinates){
        ImageIcon deckIcon = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
        this.stackButton = new StackButton(this, deckIcon);
        stackButton.setBounds(stackCoordinates.getX(), stackCoordinates.getY(), 65, 90);

        stackButton.stackButton.addActionListener(e ->  {
            if(cards.size() == 0){
                addWasteStack();
            }else{
                //TODO: implement cards getting added to waste
            }
        });
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        //only the last card is intractable and needs to be face up
        return cardIndex == cards.size() - 1 && cards.get(cardIndex).isFaceUp();
    }

    @Override
    protected Coordinates getOffset() {
        return new Coordinates(0,0);
    }

    public List<ICard> drawThree() {
        int toDraw = Math.min(cards.size(), 3);
        List<ICard> newDeck = cards.subList(0, cards.size() - toDraw);
        List<ICard> drawnCards = cards.subList(cards.size() - toDraw, cards.size());
        wasteStack.addCards(drawnCards);
        cards = newDeck;
        for (ICard card : drawnCards){
            System.out.println(card.toString());
        }

        return drawnCards;
    }

    public void addWasteStack() {
        cards.addAll(wasteStack.getAndClearAll());
    }
}
