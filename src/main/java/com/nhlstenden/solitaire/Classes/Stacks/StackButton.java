package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import main.java.com.nhlstenden.solitaire.Abstract.CardStack;

import javax.swing.*;

public class StackButton extends JPanel {
    JButton stackButton = new JButton();
    CardStack cardStack;

    public StackButton(CardStack stack, Icon buttonIcon) {
        this.cardStack = stack;
        Icon cardIcon = new ImageIcon("src/resources/card_sprites/blank_front_with_num_boarders.png");
        stackButton.setIcon(cardIcon);
        initializeButton(buttonIcon);
        add(stackButton);
    }

    /**
     * add icon and create listener for the button.
     */
    public void initializeButton(Icon icon) {
        this.setIconImage(icon);
        this.setVisible(true);
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
