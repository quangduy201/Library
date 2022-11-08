package com.library.main;

import com.library.management.BookManagement;
import com.library.management.EmployeeManagement;
import com.library.management.StudentManagement;

public class Library {
    private BookManagement bookMan;
    private EmployeeManagement employeeMan;
    private StudentManagement studentMan;

    public static void main(String[] args) {
        Library library = new Library();
        System.out.println("Hello World!");
    }
}
