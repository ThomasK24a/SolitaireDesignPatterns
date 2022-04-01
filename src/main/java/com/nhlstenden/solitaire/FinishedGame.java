package main.java.com.nhlstenden.solitaire;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class FinishedGame extends JFrame {
    JButton backToMenu;

    public FinishedGame() {
        super("You've Won");
        setLayout(null);
        setBackground(new ColorUIResource(0, 153, 153));
        setSize(900, 750);
        setLocationRelativeTo(null);
        setVisible(true);

        JLayeredPane backGroundPanel = new JLayeredPane();
        setContentPane(backGroundPanel);
        createBackToMenuButton();
    }

    private void createBackToMenuButton(){

        backToMenu = new JButton();
        backToMenu.setBounds(400, 300, 200,50);
        backToMenu.setIcon(new ImageIcon("src/resources/card_sprites/spade.png"));
        backToMenu.setText("Victory");
        add(backToMenu);

        backToMenu.addActionListener(e -> GameManager.getInstance().restartGame());
    }
}
