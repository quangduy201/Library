package com.library.component;

import com.library.util.Day;

public class Dictionary extends Book {
    private String language;

    public Dictionary() {

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

    }

    @Override
    public void output() {

    }

    public void update() {

    }
}
