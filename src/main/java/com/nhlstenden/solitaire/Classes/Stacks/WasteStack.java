package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public class WasteStack extends CardStack {
    public WasteStack(Coordinates stackCoordinates) {
        super(stackCoordinates);
    }

    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return getLastCard().isFaceUp();
    }

    @Override
    protected Coordinates getOffset() {
        return new Coordinates(0,30);
    }

    public MoveStack getAndClearAll(){
        MoveStack moveStack = new MoveStack(this.cards);
        this.cards = new ArrayList<>();
        return moveStack;
    }

    @Override
    public void addCards(List<ICard> cards) {
        for(ICard card : cards){
            card.onCardMove(this);
            card.flipCard(true);
        }
        this.cards.addAll(cards);
    }

    @Override
    public Coordinates getCoordsOfCard(int index) {
        Coordinates cardCoordinates = getStackCoordinates();
        for(int i = 0; i < index; i++){
            if(i >= this.cards.size() - 3){
                cardCoordinates.addCoordinates(getOffset());
            }
        }
        return cardCoordinates;
    }
}
