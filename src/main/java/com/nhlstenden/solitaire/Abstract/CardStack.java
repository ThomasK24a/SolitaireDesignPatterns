package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.MoveStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class CardStack implements ActionListener
{
    protected List<ICard> cards;
    private final JButton suitButton = new JButton();
    private static final Icon BACK_SPRITE = new ImageIcon("src/resources/card_sprites/back_red_basic.png");
    private Coordinates stackCoordinates;

    public CardStack(Coordinates stackCoordinates) {
        this.cards = new ArrayList<>();
        this.stackCoordinates = stackCoordinates;
    }

    public Coordinates getStackCoordinates() {
        return new Coordinates(stackCoordinates.getX(), stackCoordinates.getY());
    }

    public void setStackCoordinates(int x, int y) {
        this.stackCoordinates = new Coordinates(x,y);
    }

    public void addCards(List<ICard> cards) {
        for(ICard card : cards){
            card.onCardMove(this);
        }

        this.cards.addAll(cards);
    }

    public List<ICard> getCards() {
        return cards;
    }

    public abstract boolean canAcceptStack(MoveStack moveStack);

    public abstract boolean isIntractable(int cardIndex);

    public int findCardIndex(ICard cardToFind){
        int i = 0;
        for(ICard card : cards){
            if(card == cardToFind) return i;
            i++;
        }
        return -1;
    }

    public MoveStack getAllBelow(int startingIndex){
        MoveStack moveStack = new MoveStack();
        moveStack.getCards().addAll(cards.subList(startingIndex, cards.size()));
        return moveStack;
    }

    public void removeAllBelow(int startingIndex){
        cards = cards.subList(0, startingIndex);
    }

    public Coordinates getCoordsOfCard(int index){
        Coordinates cardCoordinates = getStackCoordinates();
        for(int i = 0; i < index; i++){
            cardCoordinates.addCoordinates(getOffset());
        }
        return cardCoordinates;
    }

    /**
     * Will get the last card from this board stack
     * @return the last card, null if the stack is empty
     */
    public ICard getLastCard(){
        if(cards.size() == 0) return null;
        return cards.get(cards.size() - 1);
    }

    protected abstract Coordinates getOffset();
}
