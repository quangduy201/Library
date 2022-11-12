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
        String[] day;
        String roll;
        Day startDate = new Day();
        double salary = 0.0;
        boolean hasError;

        do {
            System.out.print("Enter roll: ");
            roll = sc.nextLine();
        } while (roll.isBlank());
        setRoll(roll);

        do {
            hasError = false;
            System.out.print("Enter start date (dd/mm/yyyy): ");
            input = sc.nextLine();
            day = input.split("/");
            try {
                startDate.setDate(Integer.parseInt(day[0]));
                startDate.setMonth(Integer.parseInt(day[1]));
                startDate.setYear(Integer.parseInt(day[2]));
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || !Day.isValidDay(startDate));
        setStartDate(startDate);

        do {
            hasError = false;
            System.out.print("Enter salary: ");
            input = sc.nextLine();
            try {
                salary = Double.parseDouble(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || salary < 0);
        setSalary(salary);
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%15s  |  ", roll);
        System.out.print(startDate + "  |");
        System.out.printf("%12.2f\n", salary);
    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay) {
        return 0;
    }
}
