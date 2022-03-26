package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;

public class MoveStack extends CardStack {
    @Override
    public boolean canAcceptStack(ArrayList<ICard> cardStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return false;
    }
}
