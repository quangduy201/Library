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

    public boolean idExists(int id) {
        for (Employee employee : employees)
            if (id == employee.getId())
                return true;
        return false;
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
        System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", "", "", "", "", "", "", "", "", "", "");
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
                k = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || k <= 0);

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
        Scanner sc = new Scanner(System.in);
        int id = Employee.inputId("Enter ID (8 digits): ");
        Employee employee = findEmployee(id);
        if (employee == null)
            System.out.println("Employee not found!");
        else {
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", "", "", "", "", "", "", "", "", "", "");
            employee.output();
            do {
                System.out.println("""
                        \n\tEdit?
                        1. Yes
                        2. No
                        """);
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    employee.setId(0); // reset ID to be able to edit ID
                    employee.input();
                    writeFile();
                    break;
                }
                if (choice.equals("2"))
                    break;
            } while (true);
        }
    }

    @Override
    public void remove() {
        Scanner sc = new Scanner(System.in);
        int id = Employee.inputId("Enter ID (8 digits): ");
        boolean found = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {   // found the employee
                found = true;
                System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", "", "", "", "", "", "", "", "", "", "");
                employees[i].output();
                do {
                    System.out.println("""
                            \n\tRemove?
                            1. Yes
                            2. No
                            """);
                    System.out.print("Enter your choice: ");
                    String choice = sc.nextLine();
                    if (choice.equals("1")) {
                        for (int j = i; j < employees.length - 1; j++)
                            employees[j] = employees[j + 1];
                        employees = Arrays.copyOf(employees, employees.length - 1);
                        System.out.println("Employee removed!");
                        writeFile();
                        break;
                    }
                    if (choice.equals("2"))
                        break;
                } while (true);
                break;
            }
        }
        if (!found) {
            System.out.println("Employee not found!");
        }
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
            if (choice.equals("1")) {   // Find Employee by ID
                int id = Employee.inputId("Enter ID (8 digits): ");
                employee = findEmployee(id);
                break;
            }
            if (choice.equals("2")) {   // Find Employee by name
                String name = Employee.inputName("Enter name: ");
                employee = findEmployee(name);
                break;
            }
        } while (true);

        if (employee == null)
            System.out.println("Employee not found!");
        else {
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s ROLL %8s START DAY %6s SALARY\n", "", "", "", "", "", "", "", "", "", "");
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
            if (employee.getName().equalsIgnoreCase(name))
                return employee;
        return null;
    }

    @Override
    public void statistic() {
        /*
        TODO:
            Thống kê số lượng nhân viên theo giới tính
            Thống kê số lượng nhân viên theo chức vụ
         */
        int n = employees.length;
        String[] rolls = new String[0];
        int[] number = new int[0];
        int male = 0, female = 0;
        boolean rollExist;
        for (int i = 0; i < n; i++) {
            if (employees[i].getGender().equals("Nam"))
                male++;
            else
                female++;

            rollExist = false;
            for (int j = 0; j < i; j++) {
                if (employees[j].getRoll().equals(employees[i].getRoll())) {
                    rollExist = true;
                    number[j]++;
                    break;
                }
            }
            if (!rollExist) {
                rolls = Arrays.copyOf(rolls, rolls.length + 1);
                rolls[rolls.length - 1] = employees[i].getRoll();
                number = Arrays.copyOf(number, number.length + 1);
                number[rolls.length - 1] = 1;
            }
        }
        System.out.printf("Number of employees: %d\n", n);
        System.out.println("Gender:");
        System.out.printf("%15s: %d\t--> %.2f%%\n", "Nam", male, male * 100.0 / n);
        System.out.printf("%15s: %d\t--> %.2f%%\n", "Nữ", female, female * 100.0 / n);
        if (rolls.length > 0)
            System.out.println("Rolls:");
        for (int i = 0; i < rolls.length; i++) {
            System.out.printf("%15s: %d\t--> %.2f%%\n", rolls[i], number[i], number[i] * 100.0 / n);
        }
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
