package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;
import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.GameBoard;

import javax.swing.*;
import java.awt.*;

public class StackButton extends JPanel {
    JButton stackButton = new JButton();
    CardStack cardStack;

    public StackButton(CardStack stack) {
        this.cardStack = stack;
        Icon icon = new ImageIcon("src/resources/card_sprites/blank_front_with_num_boarders.png");
        stackButton.setIcon(icon);
        add(stackButton);
    }

    /**
     * create listener for the suit button.
     */
    public void createButton() {
        stackButton.addActionListener(e -> {
            Object obj = e.getSource();
            if (obj instanceof JButton cb) {
                Component component = cb.getParent();
                if (component instanceof Card card) {
                    GameBoard.getInstance().onCardButtonClick(card);
                }
            }
        });
    }

    public JButton getStackButton() {
        return stackButton;
    }

    public CardStack getCardStack() {
        return cardStack;
    }

    public void setIconImage(Icon icon){
        stackButton.setIcon(icon);
        validate();
    }
}
