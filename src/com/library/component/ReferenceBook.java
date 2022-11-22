package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class ReferenceBook extends Book {
    private String author;

    public ReferenceBook() {
        super();
        this.author = "";
    }

    public ReferenceBook(String status, int id, String name, int remain, double price, Day publishDay, String author) {
        super(status, id, name, remain, price, publishDay);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void input() {
        super.input();
        author = inputAuthor("Enter author: ");
    }

    public static String inputAuthor(String message) {
        Scanner sc = new Scanner(System.in);
        String author;
        do {
            System.out.print(message);
            author = sc.nextLine();
        } while (author.isBlank());
        return author;
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%30s\n", author);
    }

    @Override
    public String toString() {
        return "REF, " +
                super.toString() +
                author;
    }
}
