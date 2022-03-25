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

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(20,20));
        button.setMaximumSize(new Dimension(20,20));
        startButton.setIcon(icon);

        mainframe = new JFrame("Solitaire");
        mainframe.add(button);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.mainPanel);
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
                new Game();
            }
        });
    }
}
