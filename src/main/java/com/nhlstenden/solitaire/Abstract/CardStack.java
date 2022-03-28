package main.java.com.nhlstenden.solitaire.Abstract;

import main.java.com.nhlstenden.solitaire.Classes.Stacks.MoveStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class CardStack extends JPanel {
    protected List<ICard> cards;
    private final JButton suitButton = new JButton();
    private static final Icon BACK_SPRITE = new ImageIcon("src/resources/card_sprites/back_red_basic.png");

    public CardStack() {
        this.cards = new ArrayList<ICard>();
        suitButton.setIcon(BACK_SPRITE);
        this.setBorder();
        setSuitSprite();
        createButton();
    }

    public void setSuitSprite() {
        add(suitButton);
        validate();
    }

    public void setPosition(int x, int y) {
        setBounds(x, y, 65, 90);
        setBackground(Color.lightGray);
        validate();
    }

    public void createButton() {
        suitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void setBorder(){
        Border border = BorderUIResource.getBlackLineBorderUIResource();
        this.setBorder(border);
        validate();
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
}
