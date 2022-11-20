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

    public static Bill[] getBills() {
        return bills;
    }

    public void borrowBook() {
        Scanner sc = new Scanner(System.in);
        boolean hasError;

        // INPUT PERSON
        Person person;
        do {
            int id = Person.inputId("Enter person ID: ");
            person = Library.getStudentManagement().findStudent(id);
            if (person == null) {
                person = Library.getEmployeeManagement().findEmployee(id);
                if (person == null)
                    System.out.println("Person not found!");
            }
        } while (person == null);

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
        bills[bills.length - 1] = new Bill("Borrowed", person, books, borrowDay, returnDay, price);
        writeFile();
    }

    public void returnBook() {
        // INPUT PERSON
        int id = Person.inputId("Enter person ID: ");
        Person person = Library.getStudentManagement().findStudent(id);
        if (person == null) {
            person = Library.getEmployeeManagement().findEmployee(id);
            if (person == null) {
                System.out.println("Person not found!");
                return;
            }
        }

        // FIND BILLS OF PERSON
        Bill[] billList = new Bill[0];
        for (Bill bill : bills) {
            if (bill.getPerson() == person && bill.getMode().equals("Borrowed")) {
                billList = Arrays.copyOf(billList, billList.length + 1);
                billList[billList.length - 1] = bill;
            }
        }
        if (billList.length == 0) {
            System.out.println("This person did not borrow any books.");
            return;
        }

        // CHOOSE WHICH BILL TO CHECK
        for (int i = 0; i < billList.length; i++) {
            System.out.println(i + 1);
            billList[i].output();
        }
        Scanner sc = new Scanner(System.in);
        boolean hasError;
        int index = 0;
        do {
            hasError = false;
            System.out.print("Choose which one: ");
            String input = sc.nextLine();
            try {
                index = Integer.parseInt(input);
                if (index <= 0 || index > billList.length)
                    hasError = true;
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
        index--;

        // CONFIRM
        do {
            System.out.println("""
                \n\tAre you sure?
                1. Yes
                2. No
                """);
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                billList[index].hasReturned();
                Book[] books = billList[index].getBooks();
                for (Book book : books) {
                    int remain = book.getRemain();
                    book.setRemain(remain + 1);
                }
                System.out.println("Books have been returned!");
                writeFile();
                break;
            }
            if (choice.equals("2"))
                break;
        } while (true);
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
            if (choice.equals("2")) {   // Find Bill between low day and high day
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
            System.out.printf("");
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

    public void statistic() {
        /*
        TODO:
            Thống kê số lượng mượn/trả sách
            Thống kê số lượng người mượn là sinh viên hay nhân viên
            Thống kê số lượng người đã trả
            Tính tổng số tiền
         */
        int n = bills.length;
        int student = 0, employee = 0, returned = 0;
        double total = 0.0;
        for (Bill bill : bills) {
            if (bill.getPerson() instanceof Student)
                student++;
            if (bill.getPerson() instanceof Employee)
                employee++;
            if (bill.getMode().equals("Returned"))
                returned++;
            total += bill.getPrice();
        }

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
        String mode = object[0];
        int personId = Integer.parseInt(object[1]);
        String personName = object[2];
        String[] bookList = object[3].split(" / ");
        Day borrowDay = Day.parseDay(object[4]);
        Day returnDay = Day.parseDay(object[5]);
        double price = Double.parseDouble(object[6]);

        int borrow;
        if (mode.equals("Borrowed"))
            borrow = 1;
        else
            borrow = 0;

        Person person = Library.getStudentManagement().findStudent(personId);
        if (person == null)
            person = Library.getEmployeeManagement().findEmployee(personId);

        Book[] books = new Book[bookList.length];
        for (int i = 0; i < books.length; i++) {
            books[i] = Library.getBookManagement().findBook(bookList[i]);
            books[i].setRemain(books[i].getRemain() - borrow);
        }

        Bill bill = new Bill(mode, person, books, borrowDay, returnDay, price);
        bills = Arrays.copyOf(bills, bills.length + 1);
        bills[bills.length - 1] = bill;
    }
}
