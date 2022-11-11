package com.library.main;

import com.library.component.EducationBook;
import com.library.management.*;
import com.library.ui.UI;

import java.util.Scanner;

public class Library {
    private static BookManagement bookMan;
    private static EmployeeManagement employeeMan;
    private static StudentManagement studentMan;
    private static BorrowAndReturn borrowAndReturn;
    private static UI ui;
    private static String line =    "------------------------------------------------------------------------------------------------------------------------";
    private static String enterChoice = "Enter your choice: ";
    private static String mainMenu = """
                        SGU LIBRARY
            1. Book Management
            2. Student Management
            3. Employee Management
            4. Borrow and Return
            5. Exit
            """;
    private static String bookManagementMenu = """
                        BOOK MANAGEMENT
            1. Input Book
            2. Output Book
            3. Add Book
            4. Edit Book
            5. Remove Book
            6. Find Book
            7. Statistic Book
            8. Back
            """;
    private static String studentManagementMenu = """
                        STUDENT MANAGEMENT
            1. Input Student
            2. Output Student
            3. Add Student
            4. Edit Student
            5. Remove Student
            6. Find Student
            7. Statistic Student
            8. Back
            """;
    private static String employeeManagementMenu = """
                        EMPLOYEE MANAGEMENT
            1. Input Employee
            2. Output Employee
            3. Add Employee
            4. Edit Employee
            5. Remove Employee
            6. Find Employee
            7. Statistic Employee
            8. Back
            """;
    private static String borrowAndReturnMenu = """
                        BORROW AND RETURN
            1. Borrow Book
            2. Return Book
            3. Find Bill
            4. Statistic Bill
            5. Back
            """;
    private static String bookMenu = """
            1. Education Book
            2. Reference Book
            3. Dictionary
            4. Back
            """;
    private static String personMenu = """
            1. Student
            2. Employee
            3. Back
            """;
    public Library() {

    }

    public static BookManagement getBookManagement() {
        return bookMan;
    }

    public static EmployeeManagement getEmployeeManagement() {
        return employeeMan;
    }

    public static StudentManagement getStudentManagement() {
        return studentMan;
    }

    public static BorrowAndReturn getBorrowAndReturn() {
        return borrowAndReturn;
    }

    public static UI getUi() {
        return ui;
    }

    public static void main(String[] args) {
        bookMan = new BookManagement();
        studentMan = new StudentManagement();
        employeeMan = new EmployeeManagement();
        borrowAndReturn = new BorrowAndReturn();
//        ui = new UI();
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean hasError;
        while (true) {
            System.out.println(line);
            System.out.println(mainMenu);
            System.out.print(enterChoice);
            choice = sc.nextLine();

            // BOOK MANAGEMENT
            if (choice.equals("1")) {
                while (true) {
                    System.out.println(line);
                    System.out.println(bookManagementMenu);
                    System.out.print(enterChoice);
                    choice = sc.nextLine();

                    if (choice.equals("1")) {   // Input Book
                        System.out.println(line);
                        System.out.println("\t\t\tINPUT BOOK");
                        bookMan.input();
                    }
                    if (choice.equals("2")) {   // Output Book
                        System.out.println(line);
                        System.out.println("\t\t\tOUTPUT BOOK");
                        bookMan.output();
                    }
                    if (choice.equals("3")) {   // Add Book
                        System.out.println(line);
                        System.out.println("\t\t\tADD BOOK");
                        bookMan.add();
                    }
                    if (choice.equals("4")) {   // Edit Book
                        System.out.println(line);
                        System.out.println("\t\t\tEDIT BOOK");
                        bookMan.edit();
                    }
                    if (choice.equals("5")) {   // Remove Book
                        System.out.println(line);
                        System.out.println("\t\t\tREMOVE BOOK");
                        bookMan.remove();
                    }
                    if (choice.equals("6")) {   // Find Book
                        System.out.println(line);
                        System.out.println("\t\t\tFIND BOOK");
                        bookMan.find();
                    }
                    if (choice.equals("7")) {   // Statistic Book
                        System.out.println(line);
                        System.out.println("\t\t\tSTATISTIC BOOK");
                        bookMan.statistic();
                    }
                    if (choice.equals("8")) {   // Back
                        choice = "0";
                        break;
                    }
                }
            }

            // STUDENT MANAGEMENT
            if (choice.equals("2")) {
                while (true) {
                    System.out.println(line);
                    System.out.println(studentManagementMenu);
                    System.out.print(enterChoice);
                    choice = sc.nextLine();

                    if (choice.equals("1")) {   // Input Student
                        System.out.println(line);
                        System.out.println("\t\t\tINPUT STUDENT");
                        studentMan.input();
                    }
                    if (choice.equals("2")) {   // Output Student
                        System.out.println(line);
                        System.out.println("\t\t\tOUTPUT STUDENT");
                        studentMan.output();
                    }
                    if (choice.equals("3")) {   // Add Student
                        System.out.println(line);
                        System.out.println("\t\t\tADD STUDENT");
                        studentMan.add();
                    }
                    if (choice.equals("4")) {   // Edit Student
                        System.out.println(line);
                        System.out.println("\t\t\tEDIT STUDENT");
                        studentMan.edit();
                    }
                    if (choice.equals("5")) {   // Remove Student
                        System.out.println(line);
                        System.out.println("\t\t\tREMOVE STUDENT");
                        studentMan.remove();

                    }
                    if (choice.equals("6")) {   // Find Student
                        System.out.println(line);
                        System.out.println("\t\t\tFIND STUDENT");
//                        studentMan.find();
                    }
                    if (choice.equals("7")) {   // Statistic Student
                        System.out.println(line);
                        System.out.println("\t\t\tSTATISTIC STUDENT");
                        studentMan.statistic();
                    }
                    if (choice.equals("8")) {   // Back
                        choice = "0";
                        break;
                    }
                }
            }

            // EMPLOYEE MANAGEMENT
            if (choice.equals("3")) {
                while (true) {
                    System.out.println(line);
                    System.out.println(employeeManagementMenu);
                    System.out.print(enterChoice);
                    choice = sc.nextLine();

                    if (choice.equals("1")) {   // Input Employee
                        System.out.println(line);
                        System.out.println("\t\t\tINPUT EMPLOYEE");
                        employeeMan.input();
                    }
                    if (choice.equals("2")) {   // Output Employee
                        System.out.println(line);
                        System.out.println("\t\t\tOUTPUT EMPLOYEE");
                        employeeMan.output();
                    }
                    if (choice.equals("3")) {   // Add Employee
                        System.out.println(line);
                        System.out.println("\t\t\tADD EMPLOYEE");
                        employeeMan.add();
                    }
                    if (choice.equals("4")) {   // Edit Employee
                        System.out.println(line);
                        System.out.println("\t\t\tEDIT EMPLOYEE");
                        employeeMan.edit();
                    }
                    if (choice.equals("5")) {   // Remove Employee
                        System.out.println(line);
                        System.out.println("\t\t\tREMOVE EMPLOYEE");
                        employeeMan.remove();
                    }
                    if (choice.equals("6")) {   // Find Employee
                        System.out.println(line);
                        System.out.println("\t\t\tFIND EMPLOYEE");
//                        employeeMan.find();
                    }
                    if (choice.equals("7")) {   // Statistic Employee
                        System.out.println(line);
                        System.out.println("\t\t\tSTATISTIC EMPLOYEE");
                        employeeMan.statistic();
                    }
                    if (choice.equals("8")) {   // Back
                        choice = "0";
                        break;
                    }
                }
            }

            // BORROW AND RETURN
            if (choice.equals("4")) {
                while (true) {
                    System.out.println(line);
                    System.out.println(borrowAndReturnMenu);
                    System.out.print(enterChoice);
                    choice = sc.nextLine();

                    if (choice.equals("1")) {   // Borrow Book
                        System.out.println(line);
                        System.out.println("\t\t\tBORROW BOOK");
//                        borrowAndReturn.borrowBook();
                    }
                    if (choice.equals("2")) {   // Return Book
                        System.out.println(line);
                        System.out.println("\t\t\tRETURN BOOK");
//                        borrowAndReturn.returnBook();
                    }
                    if (choice.equals("3")) {   // Find Bill
                        System.out.println(line);
                        System.out.println("\t\t\tFIND BILL");
//                        borrowAndReturn.find();
                    }
                    if (choice.equals("4")) {   // Statistic Bill
                        System.out.println(line);
                        System.out.println("\t\t\tSTATISTIC BILL");
                        borrowAndReturn.statistic();
                    }
                    if (choice.equals("5")) {   // Back
                        choice = "0";
                        break;
                    }
                }
            }

            // EXIT
            if (choice.equals("5")) {
                break;
            }
        }
        System.out.println("See you later");
    }
}
