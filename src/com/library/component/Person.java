package com.library.component;

import com.library.main.Library;
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
        int id;
        do {
            id = inputId("Enter ID (8 digits): ");
            if (Library.getStudentManagement().idExists(id) || Library.getEmployeeManagement().idExists(id))
                System.out.println("ID has been used!");
            else
                break;
        } while (true);
        this.id = id;
        name = inputName("Enter name: ");
        dob = inputDob("Enter date of birth (dd/mm/yyyy): ");
        gender = inputGender("Enter gender (Nam|Nữ): ");
        phone = inputPhone("Enter phone (10 digits): ");
        address = inputAddress("Enter address (city): ");
        email = inputEmail("Enter email: ");
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
                if (input.length() != 8) {
                    System.out.println("ID must have 8 digits!");
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
        } while (name.length() == 0);
        return name;
    }

    public static Day inputDob(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] day;
        Day dob = new Day();
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            day = input.split("/");
            try {
                dob.setDate(Integer.parseInt(day[0]));
                dob.setMonth(Integer.parseInt(day[1]));
                dob.setYear(Integer.parseInt(day[2]));
                if (!Day.isValidDay(dob)) {
                    System.out.println("Date of birth is not valid!");
                    hasError = true;
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(dob));
        return dob;
    }

    public static String inputGender(String message) {
        Scanner sc = new Scanner(System.in);
        String gender;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            gender = sc.nextLine();
            if (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nữ")) {
                System.out.println("Gender must be \"Nam\" or \"Nữ\"!");
                hasError = true;
            }
        } while (hasError);
        if (gender.equalsIgnoreCase("Nam"))
            gender = "Nam";
        else
            gender = "Nữ";
        return gender;
    }

    public static String inputPhone(String message) {
        Scanner sc = new Scanner(System.in);
        String phone;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            phone = sc.nextLine();
            if (phone.length() != 10) {
                System.out.println("Phone number must have 10 digits!");
                hasError = true;
            }
        } while (hasError);
        return phone;
    }

    public static String inputAddress(String message) {
        Scanner sc = new Scanner(System.in);
        String address;
        do {
            System.out.print(message);
            address = sc.nextLine();
        } while (address.isBlank());
        return address;
    }

    public static String inputEmail(String message) {
        Scanner sc = new Scanner(System.in);
        String email;
        do {
            System.out.print(message);
            email = sc.nextLine();
        } while (email.isBlank());
        return email;
    }

    public void output() {
        System.out.printf("%10d  |%24s  |  ", id, name);
        System.out.print(dob + "  |");
        System.out.printf("%6s  |%12s  |%18s  |%25s  |", gender, phone, address, email);
    }

    public abstract double calculatePrice(Day borrowDay, Day returnDay, int numOfBooks);

    @Override
    public String toString() {
        return  id + ", " +
                name + ", " +
                dob + ", " +
                gender + ", " +
                phone + ", " +
                address + ", " +
                email + ", ";
    }
}
