package main.java.com.nhlstenden.solitaire.Classes;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    public CardPanel() {
        Icon ic0=new ImageIcon("pic/starB0.jpg");
        Icon ic10=new ImageIcon("pic/starB10.jpg");

        setBounds(40, 80, 200, 200);
        JLabel b1 = new JLabel("Button 1");
        b1.setBounds(50, 100, 80, 30);
        b1.setBackground(Color.yellow);
        b1.setIcon(ic0);
        setBounds(40,80,200,200);
        setBackground(Color.black);
        JLabel b2 = new JLabel("Button 2");
        b2.setIcon(ic10);
        b2.setBounds(100, 100, 80, 30);
        b2.setBackground(Color.green);
        add(b1);
        add(b2);
    }
}
