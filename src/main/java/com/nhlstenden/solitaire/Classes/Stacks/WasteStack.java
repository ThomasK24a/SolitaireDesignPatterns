package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Coordinates;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.awt.event.ActionEvent;
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
        return false;
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
