package main.java.com.nhlstenden.solitaire.GUI;

import main.java.com.nhlstenden.solitaire.Classes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private final JFrame mainframe;
    private JButton startButton;
    private JPanel mainPanel;
    private JButton exitButton;

    public MainMenu() {

        ImageIcon icon = new ImageIcon("images/clock.png");
        startButton.setBounds(50, 50, 300, 50);
        startButton.setIcon(icon);

        mainframe = new JFrame("Solitaire");

        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.mainPanel);
        mainframe.setSize(800,500);
        mainframe.pack();
        mainframe.setVisible(true);

        createStartButton();
        createExitButton();

    }

    public JFrame getMainframe() {
        return mainframe;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void createExitButton() {
        exitButton.addActionListener(e ->
                System.exit(0)
        );
    }

    public void createStartButton() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameBoard();
            }
        });
    }
}
