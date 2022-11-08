package com.library.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI implements ActionListener {
    JFrame frame;
    JTextField[] textFields = new JTextField[11];
    JButton[] buttons = new JButton[10];
    JPanel panel;
    Font font = new Font("SansSerif", Font.BOLD, 30);
    String selection;

    public UI() {
        frame = new JFrame("SGU Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/sgu_logo.png"));
        frame.setLayout(null);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
