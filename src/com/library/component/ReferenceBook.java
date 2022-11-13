package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class ReferenceBook extends Book {
    private String author;
    private String translator;

    public ReferenceBook() {
        super();
        this.author = "";
        this.translator = "";
    }

    public ReferenceBook(int id, String name, int remain, double price, Day publishDay, String author, String translator) {
        super(id, name, remain, price, publishDay);
        this.author = author;
        this.translator = translator;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    @Override
    public void input() {
        super.input();
        author = inputAuthor("Enter author: ");
        translator = inputTranslator("Enter translator: ");
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

    public static String inputTranslator(String message) {
        Scanner sc = new Scanner(System.in);
        String translator;
        do {
            System.out.print(message);
            translator = sc.nextLine();
        } while (translator.isBlank());
        return translator;
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%30s  |  %s\n", author, translator);
    }

    public void update() {

    }

    @Override
    public String toString() {
        return "REF, " +
                super.toString() +
                author + ", " +
                translator;
    }
}
