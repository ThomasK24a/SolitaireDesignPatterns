package main.java.com.nhlstenden.solitaire.GUI;

import main.java.com.nhlstenden.solitaire.Classes.GameBoard;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class MainMenu {
    private final JFrame mainframe;
    private JButton startButton;
    private JPanel mainPanel;
    private JButton exitButton;

    public MainMenu() {

        ImageIcon iconStart = new ImageIcon("src/resources/card_sprites/spade.png");
        ImageIcon iconExit = new ImageIcon("src/resources/card_sprites/heart.png");
        startButton.setIcon(iconStart);
        exitButton.setIcon(iconExit);

        mainframe = new JFrame("Solitaire");

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.mainPanel);
        mainframe.setSize(800, 500);
        mainframe.pack();
        mainPanel.setBackground(new ColorUIResource(0, 153, 153));
        mainframe.setVisible(true);

        createStartButton();
        createExitButton();

    }

    public void createExitButton() {
        exitButton.addActionListener(e ->
                System.exit(0)
        );
    }

    public void createStartButton() {
        startButton.addActionListener(e -> GameBoard.getInstance());
    }
}
