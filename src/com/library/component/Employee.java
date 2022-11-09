package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Employee extends Person {
    private String roll;
    private Day strartDay;
    private Double salary;
    public Employee(){

    }
    public Employee(String roll,Day strartDay,Double salary){
        this.roll=roll;
        this.strartDay=strartDay;
        this.salary=salary;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public Day getStrartDay() {
        return strartDay;
    }

    public void setStrartDay(Day strartDay) {
        this.strartDay = strartDay;
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
//        setStrartDay(startDate);
//        do {
//            System.out.println("Enter salary: ");
//            salary = sc.nextLine();
//        }while(salary != "a");
//        setSalary(salary);
//    }
//    @Override
//    public void output(){
//        super.output();
//        System.out.println("Roll: "+roll+"\t StrartDate: "+strartDay+"\t Salary: "+salary);
//    }
//    public calculatePrice(Day borrowDay,Day returnDay){
//        double
//    }
}
