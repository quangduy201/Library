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
        String[] day;
        Day dob = new Day();
        int id = 0, phone = 0;
        String name, gender, address, email;
        boolean hasError;

        do {
            hasError = false;
            System.out.print("Enter ID: ");
            input = sc.nextLine();
            try {
                id = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 8 || id < 0);
        setId(id);

        do {
            System.out.print("Enter name: ");
            name = sc.nextLine();
        } while (name.length() == 0);
        setName(name);

        do {
            hasError = false;
            System.out.print("Enter date of birth (dd/mm/yyyy): ");
            input = sc.nextLine();
            day = input.split("/");
            try {
                dob.setDate(Integer.parseInt(day[0]));
                dob.setMonth(Integer.parseInt(day[1]));
                dob.setYear(Integer.parseInt(day[2]));
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(dob));
        setDob(dob);

        do {
            System.out.print("Enter gender (M|F): ");
            gender = sc.nextLine();
        } while (!gender.equals("M") && !gender.equals("F"));
        setGender(gender);

        do {
            hasError = false;
            System.out.print("Enter phone (10 digits): ");
            input = sc.nextLine();
            try {
                phone = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || input.length() != 10 || phone < 0);
        setPhone(input);

        do {
            System.out.print("Enter address (city): ");
            address = sc.nextLine();
        } while (address.isBlank());
        setAddress(address);

        do {
            System.out.print("Enter email: ");
            email = sc.nextLine();
        } while (email.isBlank());
        setEmail(email);
    }

    public void output() {
        System.out.printf("%10d  |%24s  |  ", id, name);
        System.out.print(dob + "  |");
        System.out.printf("%6s  |%12s  |%18s  |%25s  |", gender, phone, address, email);
    }

    public abstract double calculatePrice(Day borrowDay, Day returnDay);
}
