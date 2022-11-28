package com.library.ui;

import com.library.component.*;
import com.library.main.Library;
import com.library.management.BorrowAndReturn;
import com.library.util.Day;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

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
    private final int[] enabledButton = { -1, -1, -1, -1 };
    private JPanel leftPanel;
    private JPanel rightPanel;
    private final JButton[] mainButtons = new JButton[4]; // [Book Management, Student Management, Employee Management, Borrow and Return]
    private final JButton[] bookButtons = new JButton[3];             // [INPUT, OUTPUT, FIND]
    private final JButton[] studentButtons = new JButton[3];          // [INPUT, OUTPUT, FIND]
    private final JButton[] employeeButtons = new JButton[3];         // [INPUT, OUTPUT, FIND]
    private final JButton[] borrowAndReturnButtons = new JButton[3]; // [BORROW, RETURN, FIND]
    private final JLabel[] eduBookLabels = new JLabel[6];       // [ID, Name, Remain, Price, PublishDay, Publisher]
    private final JLabel[] refBookLabels = new JLabel[6];       // [ID, Name, Remain, Price, PublishDay, Author]
    private final JLabel[] dictionaryLabels = new JLabel[6];    // [ID, Name, Remain, Price, PublishDay, Language]
    private final JLabel[] studentLabels = new JLabel[11];      // [ID, Name, DoB, Gender, Phone, Address, Email, School, Faculty, Major, Classroom]
    private final JLabel[] employeeLabels = new JLabel[10];     // [ID, Name, DoB, Gender, Phone, Address, Email, Roll, StartDate, Salary]
    private final JLabel[] billLabels = new JLabel[7];          // [Status, Person ID, Person name, Books, Borrow day, Return day, Price]
    private final JLabel findLabel = new JLabel();
    private final JTextField findText = new JTextField();
    private final JTextField[] bookText = new JTextField[6];
    private final JTextField[] studentText = new JTextField[11];
    private final JTextField[] employeeText = new JTextField[10];
    private final JTextField[] billText = new JTextField[7];
    private ButtonGroup bookButtonGroup;
    private ButtonGroup findButtonGroup;
    private final JRadioButton[] bookRadioButtons = new JRadioButton[3];
    private final JRadioButton[] findRadioButtons = new JRadioButton[2];
    private JButton addButton;
    private JButton editButton;
    private JButton removeButton;
    private JButton findButton;
    private final String[] booksTableHeader = new String[]{"CAT", "STAT", "ID", "NAME", "REM", "PRICE", "PUBLISH DAY", "NOTE"};
    private final String[] studentsTableHeader = new String[]{"STAT", "ID", "NAME", "DOB", "GENDER", "PHONE", "ADDRESS", "EMAIL", "SCHOOL", "FACULTY", "MAJOR", "CLASS"};
    private final String[] employeesTableHeader = new String[]{"STAT", "ID", "NAME", "DOB", "GENDER", "PHONE", "ADDRESS", "EMAIL", "ROLL", "START DATE", "SALARY"};
    private final String[] billsTableHeader = new String[]{"STAT", "ID", "NAME", "BOOKS", "BORROW", "RETURN", "PRICE"};
    private Book[] booksTemp;
    private Bill[] billsTemp;
    private String[][] booksDataTemp;
    private String[][] booksData;
    private String[][] studentsData;
    private String[][] employeesData;
    private String[][] billsData;
    private JTable booksTableTemp;
    private JTable booksTable;
    private JTable studentsTable;
    private JTable employeesTable;
    private JTable billsTable;
    private JScrollPane booksScrollPaneTemp;
    private JScrollPane booksScrollPane;
    private JScrollPane studentsScrollPane;
    private JScrollPane employeesScrollPane;
    private JScrollPane billsScrollPane;
    private JButton addBookButton;
    private JButton returnBookButton;
    private JButton nextButton;
    private JButton prevButton;
    private JLabel steps;
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
        initFind();
        initBorrow();

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
        rightPanel.setLayout(null);
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
        bookButtons[2] = new JButton("Find Book");
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
        studentButtons[2] = new JButton("Find Student");
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
        employeeButtons[2] = new JButton("Find Employee");
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

        eduBookLabels[0] = new JLabel("Education book ID");
        eduBookLabels[1] = new JLabel("Education book name");
        eduBookLabels[2] = new JLabel("Education book remain");
        eduBookLabels[3] = new JLabel("Education book price");
        eduBookLabels[4] = new JLabel("Education book publish day");
        eduBookLabels[5] = new JLabel("Education book publisher");

        refBookLabels[0] = new JLabel("Reference book ID");
        refBookLabels[1] = new JLabel("Reference book name");
        refBookLabels[2] = new JLabel("Reference book remain");
        refBookLabels[3] = new JLabel("Reference book price");
        refBookLabels[4] = new JLabel("Reference book publish day");
        refBookLabels[5] = new JLabel("Reference book author");

        dictionaryLabels[0] = new JLabel("Dictionary ID");
        dictionaryLabels[1] = new JLabel("Dictionary name");
        dictionaryLabels[2] = new JLabel("Dictionary remain");
        dictionaryLabels[3] = new JLabel("Dictionary price");
        dictionaryLabels[4] = new JLabel("Dictionary publish day");
        dictionaryLabels[5] = new JLabel("Dictionary language");
        for (int i = 0; i < 6; i++) {
            eduBookLabels[i].setFont(font);
            refBookLabels[i].setFont(font);
            dictionaryLabels[i].setFont(font);
            if (i == 4) // Format Dictionary publish day
                bookText[i] = new JFormattedTextField(createFormatter("##/##/####"));
            else
                bookText[i] = new JTextField();
            bookText[i].setFont(inputFont);
        }

        studentLabels[0] = new JLabel("Student ID");
        studentLabels[1] = new JLabel("Student name");
        studentLabels[2] = new JLabel("Student date of birth");
        studentLabels[3] = new JLabel("Student gender");
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
        employeeLabels[3] = new JLabel("Employee gender");
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
        employeeText[8] = new JFormattedTextField(createFormatter("##/##/####"));
        employeeText[8].setFont(inputFont);

        billLabels[0] = new JLabel("Status");
        billLabels[1] = new JLabel("Person ID");
        billLabels[2] = new JLabel("Person name");
        billLabels[3] = new JLabel("Books");
        billLabels[4] = new JLabel("Borrow day");
        billLabels[5] = new JLabel("Return day");
        billLabels[6] = new JLabel("Price");
        for (int i = 0; i < 7; i++) {
            billLabels[i].setFont(font);
            billText[i] = new JTextField();
            billText[i].setFont(inputFont);
        }
        billText[4] = new JFormattedTextField(createFormatter("##/##/####"));
        billText[4].setFont(inputFont);
        billText[5] = new JFormattedTextField(createFormatter("##/##/####"));
        billText[5].setFont(inputFont);

        addButton = new JButton();
        addButton.setBounds(RIGHT_PANEL_WIDTH / 3, RIGHT_PANEL_HEIGHT - 2 * GAP, 200, 30);
        addButton.setBorder(border);
        addButton.setFocusable(false);
        addButton.addActionListener(this);
    }

    public void initOutput() {
        // BOOKS
        Book[] books = Library.getBookManagement().getBooks();
        booksData = new String[0][8];
        for (Book book : books) {
            if (book.getStatus().equals("enabled")) {
                booksData = Arrays.copyOf(booksData, booksData.length + 1);
                booksData[booksData.length - 1] = book.toString().split(", ");
            }
        }
        booksTable = new JTable(new DefaultTableModel(booksData, booksTableHeader)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        booksTable.getTableHeader().setReorderingAllowed(false);
        booksTable.setAutoCreateRowSorter(false);
        booksTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTable.getSelectionModel().addListSelectionListener(e -> {
            if (booksTable.getSelectedRow() != -1) {    // prevent calling the event twice (mouse down and mouse released)
                String[] book = booksData[booksTable.getSelectedRow()];
                if (book[0].equals("EDU"))
                    bookRadioButtons[0].setSelected(true);
                else if (book[0].equals("REF"))
                    bookRadioButtons[1].setSelected(true);
                else
                    bookRadioButtons[2].setSelected(true);
                for (int i = 0; i < bookText.length; i++)
                    bookText[i].setText(book[i + 2]);
                showInput("Book");
                bookText[0].setEditable(false);
                rightPanel.remove(booksScrollPane);
                rightPanel.remove(addButton);
                for (int i = 0; i < 3; i++)
                    rightPanel.remove(bookRadioButtons[i]);
                backButton.setText("BOOK TABLE");
                refreshScreen();
            }
        });
        TableColumnModel booksColumnModel = booksTable.getColumnModel();
        booksColumnModel.getColumn(0).setPreferredWidth(37);
        booksColumnModel.getColumn(2).setPreferredWidth(40);
        booksColumnModel.getColumn(3).setPreferredWidth(160);
        booksColumnModel.getColumn(4).setPreferredWidth(37);
        booksColumnModel.getColumn(5).setPreferredWidth(65);
        booksColumnModel.getColumn(6).setPreferredWidth(87);
        booksColumnModel.getColumn(7).setPreferredWidth(160);
        booksColumnModel.removeColumn(booksColumnModel.getColumn(1));   // remove STAT

        booksScrollPane = new JScrollPane(booksTable);
        booksScrollPane.setBounds(5, 5, 591, 411);

        // STUDENTS
        Student[] students = Library.getStudentManagement().getStudents();
        studentsData = new String[0][11];
        for (Student book : students) {
            if (book.getStatus().equals("enabled")) {
                studentsData = Arrays.copyOf(studentsData, studentsData.length + 1);
                studentsData[studentsData.length - 1] = book.toString().split(", ");
            }
        }
        studentsTable = new JTable(new DefaultTableModel(studentsData, studentsTableHeader)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentsTable.getTableHeader().setReorderingAllowed(false);
        studentsTable.setAutoCreateRowSorter(false);
        studentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentsTable.getSelectionModel().addListSelectionListener(e -> {
            if (studentsTable.getSelectedRow() != -1) {    // prevent calling the event twice (mouse down and mouse released)
                String[] student = studentsData[studentsTable.getSelectedRow()];
                for (int i = 0; i < studentText.length; i++)
                    studentText[i].setText(student[i + 1]);
                showInput("Student");
                studentText[0].setEditable(false);
                rightPanel.remove(studentsScrollPane);
                rightPanel.remove(addButton);
                backButton.setText("STUDENT TABLE");
                refreshScreen();
            }
        });
        TableColumnModel studentsColumnModel = studentsTable.getColumnModel();
        studentsColumnModel.getColumn(1).setPreferredWidth(70);
        studentsColumnModel.getColumn(2).setPreferredWidth(160);
        studentsColumnModel.getColumn(3).setPreferredWidth(70);
        studentsColumnModel.getColumn(4).setPreferredWidth(65);
        studentsColumnModel.getColumn(5).setPreferredWidth(77);
        studentsColumnModel.getColumn(6).setPreferredWidth(140);
        studentsColumnModel.getColumn(11).setPreferredWidth(70);
        studentsColumnModel.removeColumn(studentsColumnModel.getColumn(0)); // remove STAT
        studentsColumnModel.removeColumn(studentsColumnModel.getColumn(6)); // remove EMAIL
        studentsColumnModel.removeColumn(studentsColumnModel.getColumn(6)); // remove SCHOOL
        studentsColumnModel.removeColumn(studentsColumnModel.getColumn(6)); // remove FACULTY
        studentsColumnModel.removeColumn(studentsColumnModel.getColumn(6)); // remove MAJOR

        studentsScrollPane = new JScrollPane(studentsTable);
        studentsScrollPane.setBounds(5, 5, 591, 411);

        // EMPLOYEES
        Employee[] employees = Library.getEmployeeManagement().getEmployees();
        employeesData = new String[0][10];
        for (Employee employee : employees) {
            if (employee.getStatus().equals("enabled")) {
                employeesData = Arrays.copyOf(employeesData, employeesData.length + 1);
                employeesData[employeesData.length - 1] = employee.toString().split(", ");
            }
        }
        employeesTable = new JTable(new DefaultTableModel(employeesData, employeesTableHeader)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        employeesTable.getTableHeader().setReorderingAllowed(false);
        employeesTable.setAutoCreateRowSorter(false);
        employeesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeesTable.getSelectionModel().addListSelectionListener(e -> {
            if (employeesTable.getSelectedRow() != -1) {    // prevent calling the event twice (mouse down and mouse released)
                String[] employee = employeesData[employeesTable.getSelectedRow()];
                for (int i = 0; i < employeeText.length; i++)
                    employeeText[i].setText(employee[i + 1]);
                showInput("Employee");
                employeeText[0].setEditable(false);
                rightPanel.remove(employeesScrollPane);
                rightPanel.remove(addButton);
                backButton.setText("EMPLOYEE TABLE");
                refreshScreen();
            }
        });
        TableColumnModel employeesColumnModel = employeesTable.getColumnModel();
        employeesColumnModel.getColumn(1).setPreferredWidth(70);
        employeesColumnModel.getColumn(2).setPreferredWidth(160);
        employeesColumnModel.getColumn(3).setPreferredWidth(70);
        employeesColumnModel.getColumn(4).setPreferredWidth(65);
        employeesColumnModel.getColumn(5).setPreferredWidth(77);
        employeesColumnModel.getColumn(6).setPreferredWidth(140);
        employeesColumnModel.getColumn(8).setPreferredWidth(100);
        employeesColumnModel.removeColumn(employeesColumnModel.getColumn(0));   // remove STAT
        employeesColumnModel.removeColumn(employeesColumnModel.getColumn(6));   // remove EMAIL
        employeesColumnModel.removeColumn(employeesColumnModel.getColumn(7));   // remove START DATE
        employeesColumnModel.removeColumn(employeesColumnModel.getColumn(7));   // remove SALARY

        employeesScrollPane = new JScrollPane(employeesTable);
        employeesScrollPane.setBounds(5, 5, 591, 411);

        editButton = new JButton();
        editButton.setBounds(RIGHT_PANEL_WIDTH / 7, RIGHT_PANEL_HEIGHT - 2 * GAP, 170, 30);
        editButton.setBorder(border);
        editButton.setFocusable(false);
        editButton.addActionListener(this);
        removeButton = new JButton();
        removeButton.setBounds(RIGHT_PANEL_WIDTH * 4 / 7, RIGHT_PANEL_HEIGHT - 2 * GAP, 170, 30);
        removeButton.setBorder(border);
        removeButton.setFocusable(false);
        removeButton.addActionListener(this);

    }

    public void initFind() {
        findRadioButtons[0] = new JRadioButton("Find by ID");
        findRadioButtons[1] = new JRadioButton("Find by name");
        findButtonGroup = new ButtonGroup();
        for (int i = 0; i < 2; i++) {
            findRadioButtons[i].setBackground(Color.WHITE);
            findRadioButtons[i].setFont(font);
            findRadioButtons[i].setFocusable(false);
            findRadioButtons[i].addActionListener(this);
            findButtonGroup.add(findRadioButtons[i]);
        }

        findButton = new JButton();
        findButton.setBounds(RIGHT_PANEL_WIDTH / 3, RIGHT_PANEL_HEIGHT - 2 * GAP, 200, 30);
        findButton.setBorder(border);
        findButton.setFocusable(false);
        findButton.addActionListener(this);

        findLabel.setBounds(RIGHT_PANEL_WIDTH * 2 / 7, RIGHT_PANEL_HEIGHT / 5, 200, 25);
        findLabel.setFont(font);
        findText.setBounds(RIGHT_PANEL_WIDTH / 2, RIGHT_PANEL_HEIGHT / 5, 200, 25);
        findText.setFont(new Font("Consolas", Font.PLAIN, 16));
    }

    public void initBorrow() {
        nextButton = new JButton("NEXT");
        nextButton.setBounds(RIGHT_PANEL_WIDTH - 4 * GAP, RIGHT_PANEL_HEIGHT - 2 * GAP, 50, 30);
        nextButton.setBorder(border);
        nextButton.addActionListener(this);
        prevButton = new JButton("BACK");
        prevButton.setBounds(GAP, RIGHT_PANEL_HEIGHT - 2 * GAP, 50, 30);
        prevButton.setBorder(border);
        prevButton.addActionListener(this);
        steps = new JLabel("1/4");
        steps.setBounds(RIGHT_PANEL_WIDTH / 2, RIGHT_PANEL_HEIGHT - 2 * GAP, 50, 30);

        // BILLS
        Bill[] bills = BorrowAndReturn.getBills();
        billsData = new String[bills.length][7];
        for (int i = 0; i < billsData.length; i++)
            billsData[i] = bills[i].toString().split(", ");
        billsTable = new JTable(new DefaultTableModel(billsData, billsTableHeader)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        billsTable.getTableHeader().setReorderingAllowed(false);
        billsTable.setAutoCreateRowSorter(false);
        billsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        billsTable.getSelectionModel().addListSelectionListener(e -> {
            if (billsTable.getSelectedRow() != -1) {    // prevent calling the event twice (mouse down and mouse released)
                String[] bill = billsData[billsTable.getSelectedRow()];
                for (int i = 0; i < billText.length; i++)
                    billText[i].setText(bill[i]);
                showInput("Bill");
                billText[0].setEditable(false);
                rightPanel.remove(billsScrollPane);
                rightPanel.remove(addButton);
                rightPanel.remove(editButton);
                rightPanel.remove(removeButton);
                backButton.setText("BILL TABLE");
                refreshScreen();
            }
        });
        for (int i = 0; i < 7; i++) {
            billLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT / 15 + i * 33, 200, 25);
            billText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT / 15 + i * 33, 300, 25);
        }
        TableColumnModel billColumnModel = billsTable.getColumnModel();
        billColumnModel.getColumn(0).setPreferredWidth(70);
        billColumnModel.getColumn(1).setPreferredWidth(60);
        billColumnModel.getColumn(2).setPreferredWidth(150);
        billColumnModel.getColumn(3).setPreferredWidth(150);
        billColumnModel.getColumn(4).setPreferredWidth(80);
        billColumnModel.getColumn(5).setPreferredWidth(80);

        billsScrollPane = new JScrollPane(billsTable);
        billsScrollPane.setBounds(5, 5, 591, 411);

        // BOOKS
        addBookButton = new JButton("ADD");
        addBookButton.setBounds(RIGHT_PANEL_WIDTH * 9 / 10, RIGHT_PANEL_HEIGHT / 15 + 3 * 33, 50, 25);
        addBookButton.setBorder(border);
        addBookButton.addActionListener(this);
        returnBookButton = new JButton("\u2713");
        returnBookButton.setBounds(RIGHT_PANEL_WIDTH * 9 / 10, RIGHT_PANEL_HEIGHT / 15, 50, 25);
        returnBookButton.setBorder(border);
        returnBookButton.addActionListener(this);
        booksDataTemp = new String[0][8];
        booksTableTemp = new JTable(new DefaultTableModel(booksDataTemp, booksTableHeader)) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        booksTableTemp.getTableHeader().setReorderingAllowed(false);
        booksTableTemp.setAutoCreateRowSorter(false);
        booksTableTemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        booksTableTemp.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && booksTableTemp.getSelectedRow() != -1) {    // prevent calling the event twice (mouse down and mouse released)
                int row = booksTableTemp.getSelectedRow();
                Book book = Library.getBookManagement().findBook(Integer.parseInt(booksDataTemp[row][2]));
                book.setRemain(book.getRemain() + 1);
                DefaultTableModel model = (DefaultTableModel) booksTableTemp.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 2).equals(booksDataTemp[row][2])) {
                        booksDataTemp[i][4] = Integer.toString(book.getRemain());
                        model.setValueAt(booksDataTemp[i][4], i, 4);  // refresh remaining book
                    }
                }
                for (int i = row; i < booksTemp.length - 1; i++) {
                    booksTemp[i] = booksTemp[i + 1];
                    booksDataTemp[i] = booksDataTemp[i + 1];
                }
                booksTemp = Arrays.copyOf(booksTemp, booksTemp.length - 1);
                booksDataTemp = Arrays.copyOf(booksDataTemp, booksDataTemp.length - 1);
                model.removeRow(row);
                refreshScreen();
            }
        });
        TableColumnModel booksColumnModel = booksTableTemp.getColumnModel();
        booksColumnModel.getColumn(0).setPreferredWidth(37);
        booksColumnModel.getColumn(2).setPreferredWidth(40);
        booksColumnModel.getColumn(3).setPreferredWidth(160);
        booksColumnModel.getColumn(4).setPreferredWidth(37);
        booksColumnModel.getColumn(5).setPreferredWidth(65);
        booksColumnModel.getColumn(6).setPreferredWidth(87);
        booksColumnModel.getColumn(7).setPreferredWidth(160);
        booksColumnModel.removeColumn(booksColumnModel.getColumn(1));   // remove STAT

        booksScrollPaneTemp = new JScrollPane(booksTableTemp);
        booksScrollPaneTemp.setBounds(5, RIGHT_PANEL_HEIGHT / 2, 591, RIGHT_PANEL_HEIGHT / 4);
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
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(3, 1, 0, 10));
        for (JButton button : bookButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoStudentMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(3, 1, 0, 10));
        for (JButton button : studentButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoEmployeeMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(3, 1, 0, 10));
        for (JButton button : employeeButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void gotoBorrowAndReturnMenu() {
        canGoBack = true;
        leftPanel.setBounds(GAP, GAP + HEADER_HEIGHT + LEFT_PANEL_HEIGHT * 2 / 7, LEFT_PANEL_WIDTH, LEFT_PANEL_HEIGHT * 4 / 7);
        leftPanel.setLayout(new GridLayout(3, 1, 0, 10));
        for (JButton button : borrowAndReturnButtons) {
            button.setEnabled(true);
            leftPanel.add(button);
        }
    }

    public void showInputBook(String type) {
        for (int i = 0; i < 6; i++) {
            rightPanel.remove(eduBookLabels[i]);
            rightPanel.remove(refBookLabels[i]);
            rightPanel.remove(dictionaryLabels[i]);
            rightPanel.remove(bookText[i]);
        }
        if (type.equals("Education")) {
            for (int i = 0; i < 6; i++) {
                eduBookLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 250, 25);
                rightPanel.add(eduBookLabels[i]);
            }
        }
        if (type.equals("Reference")) {
            for (int i = 0; i < 6; i++) {
                refBookLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 250, 25);
                rightPanel.add(refBookLabels[i]);
            }
        }
        if (type.equals("Dictionary")) {
            for (int i = 0; i < 6; i++) {
                dictionaryLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT * 2 / 10 + i * 35, 250, 25);
                rightPanel.add(dictionaryLabels[i]);
            }
        }
        for (int i = 0; i < 6; i++) {
            bookText[i].setBounds(RIGHT_PANEL_WIDTH / 2, RIGHT_PANEL_HEIGHT / 5 + i * 35, 250, 25);
            rightPanel.add(bookText[i]);
        }
    }

    public void showInput(String type) {
        backButton.setText("BACK");
        if (type.equals("Book")) {
            for (int i = 0; i < 3; i++) {
                bookRadioButtons[i].setBounds(RIGHT_PANEL_WIDTH / 20 + i * 200, GAP, 150, 25);
                rightPanel.add(bookRadioButtons[i]);
            }
            if (bookRadioButtons[0].isSelected())
                showInputBook("Education");
            if (bookRadioButtons[1].isSelected())
                showInputBook("Reference");
            if (bookRadioButtons[2].isSelected())
                showInputBook("Dictionary");
            addButton.setText("ADD NEW BOOK");
            editButton.setText("EDIT BOOK");
            removeButton.setText("REMOVE BOOK");
            bookText[0].setEditable(true);
        }
        if (type.equals("Student")) {
            for (int i = 0; i < 11; i++) {
                studentLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT / 20 + i * 33, 200, 25);
                rightPanel.add(studentLabels[i]);
                studentText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT / 20 + i * 33, 300, 25);
                rightPanel.add(studentText[i]);
            }
            addButton.setText("ADD NEW STUDENT");
            editButton.setText("EDIT STUDENT");
            removeButton.setText("REMOVE STUDENT");
            studentText[0].setEditable(true);
        }
        if (type.equals("Employee")) {
            for (int i = 0; i < 10; i++) {
                employeeLabels[i].setBounds(RIGHT_PANEL_WIDTH / 10, RIGHT_PANEL_HEIGHT / 20 + i * 33, 200, 25);
                rightPanel.add(employeeLabels[i]);
                employeeText[i].setBounds(RIGHT_PANEL_WIDTH * 4 / 10, RIGHT_PANEL_HEIGHT / 20 + i * 33, 300, 25);
                rightPanel.add(employeeText[i]);
            }
            addButton.setText("ADD NEW EMPLOYEE");
            editButton.setText("EDIT EMPLOYEE");
            removeButton.setText("REMOVE EMPLOYEE");
            employeeText[0].setEditable(true);
        }
        if (type.equals("Bill")) {
            for (int i = 0; i < 7; i++) {
                billText[i].setEditable(false);
                rightPanel.add(billLabels[i]);
                rightPanel.add(billText[i]);
            }
            if (billText[0].getText().equals("Borrowed")) {
                rightPanel.add(returnBookButton);
            }
        }
        rightPanel.add(addButton);
        rightPanel.add(editButton);
        rightPanel.add(removeButton);
    }

    public void showOutput(String type, boolean clearSelection) {
        backButton.setText("BACK");
        if (type.equals("Book")) {
            if (clearSelection)
                booksTable.clearSelection();
            rightPanel.add(booksScrollPane);
        }
        if (type.equals("Student")) {
            if (clearSelection)
                studentsTable.clearSelection();
            rightPanel.add(studentsScrollPane);
        }
        if (type.equals("Employee")) {
            if (clearSelection)
                employeesTable.clearSelection();
            rightPanel.add(employeesScrollPane);
        }
    }

    public void showFoundItem(Object object) {
        if (object instanceof Book foundBook) {
            bookText[0].setText(Integer.toString(foundBook.getId()));
            bookText[1].setText(foundBook.getName());
            bookText[2].setText(Integer.toString(foundBook.getRemain()));
            bookText[3].setText(Double.toString(foundBook.getPrice()));
            bookText[4].setText(foundBook.getPublishDay().toString());
            if (object instanceof EducationBook eduBook) {
                bookRadioButtons[0].setSelected(true);
                bookText[5].setText(eduBook.getPublisher());
            }
            if (object instanceof ReferenceBook refBook) {
                bookRadioButtons[1].setSelected(true);
                bookText[5].setText(refBook.getAuthor());
            }
            if (object instanceof Dictionary dictionary) {
                bookRadioButtons[2].setSelected(true);
                bookText[5].setText(dictionary.getLanguage());
            }
            showInput("Book");
            for (int i = 0; i < 3; i++)
                rightPanel.remove(bookRadioButtons[i]);
            bookText[0].setEditable(false);
        }
        if (object instanceof Student foundStudent) {
            studentText[0].setText(Integer.toString(foundStudent.getId()));
            studentText[1].setText(foundStudent.getName());
            studentText[2].setText(foundStudent.getDob().toString());
            studentText[3].setText(foundStudent.getGender());
            studentText[4].setText(foundStudent.getPhone());
            studentText[5].setText(foundStudent.getAddress());
            studentText[6].setText(foundStudent.getEmail());
            studentText[8].setText(foundStudent.getFaculty());
            studentText[9].setText(foundStudent.getMajor());
            studentText[10].setText(foundStudent.getClassroom());
            showInput("Student");
            studentText[0].setEditable(false);
        }
        if (object instanceof Employee foundEmployee) {
            employeeText[0].setText(Integer.toString(foundEmployee.getId()));
            employeeText[1].setText(foundEmployee.getName());
            employeeText[2].setText(foundEmployee.getDob().toString());
            employeeText[3].setText(foundEmployee.getGender());
            employeeText[4].setText(foundEmployee.getPhone());
            employeeText[5].setText(foundEmployee.getAddress());
            employeeText[6].setText(foundEmployee.getEmail());
            employeeText[7].setText(foundEmployee.getRoll());
            employeeText[8].setText(foundEmployee.getStartDate().toString());
            employeeText[9].setText(Double.toString(foundEmployee.getSalary()));
            showInput("Employee");
            employeeText[0].setEditable(false);
        }
        rightPanel.remove(addButton);
        rightPanel.remove(findRadioButtons[0]);
        rightPanel.remove(findRadioButtons[1]);
        rightPanel.remove(findLabel);
        rightPanel.remove(findText);
        rightPanel.remove(findButton);
    }

    public void showFoundItems(Bill[] foundBill) {
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        while (model.getRowCount() != 0)
            model.removeRow(0);
        billsData = new String[foundBill.length][7];
        for (int i = 0; i < billsData.length; i++)
            billsData[i] = foundBill[i].toString().split(", ");
        for (Bill bill : foundBill)
            model.addRow(bill.toString().split(", "));
        rightPanel.add(billsScrollPane);
        rightPanel.remove(findRadioButtons[0]);
        rightPanel.remove(findRadioButtons[1]);
        rightPanel.remove(findLabel);
        rightPanel.remove(findText);
        rightPanel.remove(findButton);
    }

    public void showFind(String type) {
        backButton.setText("BACK");
        for (int i = 0; i < 2; i++) {
            findRadioButtons[i].setBounds(RIGHT_PANEL_WIDTH / 5 + i * 200, GAP, 150, 25);
            rightPanel.add(findRadioButtons[i]);
        }
        if (type.equals("Book")) {
            if (findRadioButtons[0].isSelected())
                findLabel.setText("Book ID");
            if (findRadioButtons[1].isSelected())
                findLabel.setText("Book name");
            findButton.setText("FIND BOOK");
        }
        if (type.equals("Student")) {
            if (findRadioButtons[0].isSelected())
                findLabel.setText("Student ID");
            if (findRadioButtons[1].isSelected())
                findLabel.setText("Student name");
            findButton.setText("FIND STUDENT");
        }
        if (type.equals("Employee")) {
            if (findRadioButtons[0].isSelected())
                findLabel.setText("Employee ID");
            if (findRadioButtons[1].isSelected())
                findLabel.setText("Employee name");
            findButton.setText("FIND EMPLOYEE");
        }
        if (type.equals("Bill")) {
            if (findRadioButtons[0].isSelected())
                findLabel.setText("Person ID");
            if (findRadioButtons[1].isSelected())
                findLabel.setText("Person name");
            findButton.setText("FIND BILL");
        }
        rightPanel.add(findRadioButtons[0]);
        rightPanel.add(findRadioButtons[1]);
        rightPanel.add(findLabel);
        rightPanel.add(findText);
        rightPanel.add(findButton);
    }

    public void showBorrow(int step) {
        backButton.setText("BACK");
        rightPanel.add(prevButton);
        rightPanel.add(nextButton);
        rightPanel.add(steps);
        rightPanel.remove(addBookButton);
        rightPanel.remove(booksScrollPaneTemp);
        for (int i = 1; i < 7; i++) {
            rightPanel.add(billLabels[i]);
            rightPanel.add(billText[i]);
            billText[i].setEditable(true);
        }
        if (step == 1) {
            for (int i = 2; i < 7; i++) {
                rightPanel.remove(billLabels[i]);
                rightPanel.remove(billText[i]);
            }
        }
        if (step == 2) {
            billText[1].setEditable(false);
            billText[2].setEditable(false);
            billText[3].setText("");
            for (int i = 4; i < 7; i++) {
                rightPanel.remove(billLabels[i]);
                rightPanel.remove(billText[i]);
            }
            rightPanel.add(addBookButton);
            rightPanel.add(booksScrollPaneTemp);
        }
        if (step == 3) {
            String books = booksTemp[0].getName();
            for (int i = 1; i < booksTemp.length; i++)
                books += " / " + booksTemp[i].getName();
            billText[1].setEditable(false);
            billText[2].setEditable(false);
            billText[3].setEditable(false);
            billText[3].setText(books);
            rightPanel.remove(billLabels[6]);
            rightPanel.remove(billText[6]);
        }
        if (step == 4) {
            for (int i = 1; i < 7; i++)
                billText[i].setEditable(false);
        }
    }

    public void showReturn() {
        backButton.setText("BACK");
        Bill[] bills = BorrowAndReturn.getBills();
        DefaultTableModel model = (DefaultTableModel) billsTable.getModel();
        while (model.getRowCount() != 0)
            model.removeRow(0);
        billsData = new String[0][7];
        billsTemp = new Bill[0];
        for (Bill bill : bills) {
            if (bill.getStatus().equals("Borrowed")) {
                billsData = Arrays.copyOf(billsData, billsTemp.length + 1);
                billsData[billsData.length - 1] = bill.toString().split(", ");
                billsTemp = Arrays.copyOf(billsTemp, billsTemp.length + 1);
                billsTemp[billsTemp.length - 1] = bill;
                model.addRow(bill.toString().split(", "));
            }
        }
        rightPanel.add(billsScrollPane);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showValidity(String message) {
        JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showConfirm(String message) {
        return JOptionPane.showConfirmDialog(frame, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
    }

    public Book validateBook() {
        int id;
        if (bookText[0].getText().isBlank()) {
            showError("Please input ID!");
            return null;
        }
        try {
            id = Integer.parseInt(bookText[0].getText());
            if (bookText[0].getText().length() != 4) {
                showError("ID must have 4 digits!");
                return null;
            } else if (id <= 0) {
                showError("ID cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("ID must be a number!");
            return null;
        }

        String name = bookText[1].getText();
        if (name.isBlank()) {
            showError("Please input name!");
            return null;
        }

        int remain;
        if (bookText[2].getText().isBlank()) {
            showError("Please input remain!");
            return null;
        }
        try {
            remain = Integer.parseInt(bookText[2].getText());
            if (remain <= 0) {
                showError("Remain cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("Remain must be a number!");
            return null;
        }

        double price;
        if (bookText[3].getText().isBlank()) {
            showError("Please input price!");
            return null;
        }
        try {
            price = Double.parseDouble(bookText[3].getText());
            if (price <= 0.0) {
                showError("Price cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("Price must be a number!");
            return null;
        }

        Day publishDay;
        if (bookText[4].getText().equals("  /  /    ")) {
            showError("Please input publish day!");
            return null;
        }
        try {
            publishDay = Day.parseDay(bookText[4].getText());
            if (!Day.isValidDay(publishDay)) {
                showError("Publish day is not valid!");
                return null;
            }
        } catch (Exception e) {
            showError("Publish day must contain numbers!");
            return null;
        }

        String note = bookText[5].getText();
        if (note.isBlank()) {
            if (bookRadioButtons[0].isSelected())
                showError("Please input publisher!");
            else if (bookRadioButtons[1].isSelected())
                showError("Please input author!");
            else
                showError("Please input language!");
            return null;
        }

        if (bookRadioButtons[0].isSelected())
            return new EducationBook("enabled", id, name, remain, price, publishDay, note);
        else if (bookRadioButtons[1].isSelected())
            return new ReferenceBook("enabled", id, name, remain, price, publishDay, note);
        else
            return new Dictionary("enabled", id, name, remain, price, publishDay, note);
    }

    public Student validateStudent() {
        int id;
        if (studentText[0].getText().isBlank()) {
            showError("Please input ID!");
            return null;
        }
        try {
            id = Integer.parseInt(studentText[0].getText());
            if (studentText[0].getText().length() != 8) {
                showError("ID must have 8 digits!");
                return null;
            } else if (id <= 0) {
                showError("ID cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("ID must be a number!");
            return null;
        }

        String name = studentText[1].getText();
        if (name.isBlank()) {
            showError("Please input name!");
            return null;
        }

        Day dob;
        if (studentText[2].getText().equals("  /  /    ")) {
            showError("Please input date of birth!");
            return null;
        }
        try {
            dob = Day.parseDay(studentText[2].getText());
            if (!Day.isValidDay(dob)) {
                showError("Date of birth is not valid");
                return null;
            }
        } catch (Exception e) {
            showError("Date of birth must contain numbers!");
            return null;
        }

        String gender = studentText[3].getText();
        if (gender.isBlank()) {
            showError("Please input gender!");
            return null;
        }
        if (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nữ")) {
            showError("Gender must be \"Nam\" or \"Nữ\"!");
            return null;
        }

        String phone = studentText[4].getText();
        if (phone.isBlank()) {
            showError("Please input phone number!");
            return null;
        }
        if (phone.length() != 10) {
            showError("Phone number must have 10 digits!");
            return null;
        }

        String address = studentText[5].getText();
        if (address.isBlank()) {
            showError("Please input address!");
            return null;
        }

        String email = studentText[6].getText();
        if (email.isBlank()) {
            showError("Please input email!");
            return null;
        }

        String faculty = studentText[8].getText();
        if (faculty.isBlank()) {
            showError("Please input faculty!");
            return null;
        }

        String major = studentText[9].getText();
        if (major.isBlank()) {
            showError("Please input major!");
            return null;
        }

        String classroom = studentText[10].getText();
        if (classroom.isBlank()) {
            showError("Please input classroom!");
            return null;
        }

        return new Student("enabled", id, name, dob, gender, phone, address, email, faculty, major, classroom);
    }

    public Employee validateEmployee() {
        int id;
        if (employeeText[0].getText().isBlank()) {
            showError("Please input ID!");
            return null;
        }
        try {
            id = Integer.parseInt(employeeText[0].getText());
            if (employeeText[0].getText().length() != 8) {
                showError("ID must have 8 digits!");
                return null;
            } else if (id <= 0) {
                showError("ID cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("ID must be a number!");
            return null;
        }

        String name = employeeText[1].getText();
        if (name.isBlank()) {
            showError("Please input name!");
            return null;
        }

        Day dob;
        if (employeeText[2].getText().equals("  /  /    ")) {
            showError("Please input date of birth!");
            return null;
        }
        try {
            dob = Day.parseDay(employeeText[2].getText());
            if (!Day.isValidDay(dob)) {
                showError("Date of birth is not valid!");
                return null;
            }
        } catch (Exception e) {
            showError("Date of birth must contain numbers!");
            return null;
        }

        String gender = employeeText[3].getText();
        if (gender.isBlank()) {
            showError("Please input gender!");
            return null;
        }
        if (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nữ")) {
            showError("Gender must be \"Nam\" or \"Nữ\"!");
            return null;
        }

        String phone = employeeText[4].getText();
        if (phone.isBlank()) {
            showError("Please input phone number!");
            return null;
        }
        if (phone.length() != 10) {
            showError("Phone number must have 10 digits!");
            return null;
        }

        String address = employeeText[5].getText();
        if (address.isBlank()) {
            showError("Please input address!");
            return null;
        }

        String email = employeeText[6].getText();
        if (email.isBlank()) {
            showError("Please input email!");
            return null;
        }

        String roll = employeeText[7].getText();
        if (email.isBlank()) {
            showError("Please input roll!");
            return null;
        }

        Day startDate;
        if (employeeText[8].getText().equals("  /  /    ")) {
            showError("Please input start date!");
            return null;
        }
        try {
            startDate = Day.parseDay(employeeText[8].getText());
            if (!Day.isValidDay(startDate)) {
                showError("Start date is not valid!");
                return null;
            }
        } catch (Exception e) {
            showError("Start date must contain numbers!");
            return null;
        }

        double salary;
        if (employeeText[9].getText().isBlank()) {
            showError("Please input salary!");
            return null;
        }
        try {
            salary = Double.parseDouble(employeeText[9].getText());
            if (salary <= 0.0) {
                showError("Salary cannot be a negative number!");
                return null;
            }
        } catch (Exception exception) {
            showError("Salary must be a number!");
            return null;
        }

        return new Employee("enabled", id, name, dob, gender, phone, address, email, roll, startDate, salary);
    }

    public void updateData(String method, Object object) {
        if (method.equals("Add")) {
            if (object instanceof Book addedBook) {
                Book[] books = Library.getBookManagement().getBooks();
                books = Arrays.copyOf(books, books.length + 1);
                books[books.length - 1] = addedBook;
                String[] book = addedBook.toString().split(", ");
                booksData = Arrays.copyOf(booksData, booksData.length + 1);
                booksData[booksData.length - 1] = book;
                DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
                model.addRow(book);
                Library.getBookManagement().setBooks(books);
                Library.getBookManagement().writeFile(Library.getBookManagement().getBooks(), true);
            }
            if (object instanceof Student addedStudent) {
                Student[] students = Library.getStudentManagement().getStudents();
                students = Arrays.copyOf(students, students.length + 1);
                students[students.length - 1] = addedStudent;
                String[] student = addedStudent.toString().split(", ");
                studentsData = Arrays.copyOf(studentsData, studentsData.length + 1);
                studentsData[studentsData.length - 1] = student;
                DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
                model.addRow(student);
                Library.getStudentManagement().setStudents(students);
                Library.getStudentManagement().writeFile(Library.getStudentManagement().getStudents(), true);
            }
            if (object instanceof Employee addedEmployee) {
                Employee[] employees = Library.getEmployeeManagement().getEmployees();
                employees = Arrays.copyOf(employees, employees.length + 1);
                employees[employees.length - 1] = addedEmployee;
                String[] employee = addedEmployee.toString().split(", ");
                employeesData = Arrays.copyOf(employeesData, employeesData.length + 1);
                employeesData[employeesData.length - 1] = employee;
                DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
                model.addRow(employee);
                Library.getEmployeeManagement().setEmployees(employees);
                Library.getEmployeeManagement().writeFile(Library.getEmployeeManagement().getEmployees(), true);
            }
        }
        if (method.equals("Edit")) {
            if (object instanceof Book editedBook) {
                Book book = Library.getBookManagement().findBook(editedBook.getId());
                book.setName(editedBook.getName());
                book.setRemain(editedBook.getRemain());
                book.setPrice(editedBook.getPrice());
                book.setPublishDay(editedBook.getPublishDay());
                if (book instanceof EducationBook)
                    ((EducationBook) book).setPublisher(((EducationBook) editedBook).getPublisher());
                else if (book instanceof ReferenceBook)
                    ((ReferenceBook) book).setAuthor(((ReferenceBook) editedBook).getAuthor());
                else
                    ((Dictionary) book).setLanguage(((Dictionary) editedBook).getLanguage());
                for (int i = 0; i < booksData.length; i++) {
                    if (booksData[i][2].equals(Integer.toString(editedBook.getId()))) {
                        booksData[i] = editedBook.toString().split(", ");
                        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
                        for (int j = 0; j < 8; j++)
                            model.setValueAt(booksData[i][j], i, j);
                        break;
                    }
                }
                Library.getBookManagement().writeFile(Library.getBookManagement().getBooks(), false);
            }
            if (object instanceof Student editedStudent) {
                Student student = Library.getStudentManagement().findStudent(editedStudent.getId());
                student.setName(editedStudent.getName());
                student.setDob(editedStudent.getDob());
                student.setGender(editedStudent.getGender());
                student.setPhone(editedStudent.getPhone());
                student.setAddress(editedStudent.getAddress());
                student.setEmail(editedStudent.getEmail());
                student.setFaculty(editedStudent.getFaculty());
                student.setMajor(editedStudent.getMajor());
                student.setClassroom(editedStudent.getClassroom());
                for (int i = 0; i < studentsData.length; i++) {
                    if (studentsData[i][1].equals(Integer.toString(editedStudent.getId()))) {
                        studentsData[i] = editedStudent.toString().split(", ");
                        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
                        for (int j = 0; j < 12; j++)
                            model.setValueAt(studentsData[i][j], i, j);
                        break;
                    }
                }
                Library.getStudentManagement().writeFile(Library.getStudentManagement().getStudents(), false);
            }
            if (object instanceof Employee editedEmployee) {
                Employee employee = Library.getEmployeeManagement().findEmployee(editedEmployee.getId());
                employee.setName(editedEmployee.getName());
                employee.setDob(editedEmployee.getDob());
                employee.setGender(editedEmployee.getGender());
                employee.setPhone(editedEmployee.getPhone());
                employee.setAddress(editedEmployee.getAddress());
                employee.setEmail(editedEmployee.getEmail());
                employee.setRoll(editedEmployee.getRoll());
                employee.setStartDate(editedEmployee.getStartDate());
                employee.setSalary(editedEmployee.getSalary());
                for (int i = 0; i < employeesData.length; i++) {
                    if (employeesData[i][1].equals(Integer.toString(editedEmployee.getId()))) {
                        employeesData[i] = editedEmployee.toString().split(", ");
                        DefaultTableModel model = (DefaultTableModel) employeesTable.getModel();
                        for (int j = 0; j < 11; j++)
                            model.setValueAt(employeesData[i][j], i, j);
                        break;
                    }
                }
                Library.getEmployeeManagement().writeFile(Library.getEmployeeManagement().getEmployees(), false);
            }
        }
        if (method.equals("Remove")) {
            if (object instanceof Book removedBook) {
                Book book = Library.getBookManagement().findBook(removedBook.getId());
                book.setStatus("disabled");
                for (int i = 0; i < booksData.length; i++) {
                    if (booksData[i][2].equals(Integer.toString(removedBook.getId()))) {
                        for (int j = i; j < booksData.length - 1; j++)
                            booksData[j] = booksData[j + 1];
                        booksData = Arrays.copyOf(booksData, booksData.length - 1);
                        DefaultTableModel model = (DefaultTableModel) booksTable.getModel();
                        model.removeRow(i);
                        break;
                    }
                }
                Library.getBookManagement().writeFile(Library.getBookManagement().getBooks(), false);
            }
            if (object instanceof Student removedStudent) {
                Student student = Library.getStudentManagement().findStudent(removedStudent.getId());
                student.setStatus("disabled");
                for (int i = 0; i < studentsData.length; i++) {
                    if (studentsData[i][1].equals(Integer.toString(removedStudent.getId()))) {
                        for (int j = i; j < studentsData.length - 1; j++)
                            studentsData[j] = studentsData[j + 1];
                        studentsData = Arrays.copyOf(studentsData, studentsData.length - 1);
                        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();
                        model.removeRow(i);
                        break;
                    }
                }
                Library.getStudentManagement().writeFile(Library.getStudentManagement().getStudents(), false);
            }
            if (object instanceof Employee removedEmployee) {
                Employee employee = Library.getEmployeeManagement().findEmployee(removedEmployee.getId());
                employee.setStatus("disabled");
                for (int i = 0; i < employeesData.length; i++) {
                    if (employeesData[i][1].equals(Integer.toString(removedEmployee.getId()))) {
                        for (int j = i; j < employeesData.length - 1; j++)
                            employeesData[j] = employeesData[j + 1];
                        employeesData = Arrays.copyOf(employeesData, employeesData.length - 1);
                        DefaultTableModel model = (DefaultTableModel) employeesTable.getModel();
                        model.removeRow(i);
                        break;
                    }
                }
                Library.getEmployeeManagement().writeFile(Library.getEmployeeManagement().getEmployees(), false);
            }
        }
    }

    public void updateData(Book book) {
        if (book.getRemain() == 0) {
            showError("Out of stock!");
            return;
        }
        book.setRemain(book.getRemain() - 1);
        booksTemp = Arrays.copyOf(booksTemp, booksTemp.length + 1);
        booksTemp[booksTemp.length - 1] = book;
        String[] data = book.toString().split(", ");
        booksDataTemp = Arrays.copyOf(booksDataTemp, booksDataTemp.length + 1);
        booksDataTemp[booksDataTemp.length - 1] = data;
        DefaultTableModel model = (DefaultTableModel) booksTableTemp.getModel();
        model.addRow(data);
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 2).equals(data[2])) {
                booksDataTemp[i][4] = Integer.toString(book.getRemain());
                model.setValueAt(data[4], i, 4);
            }
        }
    }

    public MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (Exception ignored) {}
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
            }
            if (backButton.getText().equals("BACK")) {
                backButton.setText("MAIN MENU");
                rightPanel.removeAll();
                Arrays.fill(enabledButton, -1);
            } else {
                rightPanel.removeAll();
                if (backButton.getText().equals("BOOK TABLE")) {
                    showOutput("Book", false);
                }
                if (backButton.getText().equals("STUDENT TABLE")) {
                    showOutput("Student", false);
                }
                if (backButton.getText().equals("EMPLOYEE TABLE")) {
                    showOutput("Employee", false);
                }
                if (backButton.getText().equals("BILL TABLE")) {
                    for (int i = 0; i < 7; i++) {
                        rightPanel.remove(billLabels[i]);
                        rightPanel.remove(billText[i]);
                    }
                    rightPanel.remove(returnBookButton);
                    rightPanel.add(billsScrollPane);
                    backButton.setText("BACK");
                }
            }
        }
        backButton.setVisible(canGoBack);

        // BOOK MANAGEMENT MENU
        if (e.getSource() == bookButtons[0]) { // Input Book
            bookRadioButtons[0].setSelected(true);  // Education book is selected by default
            showInput("Book");
            rightPanel.remove(editButton);
            rightPanel.remove(removeButton);
            for (JTextField text : bookText)
                text.setText("");
            enabledButton[0] = 0;
        }
        if (e.getSource() == bookButtons[1]) { // Output Book
            showOutput("Book", true);
            enabledButton[0] = 1;
        }
        if (e.getSource() == bookButtons[2]) { // Find Book
            findRadioButtons[0].setSelected(true);
            showFind("Book");
            findText.setText("");
            enabledButton[0] = 2;
        }

        // STUDENT MANAGEMENT MENU
        if (e.getSource() == studentButtons[0]) { // Input Student
            showInput("Student");
            rightPanel.remove(editButton);
            rightPanel.remove(removeButton);
            for (JTextField text : studentText)
                text.setText("");
            studentText[7].setText("Đại học Sài Gòn");
            enabledButton[1] = 0;
        }
        if (e.getSource() == studentButtons[1]) { // Output Student
            showOutput("Student", true);
            enabledButton[1] = 1;
        }
        if (e.getSource() == studentButtons[2]) { // Find Student
            findRadioButtons[0].setSelected(true);
            showFind("Student");
            findText.setText("");
            enabledButton[1] = 2;
        }

        // EMPLOYEE MANAGEMENT MENU
        if (e.getSource() == employeeButtons[0]) { // Input Employee
            showInput("Employee");
            rightPanel.remove(editButton);
            rightPanel.remove(removeButton);
            for (JTextField text : studentText)
                text.setText("");
            enabledButton[2] = 0;
        }
        if (e.getSource() == employeeButtons[1]) { // Output Employee
            showOutput("Employee", true);
            enabledButton[2] = 1;
        }
        if (e.getSource() == employeeButtons[2]) { // Find Employee
            findRadioButtons[0].setSelected(true);
            showFind("Employee");
            findText.setText("");
            enabledButton[2] = 2;
        }

        // BORROW AND RETURN MENU
        if (e.getSource() == borrowAndReturnButtons[0]) { // Borrow Book
            booksTemp = new Book[0];
            booksDataTemp = new String[0][8];
            DefaultTableModel model = (DefaultTableModel) booksTableTemp.getModel();
            while (model.getRowCount() != 0)
                model.removeRow(0);
            steps.setText("1/4");
            for (int i = 0; i < 7; i++)
                billText[i].setText("");
            showBorrow(1);
            enabledButton[3] = 0;
        }
        if (e.getSource() == borrowAndReturnButtons[1]) { // Return Book
            billsTable.clearSelection();
            rightPanel.removeAll();
            showReturn();
            enabledButton[3] = 1;
        }
        if (e.getSource() == borrowAndReturnButtons[2]) { // Find Bill
            billsTable.clearSelection();
            rightPanel.removeAll();
            findRadioButtons[0].setSelected(true);
            showFind("Bill");
            findText.setText("");
            enabledButton[3] = 2;
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

        // FIND RADIO BUTTONS
        if (e.getSource() == findRadioButtons[0]) {
            if (findButton.getText().equals("FIND BOOK"))
                findLabel.setText("Book ID");
            if (findButton.getText().equals("FIND STUDENT"))
                findLabel.setText("Student ID");
            if (findButton.getText().equals("FIND EMPLOYEE"))
                findLabel.setText("Employee ID");
            if (findButton.getText().equals("FIND BILL"))
                findLabel.setText("Person ID");
        }
        if (e.getSource() == findRadioButtons[1]) {
            if (findButton.getText().equals("FIND BOOK"))
                findLabel.setText("Book name");
            if (findButton.getText().equals("FIND STUDENT"))
                findLabel.setText("Student name");
            if (findButton.getText().equals("FIND EMPLOYEE"))
                findLabel.setText("Employee name");
            if (findButton.getText().equals("FIND BILL"))
                findLabel.setText("Person name");
        }

        // CHOOSE AN ITEM FROM TABLE
        if (e.getSource() == addButton) {
            if (addButton.getText().equals("ADD NEW BOOK")) {
                Book newBook = validateBook();
                if (newBook != null) {
                    if (Library.getBookManagement().idExists(newBook.getId())) {
                        showError("ID has already existed!");
                        return;
                    }
                    updateData("Add", newBook);
                    showValidity("New book has been added.");
                }
            }
            if (addButton.getText().equals("ADD NEW STUDENT")) {
                Student newStudent = validateStudent();
                if (newStudent != null) {
                    if (Library.getStudentManagement().idExists(newStudent.getId())) {
                        showError("ID has already existed!");
                        return;
                    }
                    updateData("Add", newStudent);
                    showValidity("New student has been added.");
                }
            }
            if (addButton.getText().equals("ADD NEW EMPLOYEE")) {
                Employee newEmployee = validateEmployee();
                if (newEmployee != null) {
                    if (Library.getEmployeeManagement().idExists(newEmployee.getId())) {
                        showError("ID has already existed!");
                        return;
                    }
                    updateData("Add", newEmployee);
                    showValidity("New employee has been added.");
                }
            }
        }
        if (e.getSource() == editButton) {
            if (editButton.getText().equals("EDIT BOOK")) {
                Book editingBook = validateBook();
                if (editingBook != null && showConfirm("This action cannot be undone.\nAre you sure you want to edit this book?") == 0) {
                    updateData("Edit", editingBook);
                    showValidity("Book has been edited.");
                    backButton.doClick();
                }
            }
            if (editButton.getText().equals("EDIT STUDENT")) {
                Student editingStudent = validateStudent();
                if (editingStudent != null && showConfirm("This action cannot be undone.\nAre you sure you want to edit this student?") == 0) {
                    updateData("Edit", editingStudent);
                    showValidity("Student has been edited.");
                    backButton.doClick();
                }
            }
            if (editButton.getText().equals("EDIT EMPLOYEE")) {
                Employee editingEmployee = validateEmployee();
                if (editingEmployee != null && showConfirm("This action cannot be undone.\nAre you sure you want to edit this employee?") == 0) {
                    updateData("Edit", editingEmployee);
                    showValidity("Employee has been edited.");
                    backButton.doClick();
                }
            }
        }
        if (e.getSource() == removeButton) {
            if (removeButton.getText().equals("REMOVE BOOK")) {
                Book removingBook = validateBook();
                if (removingBook != null && showConfirm("This action cannot be undone.\nAre you sure you want to remove this book?") == 0) {
                    updateData("Remove", removingBook);
                    showValidity("Book has been removed.");
                    backButton.doClick();
                }
            }
            if (removeButton.getText().equals("REMOVE STUDENT")) {
                Student removingStudent = validateStudent();
                if (removingStudent != null && showConfirm("This action cannot be undone.\nAre you sure you want to remove this student?") == 0) {
                    updateData("Remove", removingStudent);
                    showValidity("Student has been removed.");
                    backButton.doClick();
                }
            }
            if (removeButton.getText().equals("REMOVE EMPLOYEE")) {
                Employee removingEmployee = validateEmployee();
                if (removingEmployee != null && showConfirm("This action cannot be undone.\nAre you sure you want to remove this employee?") == 0) {
                    updateData("Remove", removingEmployee);
                    showValidity("Employee has been removed.");
                    backButton.doClick();
                }
            }
        }
        if (e.getSource() == findButton) {
            int id = -1;
            String name = "";
            billsTemp = new Bill[0];
            // VALIDATE TEXT FIELD
            if (findRadioButtons[0].isSelected()) {
                int digits;
                if (findButton.getText().contains("BOOK"))
                    digits = 4;
                else
                    digits = 8;
                if (findText.getText().isBlank()) {
                    showError("Please input ID!");
                    return;
                }
                try {
                    id = Integer.parseInt(findText.getText());
                    if (findText.getText().length() != digits) {
                        showError("ID must have " + digits + " digits!");
                        return;
                    } else if (id <= 0) {
                        showError("ID cannot be a negative number!");
                        return;
                    }
                } catch (Exception exception) {
                    showError("ID must be a number!");
                    return;
                }
            }
            if (findRadioButtons[1].isSelected()) {
                name = findText.getText();
                if (name.isBlank()) {
                    showError("Please input name!");
                    return;
                }
            }
            // FIND OBJECT
            if (findButton.getText().equals("FIND BOOK")) {
                Book findingBook;
                if (id != -1)
                    findingBook = Library.getBookManagement().findBook(id);
                else
                    findingBook = Library.getBookManagement().findBook(name);
                if (findingBook == null) {
                    showError("Book not found!");
                    return;
                }
                showFoundItem(findingBook);
            }
            if (findButton.getText().equals("FIND STUDENT")) {
                Student findingStudent;
                if (id != -1)
                    findingStudent = Library.getStudentManagement().findStudent(id);
                else
                    findingStudent = Library.getStudentManagement().findStudent(name);
                if (findingStudent == null) {
                    showError("Student not found!");
                    return;
                }
                showFoundItem(findingStudent);
            }
            if (findButton.getText().equals("FIND EMPLOYEE")) {
                Employee findingEmployee;
                if (id != -1)
                    findingEmployee = Library.getEmployeeManagement().findEmployee(id);
                else
                    findingEmployee = Library.getEmployeeManagement().findEmployee(name);
                if (findingEmployee == null) {
                    showError("Employee not found!");
                    return;
                }
                showFoundItem(findingEmployee);
            }
            if (findButton.getText().equals("FIND BILL")) {
                Bill[] bills = BorrowAndReturn.getBills();
                Person findingPerson;
                if (id != -1) {
                    findingPerson = Library.getStudentManagement().findStudent(id);
                    if (findingPerson == null) {
                        findingPerson = Library.getEmployeeManagement().findEmployee(id);
                        if (findingPerson == null) {
                            showError("Person not found!");
                            return;
                        }
                    }
                } else {
                    findingPerson = Library.getStudentManagement().findStudent(name);
                    if (findingPerson == null) {
                        findingPerson = Library.getEmployeeManagement().findEmployee(name);
                        if (findingPerson == null) {
                            showError("Person not found!");
                            return;
                        }
                    }
                }
                for (Bill bill : bills) {
                    if (bill.getPerson().getId() == findingPerson.getId()) {
                        billsTemp = Arrays.copyOf(billsTemp, billsTemp.length + 1);
                        billsTemp[billsTemp.length - 1] = bill;
                    }
                }
                showFoundItems(billsTemp);
            }
        }

        // BORROW BOOKS
        /*
        TODO
         */
        if (e.getSource() == nextButton) {
            if (steps.getText().equals("1/4")) {
                int id;
                if (billText[1].getText().isBlank()) {
                    showError("Please input person ID!");
                    return;
                }
                try {
                    id = Integer.parseInt(billText[1].getText());
                    if (billText[1].getText().length() != 8) {
                        showError("Person ID must have 8 digits!");
                        return;
                    } else if (id <= 0) {
                        showError("Person ID cannot be a negative number!");
                        return;
                    }
                } catch (Exception exception) {
                    showError("Person ID must be a number!");
                    return;
                }
                Person person = Library.getStudentManagement().findStudent(id);
                if (person == null) {
                    person = Library.getEmployeeManagement().findEmployee(id);
                    if (person == null) {
                        showError("Person not found!");
                        return;
                    }
                }
                billText[2].setText(person.getName());
                steps.setText("2/4");
                showBorrow(2);
            } else if (steps.getText().equals("2/4")) {
                if (booksTemp.length == 0) {
                    showError("Please add a book!");
                    return;
                }
                steps.setText("3/4");
                showBorrow(3);
            } else if (steps.getText().equals("3/4")) {
                if (billText[4].getText().equals("  /  /    ")) {
                    showError("Please input borrow day!");
                    return;
                }
                Day borrowDay;
                try {
                    borrowDay = Day.parseDay(billText[4].getText());
                    if (!Day.isValidDay(borrowDay)) {
                        showError("Borrow day is not valid!");
                        return;
                    }
                } catch (Exception exception) {
                    showError("Borrow day must contain numbers!");
                    return;
                }
                if (billText[5].getText().equals("  /  /    ")) {
                    showError("Please input return day!");
                    return;
                }
                Day returnDay;
                try {
                    returnDay = Day.parseDay(billText[5].getText());
                    if (!Day.isValidDay(returnDay)) {
                        showError("Return day is not valid!");
                        return;
                    }
                } catch (Exception exception) {
                    showError("Return day must contain numbers!");
                    return;
                }
                int id = Integer.parseInt(billText[1].getText());
                Person person = Library.getStudentManagement().findStudent(id);
                if (person == null)
                    person = Library.getEmployeeManagement().findEmployee(id);
                double price = person.calculatePrice(borrowDay, returnDay, booksTemp.length);
                billText[6].setText(Double.toString(price));
                steps.setText("4/4");
                nextButton.setText("DONE");
                showBorrow(4);
            } else if (steps.getText().equals("4/4")) {
                if (showConfirm("This action cannot be undone.\nAre you sure you want to print this bill?") == 0) {
                    int id = Integer.parseInt(billText[1].getText());
                    Person person = Library.getStudentManagement().findStudent(id);
                    if (person == null)
                        person = Library.getEmployeeManagement().findEmployee(id);
                    Day borrowDay = null;
                    Day returnDay = null;
                    try {
                        borrowDay = Day.parseDay(billText[4].getText());
                        returnDay = Day.parseDay(billText[5].getText());
                    } catch (Exception ignored) {}
                    double price = person.calculatePrice(borrowDay, returnDay, booksTemp.length);
                    Bill bill = new Bill("Borrowed", person, booksTemp, borrowDay, returnDay, price);
                    Bill[] bills = BorrowAndReturn.getBills();
                    bills = Arrays.copyOf(bills, bills.length + 1);
                    bills[bills.length - 1] = bill;
                    BorrowAndReturn.setBills(bills);
                    rightPanel.remove(prevButton);
                    rightPanel.remove(nextButton);

                    DefaultTableModel booksModel = (DefaultTableModel) booksTable.getModel();
                    for (Book book : booksTemp) {
                        for (int j = 0; j < booksModel.getRowCount(); j++) {
                            if (booksData[j][2].equals(Integer.toString(book.getId()))) {
                                booksData[j][4] = Integer.toString(book.getRemain());
                                booksModel.setValueAt(Integer.toString(book.getRemain()), j, 4);
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (e.getSource() == prevButton) {
            if (steps.getText().equals("2/4")) {
                steps.setText("1/4");
                showBorrow(1);
            } else if (steps.getText().equals("3/4")) {
                steps.setText("2/4");
                showBorrow(2);
            } else if (steps.getText().equals("4/4")) {
                steps.setText("3/4");
                nextButton.setText("NEXT");
                showBorrow(3);
            }
        }
        if (e.getSource() == addBookButton) {
            int id;
            if (billText[3].getText().isBlank()) {
                showError("Please input book ID!");
                return;
            }
            try {
                id = Integer.parseInt(billText[3].getText());
                if (billText[3].getText().length() != 4) {
                    showError("Book ID must have 4 digits!");
                    return;
                } else if (id <= 0) {
                    showError("Book ID cannot be a negative number!");
                    return;
                }
            } catch (Exception exception) {
                showError("Book ID must be a number!");
                return;
            }
            Book book = Library.getBookManagement().findBook(id);
            billText[3].setText("");
            if (book == null) {
                showError("Book not found!");
                return;
            }
            updateData(book);
        }
        if (e.getSource() == returnBookButton) {
            if (showConfirm("This action cannot be undone.\n Are you sure you want to return all the books?") == 0) {
                rightPanel.remove(returnBookButton);
                int row = billsTable.getSelectedRow();
                Book[] books = billsTemp[row].getBooks();
                for (Book book : books)
                    book.setRemain(book.getRemain() + 1);
                billText[0].setText("Returned");
                billsData[row][0] = "Returned";
                billsTable.setValueAt("Returned", row, 0);
                DefaultTableModel bookModel = (DefaultTableModel) booksTable.getModel();
                String[] strBook = billText[3].getText().split(" / ");
                for (String s : strBook) {
                    Book temp = Library.getBookManagement().findBook(s);
                    for (int j = 0; j < bookModel.getRowCount(); j++) {
                        if (bookModel.getValueAt(j, 2).equals(Integer.toString(temp.getId()))) {
                            booksData[j][4] = Integer.toString(temp.getRemain());
                            bookModel.setValueAt(Integer.toString(temp.getRemain()), j, 4);
                        }
                    }
                }
                Bill[] bills = BorrowAndReturn.getBills();
                for (Bill bill : bills)
                    if (bill.getPerson().getId() == billsTemp[billsTable.getSelectedRow()].getPerson().getId())
                        bill.hasReturned();
                Library.getBorrowAndReturn().writeFile(bills, false);
            }
        }

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
            Arrays.fill(enabledButton, -1);
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
