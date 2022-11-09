package com.library.management;

import com.library.component.*;
import com.library.main.Library;
import com.library.util.Day;

public class BorrowAndReturn {
    private static Bill bills[];

    public BorrowAndReturn() {
    }

    public void borrowBook(String category, String person) {
        if (category.equalsIgnoreCase("Education")) {
            EducationBook eduBook = new EducationBook();
            System.out.println("Enter information Education Book:");
            eduBook.input();
            BookManagement bookManagement = Library.getBookManagement();
            EducationBook temp = bookManagement.findEducationBook(eduBook.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
        else if (category.equalsIgnoreCase("Reference")) {
            ReferenceBook refBook = new ReferenceBook();
            System.out.println("Enter information Reference Book:");
            refBook.input();
            BookManagement bookManagement = Library.getBookManagement();
            ReferenceBook temp = bookManagement.findReferenceBook(refBook.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
        else if (category.equalsIgnoreCase("Dictionary")) {
            Dictionary dictionary = new Dictionary();
            System.out.println("Enter information Dictionary:");
            dictionary.input();
            BookManagement bookManagement = Library.getBookManagement();
            Dictionary temp = bookManagement.findDictionary(dictionary.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
        if (person.equalsIgnoreCase("Student")) {
            Student student = new Student();
            System.out.println("Enter information student:");
            student.input();
            StudentManagement studentManagement = Library.getStudentManagement();
            Student temp = studentManagement.findStudent(student.getId());
            if (temp == null) {
                System.out.println("Student not found!");
            }
            //em chua hieu se lam gi them phan chon nguoi muon nen se update sau
        }
        else if (person.equalsIgnoreCase("Employee")) {
            Employee employee = new Employee();
            System.out.println("Enter information employee:");
            employee.input();
            EmployeeManagement employeeManagement = Library.getEmployeeManagement();
            Employee temp = employeeManagement.findEmployee(employee.getId());
            if (temp == null) {
                System.out.println("Employee not found!");
            }
            //em chua hieu se lam gi them phan chon nguoi muon nen se update sau
        }
    }

    public void returnBook(String category) {
        if (category.equalsIgnoreCase("Education")) {
            EducationBook eduBook = new EducationBook();
            System.out.println("Enter information Education Book:");
            eduBook.input();
            BookManagement bookMan = new BookManagement();
            EducationBook temp = bookMan.findEducationBook(eduBook.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
        if (category.equalsIgnoreCase("Reference")) {
            ReferenceBook refBook = new ReferenceBook();
            System.out.println("Enter information ReferenceBook:");
            refBook.input();
            BookManagement bookMan = new BookManagement();
            ReferenceBook temp = bookMan.findReferenceBook(refBook.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
        if (category.equalsIgnoreCase("Dictionmary")) {
            Dictionary dictionary = new Dictionary();
            System.out.println("Enter information Dictionary:");
            dictionary.input();
            BookManagement bookMan = new BookManagement();
            Dictionary temp = bookMan.findDictionary(dictionary.getId());
            if (temp == null) {
                System.out.println("Book not found!");
                return;
            }
        }
    }
    public Bill findBill(int studentID) {
        for (Bill b : bills)
            if (b.getStudent().getId() == studentID)
                return b;
        return null;
    }

    public Bill[] findBill(Day low, Day high) {
        Bill listBill[];
        int count = 0, j = 0;
        int time = Day.calculateDays(low, high);
        /*
        * nếu khoảng cách ngày mượn đên ngày low <= khoảng cách low đến high thì nhận
        */
        for (int i = 0; i < bills.length; i++) {
            if (time >= Day.calculateDays(low, bills[i].getBorrowDay())) {
                count++;
            }
        }
        listBill = new Bill[count];
        for (int i = 0; i < bills.length; i++) {
            if (time >= Day.calculateDays(low, bills[i].getBorrowDay())) {
                listBill[j] =  new Bill();
                listBill[j++] = bills[i];
            }
        }
        return listBill;
    }

    public void statistic(){

    }
}
