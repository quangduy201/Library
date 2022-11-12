package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Dictionary extends Book {
    private String language;

    public Dictionary() {
        super();
        this.language = "";
    }

    public Dictionary(int id, String name, int remain, double price, Day publishDay, String language) {
        super(id, name, remain, price, publishDay);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);
        String language;

        do {
            System.out.print("Enter language: ");
            language = sc.nextLine();
        } while (language.isBlank());
        setLanguage(language);
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%30s\n", language);
    }

    public void update() {

    }
}
