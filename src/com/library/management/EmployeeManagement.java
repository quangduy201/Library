package com.library.management;

import com.library.component.Employee;
import com.library.util.Day;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class EmployeeManagement implements Management, File {
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
        writeFile();
    }

    @Override
    public void output() {
        if (employees.length == 0) {
            System.out.println("There are no employees yet.");
            return;
        }
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
        writeFile();
    }

    @Override
    public void edit() {
        int id = Employee.inputId("Enter ID: ");
        Employee employee = findEmployee(id);
        if (employee == null)
            System.out.println("Employee not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            employee.output();
            System.out.println("\t\tEdit employee");
            employee.input();
            writeFile();
        }
    }

    @Override
    public void remove() {
        int id = Employee.inputId("Enter ID: ");
        boolean isRemoved = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {   // found the employee
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
        if (isRemoved) {
            System.out.println("Book removed!");
            writeFile();
        }
        else
            System.out.println("Book not found!");
    }

    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice;
        Employee employee;

        do {
            System.out.println("""
                    1. Find by ID
                    2. Find by name
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {
                int id = Employee.inputId("Enter ID: ");
                employee = findEmployee(id);
                break;
            }
            if (choice.equals("2")) {
                String name = Employee.inputName("Enter name: ");
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

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("res\\employees.dat");
            BufferedReader reader = new BufferedReader(file);
            String strLine;
            while ((strLine = reader.readLine()) != null)
                convertToObject(strLine);
            reader.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void writeFile() {
        try {
            FileWriter file = new FileWriter("res\\employees.dat");
            BufferedWriter writer = new BufferedWriter(file);
            for (Employee employee : employees)
                writer.write(employee.toString() + "\n");
            writer.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void convertToObject(String line) {
        String[] object = line.split(", ");
        int id = Integer.parseInt(object[0]);
        String name = object[1];
        Day dob = Day.parseDay(object[2]);
        String gender = object[3];
        String phone = object[4];
        String address = object[5];
        String email = object[6];
        String roll = object[7];
        Day startDate = Day.parseDay(object[8]);
        double salary = Double.parseDouble(object[9]);
        Employee employee = new Employee(id, name, dob, gender, phone, address, email, roll, startDate, salary);
        employees = Arrays.copyOf(employees, employees.length + 1);
        employees[employees.length - 1] = employee;
    }
}
