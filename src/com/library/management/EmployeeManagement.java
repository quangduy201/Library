package com.library.management;

import com.library.component.Employee;

import java.util.Arrays;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        String input;
        int n = 0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of employees: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = new Employee();
            System.out.println("\t\t\tEMPLOYEE " + (i + 1));
            employees[i].input();
        }
    }

    @Override
    public void output() {
        String temp = "";
        System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
        for (Employee employee : employees)
            employee.output();
    }

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        String input;
        int k = 0, n = employees.length;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of employees: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        employees = Arrays.copyOf(employees, employees.length + k);
        for (int i = n; i < employees.length; i++) {
            employees[i] = new Employee();
            System.out.println("\t\t\tEMPLOYEE " + (i - n + 1));
            employees[i].input();
        }
    }

    @Override
    public void edit() {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
        boolean hasError;
        Employee employee;

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

        employee = findEmployee(id);
        if (employee == null)
            System.out.println("Employee not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            employee.output();
            System.out.println("\t\tEdit employee");
            employee.input();
        }
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
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

        boolean isRemoved = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                String temp = "";
                System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
                employees[i].output();
                for (int j = i; j < employees.length; j++)
                    employees[j] = employees[j + 1];
                Arrays.copyOf(employees, employees.length - 1);
                isRemoved = true;
                break;
            }
        }
        if (isRemoved)
            System.out.println("Book removed!");
        else
            System.out.println("Book not found!");
    }

    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int id = 0;
        String name;
        boolean hasError;
        Employee employee;

        do {
            System.out.println("""
                    1. Find by ID
                    2. Find by name
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {
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

                employee = findEmployee(id);
                break;
            }
            if (choice.equals("2")) {
                do {
                    System.out.print("Enter name: ");
                    name = sc.nextLine();
                } while (name.isBlank());

                employee = findEmployee(name);
                break;
            }
        } while (true);

        if (employee == null)
            System.out.println("Student not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            employee.output();
        }
    }

    public Employee findEmployee(int id) {
        for (Employee employee : employees)
            if (employee.getId() == id)
                return employee;
        return null;
    }

    public Employee findEmployee(String name) {
        for (Employee employee : employees)
            if (employee.getName().equals(name))
                return employee;
        return null;
    }

    @Override
    public void statistic() {

    }
}
