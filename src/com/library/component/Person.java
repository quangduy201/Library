package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public abstract class Person {
    private int id;
    private String name;
    private Day dob;
    private String gender;
    private String phone;
    private String address;
    private String email;

    public Person() {
        this.id = 0;
        this.name = "";
        this.dob = new Day();
        this.gender = "";
        this.phone = "";
        this.address = "";
        this.email = "";
    }

    public Person(int id, String name, Day dob, String gender, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public Day getDob() {
        return dob;
    }

    public void setDob(Day dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void input() {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputToArray;
        Day inputToDay = new Day();
        int inputToInt = 0;
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
        } while (hasError || input.length() != 8 || inputToInt < 0);
        setId(inputToInt);

        do {
            System.out.print("Enter name: ");
            input = sc.nextLine();
        } while (input.length() == 0);
        setName(input);

        do {
            hasError = false;
            System.out.print("Enter date of birth (dd/mm/yyyy): ");
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
        setDob(inputToDay);

        do {
            System.out.print("Enter gender (M|F): ");
            input = sc.nextLine();
        } while (!input.equals("M") && !input.equals("F"));
        setGender(input);

        do {
            hasError = false;
            System.out.print("Enter phone (10 digits): ");
            input = sc.nextLine();
            try {
                inputToInt = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 10 || inputToInt < 0);
        setPhone(input);

        do {
            System.out.print("Enter address: ");
            input = sc.nextLine();
        } while (input.isBlank());
        setAddress(input);

        do {
            System.out.print("Enter email: ");
            input = sc.nextLine();
        } while (input.isBlank());
        setEmail(input);
    }

    public void output() {
        System.out.printf("  %8d  |  %30s  |  ", id, name);
        System.out.print(dob + "  |");
        System.out.printf("  %6s  |  %s  |  %30s  |  %30s  ", gender, phone, address, email);
    }

    public abstract double calculatePrice(Day borrowDay, Day returnDay);
}
