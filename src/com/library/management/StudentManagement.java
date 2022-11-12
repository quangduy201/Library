package com.library.management;

import com.library.component.Student;

import java.util.Arrays;
import java.util.Scanner;

public class StudentManagement implements Management {
    private Student[] students;

    public StudentManagement() {
        this.students = new Student[0];
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        String input;
        int n = 0;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of students: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        students = new Student[n];
        for (int i = 0; i < n; i++) {
            students[i] = new Student();
            System.out.println("\t\t\tSTUDENT " + (i + 1));
            students[i].input();
        }
    }

    @Override
    public void output() {
        String temp = "";
        System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
        for (Student student : students)
            student.output();
    }

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        String input;
        int k = 0, n = students.length;
        boolean hasError;
        do {
            hasError = false;
            System.out.print("Enter number of students: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n <= 0);

        students = Arrays.copyOf(students, students.length + k);
        for (int i = n; i < students.length; i++) {
            students[i] = new Student();
            System.out.println("\t\t\tSTUDENT " + (i - n + 1));
            students[i].input();
        }
    }

    @Override
    public void edit() {
        Scanner sc = new Scanner(System.in);
        String input;
        int id = 0;
        boolean hasError;
        Student student;

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

        student = findStudent(id);
        if (student == null)
            System.out.println("Student not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            student.output();
            System.out.println("\t\tEdit student");
            student.input();
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
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == id) {
                String temp = "";
                System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
                students[i].output();
                for (int j = i; j < students.length; j++)
                    students[j] = students[j + 1];
                Arrays.copyOf(students, students.length - 1);
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
        Student student;

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

                student = findStudent(id);
                break;
            }
            if (choice.equals("2")) {
                do {
                    System.out.print("Enter name: ");
                    name = sc.nextLine();
                } while (name.isBlank());

                student = findStudent(name);
                break;
            }
        } while (true);

        if (student == null)
            System.out.println("Student not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            student.output();
        }
    }

    public Student findStudent(int id) {
        for (Student student : students)
            if (student.getId() == id)
                return student;
        return null;
    }

    public Student findStudent(String name) {
        for (Student student : students)
            if (student.getName().equals(name))
                return student;
        return null;
    }

    @Override
    public void statistic() {

    }
}
