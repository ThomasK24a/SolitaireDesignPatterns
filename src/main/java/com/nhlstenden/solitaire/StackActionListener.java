package main.java.com.nhlstenden.solitaire;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.MoveStack;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StackActionListener implements ActionListener {

    private ICard card;
    private CardStack finishStack;

    public StackActionListener(ICard card, CardStack finishStack) {
        this.card = card;
        this.finishStack = finishStack;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        finishStack.canAcceptStack(new MoveStack());
    }

    public void setCard(ICard card) {
        System.out.println(card.getSuit());
        this.card = card;
    }
}
