package com.library.component;

import com.library.util.Day;

public class ReferenceBook extends Book {
    private String author;
    private String translator;

    public ReferenceBook() {

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

    }

    @Override
    public void output() {

    }

    public void update() {

    }
}
