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

    public Employee(String status, int id, String name, Day dob, String gender, String phone, String address, String email, String roll, Day startDate, double salary) {
        super(status, id, name, dob, gender, phone, address, email);
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
        roll = inputRoll("Enter roll: ");
        startDate = inputStartDate("Enter start date (dd/mm/yyyy): ");
        salary = inputSalary("Enter salary: ");
    }

    public static String inputRoll(String message) {
        Scanner sc = new Scanner(System.in);
        String roll;
        do {
            System.out.print(message);
            roll = sc.nextLine();
        } while (roll.isBlank());
        return roll;
    }

    public static Day inputStartDate(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        String[] day;
        Day startDate = new Day();
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            day = input.split("/");
            try {
                startDate.setDate(Integer.parseInt(day[0]));
                startDate.setMonth(Integer.parseInt(day[1]));
                startDate.setYear(Integer.parseInt(day[2]));
                if (!Day.isValidDay(startDate)) {
                    System.out.println("Start date is not valid!");
                    hasError = true;
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
        return startDate;
    }

    public static double inputSalary(String message) {
        Scanner sc = new Scanner(System.in);
        String input;
        double salary = 0.0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print(message);
            input = sc.nextLine();
            try {
                salary = Double.parseDouble(input);
                if (salary < 0.0) {
                    System.out.println("Salary must be a positive number!");
                    hasError = true;
                }
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError);
        return salary;
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%15s  |  ", roll);
        System.out.print(startDate + "  |");
        System.out.printf("%12.2f\n", salary);
    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay, int numOfBooks) {
        /*
        Tính số tiền dựa theo số ngày:
        - Trong 3 ngày đầu tiên, mỗi ngày giá tiền mỗi cuốn sách là 5000.
        - Trong 3 ngày tiếp theo, mỗi ngày giá tiền mỗi cuốn sách là 6000.
        - Những ngày sau đó mỗi ngày giá tiền mỗi cuốn sách là 10000.
        Nếu tổng số tiền >= 100000 sẽ được giảm 10%
        Nếu tổng số tiền >= 70000 sẽ được giảm 7%
        Nếu tổng số tiền >= 30000 sẽ được giảm 5%
         */
        int days = Day.calculateDays(borrowDay, returnDay);
        double money = 0.0;
        if (days - 3 < 0)
            money += days * 5000 * numOfBooks;
        else {
            money += 3 * 5000 * numOfBooks;
            days -= 3;
            if (days - 3 < 0)
                money += days * 6000 * numOfBooks;
            else {
                money += 3 * 6000 * numOfBooks;
                days -= 3;
                money += days * 10000 * numOfBooks;
            }
        }

        if (money >= 100000) {
            money -= (money * 10.0 / 100); // 10%
        } else if (money >= 70000) {
            money -= (money * 7.0 / 100); // 7%
        } else if (money >= 30000) {
            money -= (money * 5.0 / 100); // 5%
        }
        return money;
    }

    @Override
    public String toString() {
        return super.toString() +
                roll + ", " +
                startDate + ", " +
                salary;
    }
}
