package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class EducationBook extends Book {
    private String publisher;

    public EducationBook() {
        super();
        this.publisher = "";
    }

    public EducationBook(int id, String name, int remain, double price, Day publishDay, String publisher) {
        super(id, name, remain, price, publishDay);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public void input() {
        super.input();
        publisher = inputPublisher("Enter publisher: ");
    }

    public static String inputPublisher(String message) {
        Scanner sc = new Scanner(System.in);
        String publisher;
        do {
            System.out.print(message);
            publisher = sc.nextLine();
        } while (publisher.isBlank());
        return publisher;
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%30s\n", publisher);
    }

    public void update() {

    }

    @Override
    public String toString() {
        return "EDU, " +
                super.toString() +
                publisher;
    }
}
