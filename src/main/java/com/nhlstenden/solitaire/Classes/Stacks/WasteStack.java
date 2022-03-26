package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;

public class WasteStack extends CardStack {
    @Override
    public boolean canAcceptStack(MoveStack moveStack) {
        return false;
    }

    @Override
    public boolean isIntractable(int cardIndex) {
        return false;
    }
}
