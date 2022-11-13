package com.library.management;

import com.library.component.*;
import com.library.main.Library;
import com.library.util.Day;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class BorrowAndReturn implements File {
    private static Bill[] bills;

    public BorrowAndReturn() {
        bills = new Bill[0];
    }

    public void borrowBook() {
        Scanner sc = new Scanner(System.in);
        boolean hasError;

        // INPUT PERSON
        Person person;
        do {
            System.out.print("""
                    1. Student
                    2. Employee
                    """);
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                int id = Person.inputId("Enter student ID: ");
                person = Library.getStudentManagement().findStudent(id);
                if (person == null)
                    System.out.println("Student not found!");
                else
                    break;
            }
            if (choice.equals("2")) {
                int id = Person.inputId("Enter employee ID: ");
                person = Library.getEmployeeManagement().findEmployee(id);
                if (person == null)
                    System.out.println("Employee not found!");
                else
                    break;
            }
        } while (true);

        // INPUT NUMBER OF BOOKS
        int n = 0;
        do {
            hasError = false;
            System.out.print("Enter number of books: ");
            String input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        // INPUT BOOKS
        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.println("\t\t\tBOOK " + (i + 1));
                int id = Book.inputId("Enter book ID: ");
                books[i] = Library.getBookManagement().findBook(id);
                if (books[i] == null)
                    System.out.println("Book not found!");
            } while (books[i] == null);

            int remain = books[i].getRemain();
            if (remain == 0) {
                System.out.println("Out of stock!");
                books[i--] = null;
            } else {
                books[i].setRemain(remain - 1);
            }
        }

        // INPUT BORROW DAY
        Day borrowDay = Day.inputDay("Enter borrow day: ");

        // INPUT RETURN DAY
        Day returnDay = Day.inputDay("Enter return day: ");

        // CALCULATE PRICE
        double price = person.calculatePrice(borrowDay, returnDay);

        // CREATE NEW BILL
        bills = Arrays.copyOf(bills, bills.length + 1);
        bills[bills.length - 1] = new Bill(person, books, borrowDay, returnDay, price);
    }

    public void returnBook() {
        Scanner sc = new Scanner(System.in);
        boolean hasError;

        // INPUT NUMBER OF BOOKS
        int n = 0;
        do {
            hasError = false;
            System.out.print("Enter number of books: ");
            String input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        // INPUT BOOKS
        Book[] books = new Book[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.println("\t\t\tBOOK " + (i + 1));
                int id = Book.inputId("Enter book ID: ");
                books[i] = Library.getBookManagement().findBook(id);
                if (books[i] == null)
                    System.out.println("Book not found!");
            } while (books[i] == null);

            int remain = books[i].getRemain();
            books[i].setRemain(remain + 1);
        }
    }

    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice;
        Bill[] billList;

        do {
            System.out.println("""
                    1. Find Bill by person ID.
                    2. Find Bill between low day and high day.
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {   // Find Bill by person ID
                int id = Person.inputId("Enter person ID: ");
                billList = findBill(id);
                break;
            }
            if (choice.equals("2")) {
                Day low = Day.inputDay("Enter low day (dd/mm/yyyy): ");
                Day high = Day.inputDay("Enter high day (dd/mm/yyyy): ");
                billList = findBill(low, high);
                break;
            }
        } while (true);

        if (billList.length == 0)
            System.out.println("Bill not found!");
        else {
            String temp = "";
//            System.out.printf("");
            for (Bill bill : billList)
                bill.output();
        }
    }

    public Bill[] findBill(int id) {
        Bill[] listBill = new Bill[0];
        for (Bill bill : bills) {
            if (bill.getPerson().getId() == id) {
                listBill = Arrays.copyOf(listBill, listBill.length + 1);
                listBill[listBill.length - 1] = bill;
            }
        }
        return listBill;
    }

    public Bill[] findBill(Day low, Day high) {
        Bill[] listBill = new Bill[0];
        int time = Day.calculateDays(low, high);
        /*
        * nếu khoảng cách ngày mượn đến ngày low <= khoảng cách low đến high và
        * nếu khoảng cách ngày mượn đến ngày high <= khoảng cách high đến low thì nhận
        */
        for (Bill bill : bills) {
            if (time >= Day.calculateDays(low, bill.getBorrowDay()) &&
                time >= Day.calculateDays(bill.getBorrowDay(), high)) {

                listBill = Arrays.copyOf(listBill, listBill.length + 1);
                listBill[listBill.length - 1] = bill;
            }
        }
        return listBill;
    }

    public void statistic(){

    }

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("res\\bills.dat");
            BufferedReader reader = new BufferedReader(file);
            String strLine;
            while ((strLine = reader.readLine()) != null)
                convertToObject(strLine);
            reader.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void writeFile() {
        try {
            FileWriter file = new FileWriter("res\\bills.dat");
            BufferedWriter writer = new BufferedWriter(file);
            for (Bill bill : bills)
                writer.write(bill.toString() + "\n");
            writer.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void convertToObject(String line) {
        String[] object = line.split(", ");
        int personId = Integer.parseInt(object[0]);
        String personName = object[1];
        String[] bookList = object[2].split(" / ");
        Day borrowDay = Day.parseDay(object[3]);
        Day returnDay = Day.parseDay(object[4]);
        double price = Double.parseDouble(object[5]);

        Person person = Library.getStudentManagement().findStudent(personId);
        if (person == null)
            person = Library.getEmployeeManagement().findEmployee(personId);

        Book[] books = new Book[bookList.length];
        for (int i = 0; i < books.length; i++) {
            books[i] = Library.getBookManagement().findBook(bookList[i]);
        }

        Bill bill = new Bill(person, books, borrowDay, returnDay, price);
        bills = Arrays.copyOf(bills, bills.length + 1);
        bills[bills.length - 1] = bill;
    }
}
