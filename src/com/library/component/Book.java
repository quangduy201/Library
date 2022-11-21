package com.library.component;

import com.library.main.Library;
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
        int id;
        do {
            id = inputId("Enter ID (4 digits): ");
            if (Library.getBookManagement().idExists(id))
                System.out.println("ID has been used!");
            else
                break;
        } while (true);
        this.id = id;
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
                if (input.length() != 4) {
                    System.out.println("ID must have 4 digits!");
                    hasError = true;
                } else if (id < 0) {
                    System.out.println("ID must be a positive number!");
                    hasError = true;
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
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
                if (remain < 0) {
                    System.out.println("Remain number must be a positive number!");
                    hasError = true;
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
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
                if (price < 0.0) {
                    System.out.println("Price must be a positive number!");
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
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
                if (!Day.isValidDay(publishDay)) {
                    System.out.println("Publish day is not valid!");
                    hasError = true;
                }
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
