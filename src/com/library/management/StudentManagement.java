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

    public boolean idExists(int id) {
        for (Student student : students)
            if (student.getStatus().equals("enabled") && id == student.getId())
                return true;
        return false;
    }

    @Override
    public void input() {
        Scanner sc = new Scanner(System.in);
        String choice, input;
        int n = 0;
        boolean hasError;

        do {
            System.out.println("""
                    Inputting students will overwrite all of the students.
                    All existing students will be lost.
                    \tDo you want to continue?
                    1. Yes
                    2. No
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1"))
                break;
            if (choice.equals("2"))
                return;
        } while (true);

        do {
            hasError = false;
            System.out.print("Enter number of students: ");
            input = sc.nextLine();
            try {
                n = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || n < 0);

        Student[] newStudents = new Student[n];
        students = new Student[0];
        for (int i = 0; i < n; i++) {
            newStudents[i] = new Student();
            System.out.println("\t\t\tSTUDENT " + (i + 1));
            newStudents[i].input();
            students = Arrays.copyOf(students, students.length + 1);
            students[i] = newStudents[i];
        }
        writeFile(students, false);
    }

    @Override
    public void output() {
        boolean hasStudents = false;
        for (Student student : students) {
            if (student.getStatus().equals("enabled")) {
                hasStudents = true;
                break;
            }
        }
        if (!hasStudents) {
            System.out.println("There are no students yet.");
            return;
        }
        System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", "", "", "", "", "", "", "", "", "", "", "");
        for (Student student : students)
            if (student.getStatus().equals("enabled"))
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
                k = Integer.parseInt(input);
            } catch (Exception e) {
                hasError = true;
            }
        } while (hasError || k < 0);

        Student[] newStudents = new Student[k];
        for (int i = 0; i < k; i++) {
            newStudents[i] = new Student();
            System.out.println("\t\t\tSTUDENT " + (i + 1));
            newStudents[i].input();
            students = Arrays.copyOf(students, students.length + 1);
            students[i + n] = newStudents[i];
        }
        writeFile(newStudents, true);
    }

    @Override
    public void edit() {
        Scanner sc = new Scanner(System.in);
        int id = Student.inputId("Enter ID (8 digits): ");
        Student student = findStudent(id);
        if (student == null)
            System.out.println("Student not found!");
        else {
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", "", "", "", "", "", "", "", "", "", "", "");
            student.output();
            do {
                System.out.println("""
                        \n\tEdit?
                        1. Yes
                        2. No
                        """);
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    student.setId(0); // reset ID to be able to edit ID
                    student.input();
                    System.out.println("Student edited!");
                    writeFile(students, false);
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
        int id = Student.inputId("Enter ID (8 digits): ");
        Student student = findStudent(id);
        if (student == null)
            System.out.println("Student not found!");
        else {
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", "", "", "", "", "", "", "", "", "", "", "");
            student.output();
            do {
                System.out.println("""
                            \n\tRemove?
                            1. Yes
                            2. No
                            """);
                System.out.print("Enter your choice: ");
                String choice = sc.nextLine();
                if (choice.equals("1")) {
                    student.setStatus("disabled");
                    System.out.println("Student removed!");
                    writeFile(students, false);
                    break;
                }
                if (choice.equals("2"))
                    break;
            } while (true);
        }
    }

    @Override
    public void find() {
        Scanner sc = new Scanner(System.in);
        String choice;
        Student student;

        do {
            System.out.println("""
                    1. Find Student by ID
                    2. Find Student by name
                    """);
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();
            if (choice.equals("1")) {   // Find Student by ID
                int id = Student.inputId("Enter ID (8 digits): ");
                student = findStudent(id);
                break;
            }
            if (choice.equals("2")) {   // Find Student by name
                String name = Student.inputName("Enter name: ");
                student = findStudent(name);
                break;
            }
        } while (true);

        if (student == null)
            System.out.println("Student not found!");
        else {
            System.out.printf("%4s ID %15s NAME %16s DOB %5s GENDER %5s PHONE %10s ADDRESS %16s EMAIL %16s SCHOOL %17s FACULTY %20s MAJOR %10s CLASSROOM\n", "", "", "", "", "", "", "", "", "", "", "");
            student.output();
        }
    }

    public Student findStudent(int id) {
        for (Student student : students)
            if (student.getStatus().equals("enabled") && student.getId() == id)
                return student;
        return null;
    }

    public Student findStudent(String name) {
        for (Student student : students)
            if (student.getStatus().equals("enabled") && student.getName().equalsIgnoreCase(name))
                return student;
        return null;
    }

    @Override
    public void statistic() {
        /*
        Thống kê số lượng sinh viên theo giới tính
        Thống kê số lượng sinh viên theo khoa
         */
        int n = 0;
        String[] faculties = new String[0];
        int[] number = new int[0];
        int male = 0, female = 0;
        boolean facultyExist;
        for (int i = 0; i < students.length; i++) {
            if (students[i].getStatus().equals("disabled"))
                continue;
            n++;
            if (students[i].getGender().equals("Nam"))
                male++;
            else
                female++;

            facultyExist = false;
            for (int j = 0; j < i; j++) {
                if (students[j].getFaculty().equals(students[i].getFaculty())) {
                    facultyExist = true;
                    number[j]++;
                    break;
                }
            }
            if (!facultyExist) {
                faculties = Arrays.copyOf(faculties, faculties.length + 1);
                faculties[faculties.length - 1] = students[i].getFaculty();
                number = Arrays.copyOf(number, number.length + 1);
                number[number.length - 1] = 1;
            }
        }
        System.out.printf("Number of students: %d\n", n);
        System.out.println("Gender:");
        System.out.printf("%25s: %d\t--> %.2f%%\n", "Nam", male, male * 100.0 / n);
        System.out.printf("%25s: %d\t--> %.2f%%\n", "Nữ", female, female * 100.0 / n);
        System.out.println("Faculty:");
        if (faculties.length == 0)
            System.out.println("\tThere are no students yet!");
        for (int i = 0; i < faculties.length; i++) {
            System.out.printf("%25s: %d\t--> %.2f%%\n", faculties[i], number[i], number[i] * 100.0 / n);
        }
    }

    @Override
    public void readFile() {
        try {
            FileReader file = new FileReader("data\\students.txt");
            BufferedReader reader = new BufferedReader(file);
            String strLine;
            while ((strLine = reader.readLine()) != null)
                convertToObject(strLine);
            reader.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void writeFile(Object[] objects, boolean append) {
        try {
            FileWriter file = new FileWriter("data\\students.txt", append);
            BufferedWriter writer = new BufferedWriter(file);
            for (Object student : objects)
                writer.write(student.toString() + "\n");
            writer.close();
        } catch (Exception ignored) {}
    }

    @Override
    public void convertToObject(String line) throws Exception {
        String[] object = line.split(", ");
        String status = object[0];
        int id = Integer.parseInt(object[1]);
        String name = object[2];
        Day dob = Day.parseDay(object[3]);
        String gender = object[4];
        String phone = object[5];
        String address = object[6];
        String email = object[7];
        String faculty = object[9];
        String major = object[10];
        String classroom = object[11];
        Student student = new Student(status, id, name, dob, gender, phone, address, email, faculty, major, classroom);
        students = Arrays.copyOf(students, students.length + 1);
        students[students.length - 1] = student;
    }
}
