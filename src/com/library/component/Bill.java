package com.library.component;

import com.library.util.Day;

public class Bill extends Day {
    private Book book[];
    private Student student;
    private Employee employee;
    private Day borrowDay;
    private Day returnDay;
    private double price;

    public Bill() {
        this.book = new Book[0];
        this.student = new Student();
        this.employee = new Employee();
        this.borrowDay = new Day();
        this.returnDay = new Day();
        this.price = 0.0;
    }

    public Bill(Book[] book, Student student, Employee employee, Day borrowDay, Day returnDay, double price) {
        this.book = book;
        this.student = student;
        this.employee = employee;
        this.borrowDay = borrowDay;
        this.returnDay = returnDay;
        this.price = price;
    }

    public Book[] getBook() {
        return book;
    }

    public void setBook(Book[] book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Day getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(Day borrowDay) {
        this.borrowDay = borrowDay;
    }

    public Day getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Day returnDay) {
        this.returnDay = returnDay;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void output() {
        System.out.println("Bill");
        if (employee == null) {
            System.out.println("Student's name: " + student.getName());
            System.out.println("Student's gender: " + student.getGender());
            System.out.println("Student's id: " + student.getId());
            System.out.println("Student's school: " + student.getSchool());
            System.out.println("Student's faculty: " + student.getFaculty());
            System.out.println("Student's class: " + student.getClass());
            System.out.println("Student's phone: " + student.getPhone());
            System.out.println("Student's email: " + student.getEmail());
        }
        if (student == null) {
            System.out.println("Employee's name: " + employee.getName());
            System.out.println("Employee's gender: " + employee.getGender());
            System.out.println("Employee's id: " + employee.getId());
            System.out.println("Employee's roll: " + employee.getRoll());
            System.out.println("Employee's phone: " + employee.getPhone());
            System.out.println("Employee's email: " + employee.getEmail());
        }
        System.out.println("Book borrow list:");
        for (Book b : book) {
            System.out.println(b.getName() + " " + b.getId());
        }
        System.out.println("Borrow day: " + getBorrowDay());
        System.out.println("Return day: " + getReturnDay());
        System.out.println("Price: " + getPrice());
    }
}
