package com.library.management;

import com.library.component.Student;
import com.library.util.Day;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class StudentManagement implements Management, File {
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
        writeFile();
    }

    @Override
    public void output() {
        if (students.length == 0) {
            System.out.println("There are no students yet.");
            return;
        }
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
        writeFile();
    }

    @Override
    public void edit() {
        int id = Student.inputId("Enter ID: ");
        Student student = findStudent(id);
        if (student == null)
            System.out.println("Student not found!");
        else {
            String temp = "";
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", temp, temp, temp, temp, temp, temp, temp, temp, temp, temp, temp);
            student.output();
            System.out.println("\t\tEdit student");
            student.input();
            writeFile();
        }
    }

    @Override
    public void remove() {
        int id = Student.inputId("Enter ID: ");
        boolean isRemoved = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getId() == id) {    // found the student
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
        String choice, input;
        Student student;

        do {
            System.out.println("""
                    1. Find by ID
                    2. Find by name
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {
                int id = Student.inputId("Enter ID: ");
                student = findStudent(id);
                break;
            }
            if (choice.equals("2")) {
                String name = Student.inputName("Enter name: ");
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

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("res\\students.dat");
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
            FileWriter file = new FileWriter("res\\students.dat");
            BufferedWriter writer = new BufferedWriter(file);
            for (Student student : students)
                writer.write(student.toString() + "\n");
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
        String faculty = object[8];
        String major = object[9];
        String classroom = object[10];
        Student employee = new Student(id, name, dob, gender, phone, address, email, faculty, major, classroom);
        students = Arrays.copyOf(students, students.length + 1);
        students[students.length - 1] = employee;
    }
}
