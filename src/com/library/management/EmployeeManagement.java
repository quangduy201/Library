package com.library.management;

import com.library.component.Employee;

public class EmployeeManagement implements Management {
    private Employee[] employees;

    public EmployeeManagement() {

    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public void input() {

    }

    public void output() {

    }

    public void add() {

    }

    public void edit() {

    }

    public void remove() {

    }

    public Employee findEmployee(int id) {
        return new Employee();
    }

    public Employee findEmployee(String name) {
        return new Employee();
    }

    public void statistic() {

    }
}
