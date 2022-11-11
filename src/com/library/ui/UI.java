package com.library.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;

public class UI implements ActionListener, KeyListener {
    private final int SCREEN_WIDTH = 1000;
    private final int SCREEN_HEIGHT = 600;
    private final int GAP = 20;
    private final int HEADER_WIDTH = SCREEN_WIDTH * 40 / 100;
    private final int HEADER_HEIGHT = SCREEN_HEIGHT * 10 / 100;
    private final int LEFT_PANEL_WIDTH = SCREEN_WIDTH * 30 / 100;
    private final int LEFT_PANEL_HEIGHT = SCREEN_HEIGHT * 70 / 100;
    private final int RIGHT_PANEL_WIDTH = SCREEN_WIDTH * 60 / 100;
    private final int RIGHT_PANEL_HEIGHT = SCREEN_HEIGHT * 70 / 100;
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
    private JLabel[] eduBookLabels = new JLabel[6]; // [ID, Name, Remain, Price, PublishDay, Publisher]
    private JLabel[] refBookLabels = new JLabel[7]; // [ID, Name, Remain, Price, PublishDay, Author, Translator]
    private JLabel[] dictionaryLabels = new JLabel[6]; // [ID, Name, Remain, Price, PublishDay, Language]
    private JLabel[] studentLabels = new JLabel[11]; // [ID, Name, DoB, Gender, Phone, Address, Email, School, Faculty, Major, Classroom]
    private JLabel[] employeeLabels = new JLabel[10]; // [ID, Name, DoB, Gender, Phone, Address, Email, Roll, StartDate, Salary]
    private JTextField[] eduBookText = new JTextField[6];
    private JTextField[] refBookText = new JTextField[7];
    private JTextField[] dictionaryText = new JTextField[6];
    private JTextField[] studentText = new JTextField[11];
    private JTextField[] employeeText = new JTextField[10];
    private ButtonGroup bookButtonGroup;
    private JRadioButton[] bookRadioButtons = new JRadioButton[3];
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
        initInput();
        initOutput();
        initAdd();
        initEdit();
        initFind();
        initRemove();
        initStatistic();
        initBorrow();
        initReturn();

        // go to main menu when open the app
        gotoMainMenu();
        refreshScreen();
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
        headerLabel.setBounds((SCREEN_WIDTH - HEADER_WIDTH) / 2, GAP, HEADER_WIDTH, HEADER_HEIGHT);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 50));
        headerLabel.setBorder(border);

        backButton = new JButton("MAIN MENU");
        backButton.setBounds(GAP, GAP, SCREEN_WIDTH * 15 / 100, HEADER_HEIGHT / 2);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        backButton.setBorder(border);
        backButton.setFocusable(false);
        backButton.setVisible(false);
        backButton.addActionListener(this);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.decode("#3e86a0"));

        rightPanel = new JPanel();
        rightPanel.setBounds(GAP + LEFT_PANEL_WIDTH + GAP, GAP + HEADER_HEIGHT + GAP, RIGHT_PANEL_WIDTH, RIGHT_PANEL_HEIGHT);
        rightPanel.setBorder(border);
        rightPanel.setBackground(Color.WHITE);

        frame.add(headerLabel);
        frame.add(backButton);
        frame.add(leftPanel);
        frame.add(rightPanel);
        frame.addKeyListener(this);

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

    public void initInput() {
        Font inputFont = new Font("Consolas", Font.PLAIN, 16);

        bookRadioButtons[0] = new JRadioButton("Education Book");
        bookRadioButtons[1] = new JRadioButton("Reference Book");
        bookRadioButtons[2] = new JRadioButton("Dictionary");
        bookButtonGroup = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            bookRadioButtons[i].setBackground(Color.WHITE);
            bookRadioButtons[i].setFont(font);
            bookRadioButtons[i].setFocusable(false);
            bookRadioButtons[i].addActionListener(this);
            bookButtonGroup.add(bookRadioButtons[i]);
        }

        eduBookLabels[0] = new JLabel("Book ID");
        eduBookLabels[1] = new JLabel("Book name");
        eduBookLabels[2] = new JLabel("Book remain");
        eduBookLabels[3] = new JLabel("Book price");
        eduBookLabels[4] = new JLabel("Book publish day");
        eduBookLabels[5] = new JLabel("Book publisher");
        for (int i = 0; i < 6; i++) {
            eduBookLabels[i].setFont(font);
            if (i == 4) // Format Education Book publish day
                eduBookText[i] = new JFormattedTextField(createFormatter("##/##/####"));
            else
                eduBookText[i] = new JTextField();
            eduBookText[i].setFont(inputFont);
        }

        refBookLabels[0] = new JLabel("Book ID");
        refBookLabels[1] = new JLabel("Book name");
        refBookLabels[2] = new JLabel("Book remain");
        refBookLabels[3] = new JLabel("Book price");
        refBookLabels[4] = new JLabel("Book publish day");
        refBookLabels[5] = new JLabel("Book author");
        refBookLabels[6] = new JLabel("Book translator");
        for (int i = 0; i < 7; i++) {
            refBookLabels[i].setFont(font);
            if (i == 4) // Format Reference Book publish day
                refBookText[i] = new JFormattedTextField(createFormatter("##/##/####"));
            else
                refBookText[i] = new JTextField();
            refBookText[i].setFont(inputFont);
        }

        dictionaryLabels[0] = new JLabel("Dictionary ID");
        dictionaryLabels[1] = new JLabel("Dictionary name");
        dictionaryLabels[2] = new JLabel("Dictionary remain");
        dictionaryLabels[3] = new JLabel("Dictionary price");
        dictionaryLabels[4] = new JLabel("Dictionary publish day");
        dictionaryLabels[5] = new JLabel("Dictionary language");
        for (int i = 0; i < 6; i++) {
            dictionaryLabels[i].setFont(font);
            if (i == 4) // Format Dictionary publish day
                dictionaryText[i] = new JFormattedTextField(createFormatter("##/##/####"));
            else
                dictionaryText[i] = new JTextField();
            dictionaryText[i].setFont(inputFont);
        }

        studentLabels[0] = new JLabel("Student ID");
        studentLabels[1] = new JLabel("Student name");
        studentLabels[2] = new JLabel("Student date of birth");
        studentLabels[3] = new JLabel("Student gender (M|F)");
        studentLabels[4] = new JLabel("Student phone");
        studentLabels[5] = new JLabel("Student address");
        studentLabels[6] = new JLabel("Student email");
        studentLabels[7] = new JLabel("Student school");
        studentLabels[8] = new JLabel("Student faculty");
        studentLabels[9] = new JLabel("Student major");
        studentLabels[10] = new JLabel("Student classroom");
        for (int i = 0; i < 11; i++) {
            studentLabels[i].setFont(font);
            studentText[i] = new JTextField();
            studentText[i].setFont(inputFont);
        }
        studentText[2] = new JFormattedTextField(createFormatter("##/##/####"));
        studentText[2].setFont(inputFont);
        studentText[7].setText("Đại học Sài Gòn");
        studentText[7].setEditable(false);

        employeeLabels[0] = new JLabel("Employee ID");
        employeeLabels[1] = new JLabel("Employee name");
        employeeLabels[2] = new JLabel("Employee date of birth");
        employeeLabels[3] = new JLabel("Employee gender (M|F)");
        employeeLabels[4] = new JLabel("Employee phone");
        employeeLabels[5] = new JLabel("Employee address");
        employeeLabels[6] = new JLabel("Employee email");
        employeeLabels[7] = new JLabel("Employee roll");
        employeeLabels[8] = new JLabel("Employee start date");
        employeeLabels[9] = new JLabel("Employee salary");
        for (int i = 0; i < 10; i++) {
            employeeLabels[i].setFont(font);
            employeeText[i] = new JTextField();
            employeeText[i].setFont(inputFont);
        }
        employeeText[2] = new JFormattedTextField(createFormatter("##/##/####"));
        employeeText[2].setFont(inputFont);
        employeeText[7] = new JFormattedTextField(createFormatter("##/##/####"));
        employeeText[7].setFont(inputFont);
    }

    public void initOutput() {

    }

    public void initAdd() {

    }

    public void initEdit() {

    }

    public void initRemove() {

    }

    public void initFind() {

    }

    public void initStatistic() {

    }

    public void initBorrow() {

    }

    public void initReturn() {

    }

    public void refreshScreen() {
        leftPanel.revalidate();
        leftPanel.repaint();
        rightPanel.revalidate();
        rightPanel.repaint();
    }

    public void clearScreen() {
        leftPanel.removeAll();
        rightPanel.removeAll();
        refreshScreen();
    }

    public void gotoMainMenu() {
        canGoBack = false;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(4, 1, 0, 10));
        for (JButton button : mainButtons) {
            leftPanel.add(button);
        }
    }

    public void gotoBookMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + GAP, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : bookButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoStudentMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + GAP, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : studentButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoEmployeeMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + GAP, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT);
        leftPanel.setLayout(new GridLayout(7, 1, 0, 10));
        for (JButton button : employeeButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoBorrowAndReturnMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(4, 1, 0, 10));
        for (JButton button : borrowAndReturnButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void showInputBook(String type) {
        for (int i = 0; i < 6; i++) {
            rightPanel.remove(eduBookLabels[i]);
            rightPanel.remove(eduBookText[i]);
            rightPanel.remove(refBookLabels[i]);
            rightPanel.remove(refBookText[i]);
            rightPanel.remove(dictionaryLabels[i]);
            rightPanel.remove(dictionaryText[i]);
        }
        rightPanel.remove(refBookLabels[6]);
        rightPanel.remove(refBookText[6]);
        if (type.equals("Education")) {
            for (int i = 0; i < 6; i++) {
                rightPanel.add(eduBookLabels[i]);
                eduBookLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 200, 25);
                rightPanel.add(eduBookText[i]);
                eduBookText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 300, 25);
            }
        }
        if (type.equals("Reference")) {
            for (int i = 0; i < 7; i++) {
                rightPanel.add(refBookLabels[i]);
                refBookLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 200, 25);
                rightPanel.add(refBookText[i]);
                refBookText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 300, 25);
            }
        }
        if (type.equals("Dictionary")) {
            for (int i = 0; i < 6; i++) {
                rightPanel.add(dictionaryLabels[i]);
                dictionaryLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 200, 25);
                rightPanel.add(dictionaryText[i]);
                dictionaryText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 300, 25);
            }
        }
    }

    public void showInput(String type) {
        backButton.setText("BACK");
        rightPanel.setLayout(null);
        if (type.equals("Book")) {
            bookRadioButtons[0].setSelected(true); // Education Book is selected by default
            for (int i = 0; i < 3; i++) {
                rightPanel.add(bookRadioButtons[i]);
                bookRadioButtons[i].setBounds(RIGHT_PANEL_WIDTH / 20 + i * 200, GAP, 150, 25);
            }
            showInputBook("Education");
        }
        if (type.equals("Student")) {
            for (int i = 0; i < 11; i++) {
                rightPanel.add(studentLabels[i]);
                studentLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT / 8 + i * 33, 200, 25);
                rightPanel.add(studentText[i]);
                studentText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT / 8 + i * 33, 300, 25);
            }
        }
        if (type.equals("Employee")) {
            for (int i = 0; i < 10; i++) {
                rightPanel.add(employeeLabels[i]);
                employeeLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT / 8 + i * 33, 200, 25);
                rightPanel.add(employeeText[i]);
                employeeText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT / 8 + i * 33, 300, 25);
            }
        }
    }

    public void showOutput(String type) {
        backButton.setText("BACK");
    }

    public void showAdd(String type) {
        backButton.setText("BACK");
    }

    public void showEdit(String type) {
        backButton.setText("BACK");
    }

    public void showFind(String type) {
        backButton.setText("BACK");
    }

    public void showRemove(String type) {
        backButton.setText("BACK");
    }

    public void showStatistic(String type) {
        backButton.setText("BACK");
    }

    public void showBorrow() {
        backButton.setText("BACK");
    }

    public void showReturn() {
        backButton.setText("BACK");
    }

    public MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (Exception e) {}
        return formatter;
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
            if (backButton.getText().equals("MAIN MENU")) {
                clearScreen();
                gotoMainMenu();
            } else {
                rightPanel.removeAll();
                backButton.setText("MAIN MENU");
            }
            for (int i = 0; i < enabledButton.length; i++)
                enabledButton[i] = -1;
        }
        backButton.setVisible(canGoBack);

        // BOOK MANAGEMENT MENU
        if (e.getSource() == bookButtons[0]) { // Input Book
            showInput("Book");
            enabledButton[0] = 0;
        }
        if (e.getSource() == bookButtons[1]) { // Output Book
            showOutput("Book");
            enabledButton[0] = 1;
        }
        if (e.getSource() == bookButtons[2]) { // Add Book
            showAdd("Book");
            enabledButton[0] = 2;
        }
        if (e.getSource() == bookButtons[3]) { // Edit Book
            showEdit("Book");
            enabledButton[0] = 3;
        }
        if (e.getSource() == bookButtons[4]) { // Remove Book
            showRemove("Book");
            enabledButton[0] = 4;
        }
        if (e.getSource() == bookButtons[5]) { // Find Book
            showFind("Book");
            enabledButton[0] = 5;
        }
        if (e.getSource() == bookButtons[6]) { // Statistic Book
            showStatistic("Book");
            enabledButton[0] = 6;
        }

        // STUDENT MANAGEMENT MENU
        if (e.getSource() == studentButtons[0]) { // Input Student
            showInput("Student");
            enabledButton[1] = 0;
        }
        if (e.getSource() == studentButtons[1]) { // Output Student
            showOutput("Student");
            enabledButton[1] = 1;
        }
        if (e.getSource() == studentButtons[2]) { // Add Student
            showAdd("Student");
            enabledButton[1] = 2;
        }
        if (e.getSource() == studentButtons[3]) { // Edit Student
            showEdit("Student");
            enabledButton[1] = 3;
        }
        if (e.getSource() == studentButtons[4]) { // Remove Student
            showRemove("Student");
            enabledButton[1] = 4;
        }
        if (e.getSource() == studentButtons[5]) { // Find Student
            showFind("Student");
            enabledButton[1] = 5;
        }
        if (e.getSource() == studentButtons[6]) { // Statistic Student
            showStatistic("Student");
            enabledButton[1] = 6;
        }

        // EMPLOYEE MANAGEMENT MENU
        if (e.getSource() == employeeButtons[0]) { // Input Employee
            showInput("Employee");
            enabledButton[2] = 0;
        }
        if (e.getSource() == employeeButtons[1]) { // Output Employee
            showOutput("Employee");
            enabledButton[2] = 1;
        }
        if (e.getSource() == employeeButtons[2]) { // Add Employee
            showAdd("Employee");
            enabledButton[2] = 2;
        }
        if (e.getSource() == employeeButtons[3]) { // Edit Employee
            showEdit("Employee");
            enabledButton[2] = 3;
        }
        if (e.getSource() == employeeButtons[4]) { // Remove Employee
            showRemove("Employee");
            enabledButton[2] = 4;
        }
        if (e.getSource() == employeeButtons[5]) { // Find Employee
            showFind("Employee");
            enabledButton[2] = 5;
        }
        if (e.getSource() == employeeButtons[6]) { // Statistic Employee
            showStatistic("Employee");
            enabledButton[2] = 6;
        }

        // BORROW AND RETURN MENU
        if (e.getSource() == borrowAndReturnButtons[0]) { // Borrow Book
            showBorrow();
            enabledButton[3] = 0;
        }
        if (e.getSource() == borrowAndReturnButtons[1]) { // Return Book
            showReturn();
            enabledButton[3] = 1;
        }
        if (e.getSource() == borrowAndReturnButtons[2]) { // Find Bill
            showFind("Bill");
            enabledButton[3] = 2;
        }
        if (e.getSource() == borrowAndReturnButtons[3]) { // Statistic Bill
            showStatistic("Bill");
            enabledButton[3] = 3;
        }

        // DISABLE THE OTHER BUTTONS
        if (enabledButton[0] != -1) { // if a button in "Book Management" is clicked, disable the others
            for (JButton button : bookButtons)
                button.setEnabled(false);
            bookButtons[enabledButton[0]].setEnabled(true);
        } else {
            for (JButton button : bookButtons)
                button.setEnabled(true);
        }
        if (enabledButton[1] != -1) { // if a button in "Student Management" is clicked, disable the others
            for (JButton button : studentButtons)
                button.setEnabled(false);
            studentButtons[enabledButton[1]].setEnabled(true);
        } else {
            for (JButton button : studentButtons)
                button.setEnabled(true);
        }
        if (enabledButton[2] != -1) { // if a button in "Employee Management" is clicked, disable the others
            for (JButton button : employeeButtons)
                button.setEnabled(false);
            employeeButtons[enabledButton[2]].setEnabled(true);
        } else {
            for (JButton button : employeeButtons)
                button.setEnabled(true);
        }
        if (enabledButton[3] != -1) { // if a button in "Borrow and Return" is clicked, disable the others
            for (JButton button : borrowAndReturnButtons)
                button.setEnabled(false);
            borrowAndReturnButtons[enabledButton[3]].setEnabled(true);
        } else {
            for (JButton button : borrowAndReturnButtons)
                button.setEnabled(true);
        }

        // BOOK RADIO BUTTONS
        if (e.getSource() == bookRadioButtons[0])
            showInputBook("Education");
        if (e.getSource() == bookRadioButtons[1])
            showInputBook("Reference");
        if (e.getSource() == bookRadioButtons[2])
            showInputBook("Dictionary");
        refreshScreen();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (backButton.getText().equals("MAIN MENU")) {
                clearScreen();
                gotoMainMenu();
            } else {
                rightPanel.removeAll();
                backButton.setText("MAIN MENU");
            }
            for (int i = 0; i < enabledButton.length; i++)
                enabledButton[i] = -1;
        }
        backButton.setVisible(canGoBack);

        // DISABLE THE OTHER BUTTONS
        if (enabledButton[0] != -1) { // if a button in "Book Management" is clicked, disable the others
            for (JButton button : bookButtons)
                button.setEnabled(false);
            bookButtons[enabledButton[0]].setEnabled(true);
        } else {
            for (JButton button : bookButtons)
                button.setEnabled(true);
        }
        if (enabledButton[1] != -1) { // if a button in "Student Management" is clicked, disable the others
            for (JButton button : studentButtons)
                button.setEnabled(false);
            studentButtons[enabledButton[1]].setEnabled(true);
        } else {
            for (JButton button : studentButtons)
                button.setEnabled(true);
        }
        if (enabledButton[2] != -1) { // if a button in "Employee Management" is clicked, disable the others
            for (JButton button : employeeButtons)
                button.setEnabled(false);
            employeeButtons[enabledButton[2]].setEnabled(true);
        } else {
            for (JButton button : employeeButtons)
                button.setEnabled(true);
        }
        if (enabledButton[3] != -1) { // if a button in "Borrow and Return" is clicked, disable the others
            for (JButton button : borrowAndReturnButtons)
                button.setEnabled(false);
            borrowAndReturnButtons[enabledButton[3]].setEnabled(true);
        } else {
            for (JButton button : borrowAndReturnButtons)
                button.setEnabled(true);
        }
        refreshScreen();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
