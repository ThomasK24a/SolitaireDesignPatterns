package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;

import java.util.ArrayList;

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
        return new Coordinates(0,0);
    }

    public MoveStack getAndClearAll(){
        MoveStack moveStack = new MoveStack(this.cards);
        this.cards = new ArrayList<>();
        return moveStack;
    }

}
