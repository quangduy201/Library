package com.library.main;

import com.library.management.*;
import com.library.ui.UI;

public class Library {
    private static BookManagement bookMan;
    private static EmployeeManagement employeeMan;
    private static StudentManagement studentMan;
    private static BorrowAndReturn borrowAndReturn;
    private static UI ui;

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
        ui = new UI();
    }
}
