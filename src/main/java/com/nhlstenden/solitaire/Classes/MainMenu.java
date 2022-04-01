package main.java.com.nhlstenden.solitaire.Classes;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    ImageIcon iconStart;
    ImageIcon iconExit;
    JButton startButton;
    JButton exitButton;

    public MainMenu() {
        super("Solitaire");

        setLayout(null);
        setBackground(new ColorUIResource(0, 153, 153));
        setSize(900, 750);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iconStart = new ImageIcon("src/resources/card_sprites/spade.png");
        iconExit = new ImageIcon("src/resources/card_sprites/heart.png");

        setSize(800, 500);
        JLayeredPane backGroundPanel = new JLayeredPane();
        setContentPane(backGroundPanel);

        startButton = new JButton();
        exitButton = new JButton();


        createStartButton();
        createExitButton();

    }

    public void createExitButton() {

        exitButton.setIcon(iconExit);
        exitButton.setBounds(200, 400, 80, 50);
        exitButton.setVisible(true);
        add(exitButton);
        validate();
        exitButton.addActionListener(e ->
                System.exit(0)
        );
    }

    public void createStartButton() {
        startButton.setIcon(iconStart);
        startButton.setBounds(100, 400, 80, 50);
        startButton.setVisible(true);
        add(startButton);
        validate();
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameBoard.getInstance();
            }
        });
    }
}
