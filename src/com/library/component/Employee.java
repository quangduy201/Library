package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Employee extends Person {
    private String roll;
    private Day startDate;
    private double salary;

    public Employee() {

    }

    public Employee(String roll, Day startDate, double salary) {
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

    public Day getStartDay() {
        return startDate;
    }

    public void setStartDay(Day startDate) {
        this.startDate = startDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

//    @Override
//    public void input(){
//        Scanner sc = new Scanner(System.in);
//        super.input();
//        String roll,startDate,salary;
//        do {
//            System.out.println("Enter Roll: ");
//            roll = sc.nextLine();
//        }while(roll.isEmpty());
//        setRoll(roll);
//        do {
//            System.out.println("Enter startDate: ");
//            startDate = sc.nextLine();
//        }while(startDate != "a");
//        setStartDay(startDate);
//        do {
//            System.out.println("Enter salary: ");
//            salary = sc.nextLine();
//        }while(salary != "a");
//        setSalary(salary);
//    }
//    @Override
//    public void output(){
//        super.output();
//        System.out.println("Roll: "+roll+"\t StartDate: "+startDate+"\t Salary: "+salary);
//    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay) {
        return 0;
    }
}
