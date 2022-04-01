package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import java.util.List;

public class DeckStack extends CardStack {
    WasteStack wasteStack;
    StackButton stackButton;

    public DeckStack(WasteStack wasteStack, Coordinates stackCoordinates) {
        super(stackCoordinates);
        this.wasteStack = wasteStack;
        initializeStackButton(stackCoordinates);
    }

    private void initializeStackButton(Coordinates stackCoordinates) {
        ImageIcon deckIcon = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
        this.stackButton = new StackButton(this, deckIcon);
        stackButton.setBounds(stackCoordinates.getX(), stackCoordinates.getY(), 65, 95);

        stackButton.getStackButton().addActionListener(e -> {
            if (cards.size() == 0) {
                addWasteStack();
            }else{
                MoveStack moveStack = drawThree();
                this.wasteStack.addCards(moveStack.getCards());
                moveStack.moveCardSprites(wasteStack);
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
        return new Coordinates(0, 0);
    }

    public MoveStack drawThree() {
        int toDraw = Math.min(cards.size(), 3);
        List<ICard> newDeck = cards.subList(0, cards.size() - toDraw);
        List<ICard> drawnCards = cards.subList(cards.size() - toDraw, cards.size());
        MoveStack moveStack = new MoveStack(drawnCards);
        cards = newDeck;
        for (ICard card : drawnCards) {
            System.out.println(card.toString());
        }

        return moveStack;
    }

    public void addWasteStack() {
        MoveStack moveStack = wasteStack.getAndClearAll();
        cards.addAll(moveStack.getCards());
        moveStack.moveCardSprites(this);
        for(ICard card : moveStack.getCards()){
            card.flipCard(false);
        }

    }

    public StackButton getStackButton(){
        return stackButton;
    }

    public StackButton getButton() {
        return this.stackButton;
    }
}
