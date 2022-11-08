package com.library.main;

import com.library.management.*;
import com.library.ui.UI;

public class Library {
    private BookManagement bookMan;
    private EmployeeManagement employeeMan;
    private StudentManagement studentMan;

    public Library() {

    }

    public BookManagement getBookMan() {
        return bookMan;
    }

    public EmployeeManagement getEmployeeMan() {
        return employeeMan;
    }

    public StudentManagement getStudentMan() {
        return studentMan;
    }

    public static void main(String[] args) {
        UI ui = new UI();
        Library library = new Library();
        System.out.println("Hello World!");
    }
}
