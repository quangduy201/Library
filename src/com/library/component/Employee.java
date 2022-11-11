package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Employee extends Person {
    private String roll;
    private Day startDate;
    private double salary;

    public Employee() {
        super();
        this.roll = "";
        this.startDate = new Day();
        this.salary = 0.0;
    }

    public Employee(int id, String name, Day dob, String gender, String phone, String address, String email, String roll, Day startDate, double salary) {
        super(id, name, dob, gender, phone, address, email);
        this.roll = roll;
        this.startDate = startDate;
        this.salary = salary;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public Day getStartDate() {
        return startDate;
    }

    public void setStartDate(Day startDate) {
        this.startDate = startDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public void input() {
        super.input();
        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputToArray;
        Day inputToDay = new Day();
        double inputToDouble = 0.0;
        boolean hasError;

        do {
            System.out.print("Enter roll: ");
            input = sc.nextLine();
        } while (input.isBlank());
        setRoll(input);

        do {
            hasError = false;
            System.out.print("Enter start date (dd/mm/yyyy): ");
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

        do {
            hasError = false;
            System.out.print("Enter salary: ");
            input = sc.nextLine();
            try {
                inputToDouble = Double.parseDouble(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || inputToDouble < 0);
        setSalary(inputToDouble);
    }

    @Override
    public void output() {
        super.output();
    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay) {
        return 0;
    }
}
