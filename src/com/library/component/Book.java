package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Book {
    private int id;
    private String name;
    private int remain;
    private double price;
    private Day publishDay;

    public Book() {
        this.id = 0;
        this.name = "";
        this.remain = 0;
        this.price = 0.0;
        this.publishDay = new Day();
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
        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputToArray;
        Day inputToDay = new Day();
        int inputToInt = 0;
        double inputToDouble = 0.0;
        boolean hasError;

        do {
            hasError = false;
            System.out.print("Enter ID: ");
            input = sc.nextLine();
            try {
                inputToInt = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 4 || inputToInt < 0);
        setId(inputToInt);

        do {
            System.out.print("Enter name: ");
            input = sc.nextLine();
        } while (input.isBlank());
        setName(input);

        do {
            hasError = false;
            System.out.print("Enter remain: ");
            input = sc.nextLine();
            try {
                inputToInt = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || inputToInt < 0);
        setRemain(inputToInt);

        do {
            hasError = false;
            System.out.print("Enter price: ");
            input = sc.nextLine();
            try {
                inputToDouble = Double.parseDouble(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || inputToDouble < 0.0);
        setPrice(inputToDouble);

        do {
            hasError = false;
            System.out.print("Enter publish day (dd/mm/yyyy): ");
            input = sc.nextLine();
            inputToArray = input.split("/");
            try {
                inputToDay.setDate(Integer.parseInt(inputToArray[0]));
                inputToDay.setMonth(Integer.parseInt(inputToArray[1]));
                inputToDay.setYear(Integer.parseInt(inputToArray[2]));
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(inputToDay));
        setPublishDay(inputToDay);
    }

    public void output() {
        System.out.printf("  %4d  |  %30s  |  %4d  |  %10.2f  |  ", id, name, remain, price);
        System.out.print(publishDay + "  |  ");
    }
}
