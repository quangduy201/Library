package com.library.component;

import com.library.util.Day;

public class Book {
    private int id;
    private String name;
    private int remain;
    private double price;
    private Day publishDay;

    public Book() {

    }

    public Book(int id, String name, int remain, double price, Day publishDay) {
        this.id = id;
        this.name = name;
        this.remain = remain;
        this.price = price;
        this.publishDay = publishDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Day getPublishDay() {
        return publishDay;
    }

    public void setPublishDay(Day publishDay) {
        this.publishDay = publishDay;
    }

    public void input() {

    }

    public void output() {

    }
}
