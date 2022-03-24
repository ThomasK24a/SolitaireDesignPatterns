package main.java.com.nhlstenden.solitaire.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu {
    private JFrame mainframe;
    private JButton startButton;
    private JPanel mainPanel;
    private JButton exitButton;

    public MainMenu() {
        mainframe = new JFrame("Solitaire");
//        mainframe.setSize(500,500);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.mainPanel);
        mainframe.pack();
        mainframe.setVisible(true);

        createStartButton();
        createExitButton();

    }

    public void createExitButton(){
        exitButton.addActionListener(e -> System.out.print("exit \n"));
    }

    public void createStartButton() {
        startButton.addActionListener(e -> System.out.print("start \n"));
    }
}
