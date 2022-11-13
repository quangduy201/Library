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
        id = inputId("Enter ID: ");
        name = inputName("Enter name: ");
        remain = inputRemain("Enter remain: ");
        price = inputPrice("Enter price: ");
        publishDay = inputPublishDay("Enter publish day (dd/mm/yyyy): ");
    }

    public static int inputId(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 4 || id < 0);
        return id;
    }

    public static String inputName(String message) {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print(message);
            name = sc.nextLine();
        } while (name.isBlank());
        return name;
    }

    public static int inputRemain(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        int remain = 0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            try {
                remain = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || remain < 0);
        return remain;
    }

    public static double inputPrice(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        double price = 0.0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            try {
                price = Double.parseDouble(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || price < 0.0);
        return price;
    }

    public static Day inputPublishDay(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] day;
        Day publishDay = new Day();
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            day = input.split("/");
            try {
                publishDay.setDate(Integer.parseInt(day[0]));
                publishDay.setMonth(Integer.parseInt(day[1]));
                publishDay.setYear(Integer.parseInt(day[2]));
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(publishDay));
        return publishDay;
    }

    public void output() {
        System.out.printf("  %4d  |  %30s  |  %4d  |  %10.2f  |  ", id, name, remain, price);
        System.out.print(publishDay + "  |  ");
    }

    @Override
    public String toString() {
        return  id + ", " +
                name + ", " +
                remain + ", " +
                price + ", " +
                publishDay + ", ";
    }
}
