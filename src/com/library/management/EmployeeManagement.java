package com.library.management;

import com.library.component.Employee;

public class EmployeeManagement implements Management {
    private Employee[] employees;

    public EmployeeManagement() {
        this.employees = new Employee[0];
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    @Override
    public void input() {

    }

    @Override
    public void output() {

    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void find() {

    }

    public Employee findEmployee(int id) {
        return new Employee();
    }

    public Employee findEmployee(String name) {
        return new Employee();
    }

    @Override
    public void statistic() {

    }
}
