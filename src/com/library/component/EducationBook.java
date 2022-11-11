package com.library.component;

import com.library.util.Day;

public class EducationBook extends Book {
    private String publisher;

    public EducationBook() {

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

    }

    @Override
    public void output() {

    }

    public void update() {

    }
}
