package main.java.com.nhlstenden.solitaire.GUI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class GameBoard {
    ImageIcon icon = new ImageIcon("images/clock.png");
    private final JFrame mainframe;

    private JPanel mainPanel;
    private JButton card;

    public GameBoard() {
        ImageIcon icon = new ImageIcon("images/clock.png");

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(20,20));
        button.setMaximumSize(new Dimension(20,20));
        card.setIcon(icon);

        mainPanel.setBackground(Color.gray);
        mainframe = new JFrame("Solitaire");
        mainframe.add(button);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setContentPane(this.mainPanel);
        mainframe.pack();
        mainframe.setVisible(true);
    }
}
