package com.library.component;

import com.library.util.Day;

public class Bill {
    private String status;
    private Person person;
    private Book[] books;
    private Day borrowDay;
    private Day returnDay;
    private double price;

    public Bill() {
        this.status = "";
        this.books = new Book[0];
        this.borrowDay = new Day();
        this.returnDay = new Day();
        this.price = 0.0;
    }

    public Bill(String status, Person person, Book[] books, Day borrowDay, Day returnDay, double price) {
        this.status = status;
        this.person = person;
        this.books = books;
        this.borrowDay = borrowDay;
        this.returnDay = returnDay;
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public Book[] getBooks() {
        return books;
    }

    public Person getPerson() {
        return person;
    }

    public Day getBorrowDay() {
        return borrowDay;
    }

    public Day getReturnDay() {
        return returnDay;
    }

    public double getPrice() {
        return price;
    }

    public void hasReturned() {
        status = "Returned";
    }

    public void output() {
        char check = ' ';
        if (status.equals("Returned"))
            check = '\u2713';   // '✓'
        System.out.printf("%c  |%10d  |%24s  |%30s  |  ", check, person.getId(), person.getName(), books[0].getName());
        System.out.println(borrowDay + "  |  " + returnDay + "  |  " + price);
        String temp = "";
        for (int i = 1; i < books.length; i++)
            System.out.printf("   |%10s  |%24s  |%30s  |%12s  |%12s  |\n", temp, temp, books[i].getName(), temp, temp);
    }

    @Override public String toString() {
        String bookList = books[0].getName();
        for (int i = 1; i < books.length; i++)
            bookList += " / " + books[i].getName();
        return  status + ", " +
                person.getId() + ", " +
                person.getName() + ", " +
                bookList + ", " +
                borrowDay + ", " +
                returnDay + ", " +
                price;
    }
}
