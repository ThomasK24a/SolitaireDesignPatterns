package main.java.com.nhlstenden.solitaire;

import javax.swing.*;

public class MainMenu {
    private JFrame mainframe;
    private JButton button1;
    private JPanel panel1;
    private JButton button2;

    public MainMenu() {
        panel1.setVisible(true);
        panel1.setSize(500,500);
        mainframe = new JFrame("Solitaire");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.panel1);
        mainframe.pack();
        mainframe.setVisible(true);
    }
}
