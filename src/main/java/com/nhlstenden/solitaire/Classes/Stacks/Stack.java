package main.java.com.nhlstenden.solitaire.Classes.Stacks;

import javax.swing.*;

public class Stack extends JPanel {
    JButton stackButton = new JButton();

    public Stack() {
        Icon icon = new ImageIcon("src/resources/card_sprites/blank_front_with_num_boarders.png");
        stackButton.setIcon(icon);
        add(stackButton);
    }
}
