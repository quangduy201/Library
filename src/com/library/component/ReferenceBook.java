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
        Scanner sc = new Scanner(System.in);
        String author, translator;

        do {
            System.out.print("Enter author: ");
            author = sc.nextLine();
        } while (author.isBlank());
        setAuthor(author);

        do {
            System.out.print("Enter translator: ");
            translator = sc.nextLine();
        } while (translator.isBlank());
        setTranslator(translator);
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%30s  |  %s\n", author, translator);
    }

    public void update() {

    }
}
