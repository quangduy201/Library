package com.library.ui;

import com.library.main.Library;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class UI implements ActionListener, KeyListener {
    private final int SCREEN_WIDTH = 1280;
    private final int SCREEN_HEIGHT = 720;
    private JFrame frame;
    private JLabel headerLabel;
    private JButton backButton;
    private boolean canGoBack = false;
    private int[] enabledButton = { -1, -1, -1, -1 };
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton[] mainButtons = new JButton[4]; // [Book Management, Student Management, Employee Management, Borrow and Return]
    private JButton[] bookButtons = new JButton[7];     // [INPUT, OUTPUT, ADD, EDIT, REMOVE, FIND, STATISTIC]
    private JButton[] studentButtons = new JButton[7];  // [INPUT, OUTPUT, ADD, EDIT, REMOVE, FIND, STATISTIC]
    private JButton[] employeeButtons = new JButton[7]; // [INPUT, OUTPUT, ADD, EDIT, REMOVE, FIND, STATISTIC]
    private JButton[] borrowAndReturnButtons = new JButton[4]; // [BORROW, RETURN, FIND, STATISTIC]
    private JTextArea textBox;
    private JScrollPane scrollPane;
    private Font font;
    private Border border;

    public UI() {
        // initialize the User Interface
        initComponents();
        initMainMenu();
        initBookMenu();
        initStudentMenu();
        initEmployeeMenu();
        initBorrowAndReturnMenu();

        // go to main menu when open the app
        clearScreen();
        gotoMainMenu();
    }

    public JTextArea getTextBox() {
        return textBox;
    }

    public void initComponents() {
        frame = new JFrame("SGU Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setResizable(false);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("res/sgu_logo.png"));
        frame.getContentPane().setBackground(Color.decode("#3e86a0"));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);

        font = new Font("SansSerif", Font.BOLD, 16);
        border = new LineBorder(Color.BLACK, 4);

        headerLabel = new JLabel("SGU LIBRARY", JLabel.CENTER);
        headerLabel.setBounds(450, 10, 400, 55);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        headerLabel.setBorder(border);

        backButton = new JButton("BACK");
        backButton.setBounds(10, 10, 70, 30);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        backButton.setBorder(border);
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.addActionListener(this);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#3e86a0"));

        textBox = new JTextArea(28, 75);
        textBox.setFont(new Font("Consolas", Font.PLAIN, 16));
        textBox.setLineWrap(true);
        textBox.setEditable(false);
        textBox.addKeyListener(this);

        scrollPane = new JScrollPane(textBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        rightPanel = new JPanel();
        rightPanel.setBounds(500, 100, 700, 550);
        rightPanel.setFont(font);
        rightPanel.setBorder(border);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(scrollPane);

        frame.add(headerLabel);
        frame.add(backButton);
        frame.add(leftPanel);
        frame.add(rightPanel);

        frame.setVisible(true);
    }

    public void initMainMenu() {
        mainButtons[0] = new JButton("Book Management");
        mainButtons[1] = new JButton("Student Management");
        mainButtons[2] = new JButton("Employee Management");
        mainButtons[3] = new JButton("Borrow and Return");
        for (JButton button : mainButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setBorder(border);
            button.setFocusable(false);
        }
    }

    public void initBookMenu() {
        bookButtons[0] = new JButton("Input Book");
        bookButtons[1] = new JButton("Output Book");
        bookButtons[2] = new JButton("Add Book");
        bookButtons[3] = new JButton("Edit Book");
        bookButtons[4] = new JButton("Remove Book");
        bookButtons[5] = new JButton("Find Book");
        bookButtons[6] = new JButton("Statistic Book");
        for (JButton button : bookButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setBorder(border);
            button.setFocusable(false);
        }
    }

    public void initStudentMenu() {
        studentButtons[0] = new JButton("Input Student");
        studentButtons[1] = new JButton("Output Student");
        studentButtons[2] = new JButton("Add Student");
        studentButtons[3] = new JButton("Edit Student");
        studentButtons[4] = new JButton("Remove Student");
        studentButtons[5] = new JButton("Find Student");
        studentButtons[6] = new JButton("Statistic Student");
        for (JButton button : studentButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setBorder(border);
            button.setFocusable(false);
        }
    }

    public void initEmployeeMenu() {
        employeeButtons[0] = new JButton("Input Employee");
        employeeButtons[1] = new JButton("Output Employee");
        employeeButtons[2] = new JButton("Add Employee");
        employeeButtons[3] = new JButton("Edit Employee");
        employeeButtons[4] = new JButton("Remove Employee");
        employeeButtons[5] = new JButton("Find Employee");
        employeeButtons[6] = new JButton("Statistic Employee");
        for (JButton button : employeeButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setBorder(border);
            button.setFocusable(false);
        }
    }

    public void initBorrowAndReturnMenu() {
        borrowAndReturnButtons[0] = new JButton("Borrow Book");
        borrowAndReturnButtons[1] = new JButton("Return Book");
        borrowAndReturnButtons[2] = new JButton("Find Bill");
        borrowAndReturnButtons[3] = new JButton("Statistic Bill");
        for (JButton button : borrowAndReturnButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setBorder(border);
            button.setFocusable(false);
        }
    }

    public void clearScreen() {
        leftPanel.removeAll();
        leftPanel.revalidate();
        leftPanel.repaint();

        textBox.removeAll();
        textBox.revalidate();
        textBox.repaint();
    }

    public void gotoMainMenu() {
        canGoBack = false;
        textBox.setText("\t\t\t\t-- SGU LIBRARY --\nPLease choose what you want to do.\n");
        textBox.setEditable(false);
        leftPanel.setBounds(50, 220, 400, 310);
        leftPanel.setLayout(new GridLayout(4, 1, 0, 10));
        for (JButton button : mainButtons) {
            leftPanel.add(button);
        }
    }

    public void gotoBookMenu() {
        canGoBack = true;
        leftPanel.setBounds(50, 100, 400, 550);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : bookButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoStudentMenu() {
        canGoBack = true;
        leftPanel.setBounds(50, 100, 400, 550);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : studentButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoEmployeeMenu() {
        canGoBack = true;
        leftPanel.setBounds(50, 100, 400, 550);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : employeeButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoBorrowAndReturnMenu() {
        canGoBack = true;
        leftPanel.setBounds(50, 220, 400, 310);
        leftPanel.setLayout(new GridLayout(4, 1, 0, 10));
        for (JButton button : borrowAndReturnButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // MAIN MENU
        if (e.getSource() == mainButtons[0]) { // Book Management
            clearScreen();
            gotoBookMenu();
        }
        if (e.getSource() == mainButtons[1]) { // Student Management
            clearScreen();
            gotoStudentMenu();
        }
        if (e.getSource() == mainButtons[2]) { // Employee Management
            clearScreen();
            gotoEmployeeMenu();
        }
        if (e.getSource() == mainButtons[3]) { // Borrow and Return
            clearScreen();
            gotoBorrowAndReturnMenu();
        }
        if (e.getSource() == backButton) { // BACK
            clearScreen();
            gotoMainMenu();
            for (int i = 0; i < enabledButton.length; i++)
                enabledButton[i] = -1;
        }
        backButton.setVisible(canGoBack);
        // BOOK MANAGEMENT MENU
        if (e.getSource() == bookButtons[0]) { // Input Book
            Library.getBookManagement().input();
            enabledButton[0] = 0;
        }
        if (e.getSource() == bookButtons[1]) { // Output Book
            Library.getBookManagement().output();
            enabledButton[0] = 1;
        }
        if (e.getSource() == bookButtons[2]) { // Add Book
            Library.getBookManagement().output();
            enabledButton[0] = 2;
        }
        if (e.getSource() == bookButtons[3]) { // Edit Book
            Library.getBookManagement().output();
            enabledButton[0] = 3;
        }
        if (e.getSource() == bookButtons[4]) { // Remove Book
            Library.getBookManagement().output();
            enabledButton[0] = 4;
        }
        if (e.getSource() == bookButtons[5]) { // Find Book
            Library.getBookManagement().output();
            enabledButton[0] = 5;
        }
        if (e.getSource() == bookButtons[6]) { // Statistic Book
            Library.getBookManagement().output();
            enabledButton[0] = 6;
        }
        if (enabledButton[0] != -1) {
            for (int i = 0; i < bookButtons.length; i++)
                if (i != enabledButton[0])
                    bookButtons[i].setEnabled(false);
        }
        if (enabledButton[1] != -1) {
            for (int i = 0; i < studentButtons.length; i++)
                if (i != enabledButton[1])
                    studentButtons[i].setEnabled(false);
        }
        if (enabledButton[2] != -1) {
            for (int i = 0; i < employeeButtons.length; i++)
                if (i != enabledButton[2])
                    employeeButtons[i].setEnabled(false);
        }
        if (enabledButton[3] != -1) {
            for (int i = 0; i < borrowAndReturnButtons.length; i++)
                if (i != enabledButton[3])
                    borrowAndReturnButtons[i].setEnabled(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // BACK
            clearScreen();
            gotoMainMenu();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Hello");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
