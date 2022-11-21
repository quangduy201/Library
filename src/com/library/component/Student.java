package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Student extends Person {
    private static String school = "Đại học Sài Gòn";
    private String faculty;
    private String major;
    private String classroom;

    public Student() {
        super();
        this.faculty = "";
        this.major = "";
        this.classroom = "";
    }

    public Student(int id, String name, Day dob, String gender, String phone, String address, String email, String faculty, String major, String classroom) {
        super(id, name, dob, gender, phone, address, email);
        this.faculty = faculty;
        this.major = major;
        this.classroom = classroom;
    }

    public static String getSchool() {
        return school;
    }

    public static void setSchool(String school) {
        Student.school = school;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    @Override
    public void input() {
        super.input();
        faculty = inputFaculty("Enter faculty: ");
        major = inputMajor("Enter major: ");
        classroom = inputClassroom("Enter classroom: ");
    }

    public static String inputFaculty(String message) {
        Scanner sc = new Scanner(System.in);
        String faculty;
        do {
            System.out.print(message);
            faculty = sc.nextLine();
        } while (faculty.isBlank());
        return faculty;
    }

    public static String inputMajor(String message) {
        Scanner sc = new Scanner(System.in);
        String major;
        do {
            System.out.print(message);
            major = sc.nextLine();
        } while (major.isBlank());
        return major;
    }

    public static String inputClassroom(String message) {
        Scanner sc = new Scanner(System.in);
        String classroom;
        do {
            System.out.print(message);
            classroom = sc.nextLine();
        } while (classroom.isBlank());
        return classroom;
    }

    @Override
    public void output() {
        super.output();
        System.out.printf("%17s  |%25s  |%25s  |  %s\n", school, faculty, major, classroom);
    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay, int numOfBooks) {
        /*
        Tính số tiền dựa theo số ngày:
        - Trong 3 ngày đầu tiên, mỗi ngày giá tiền mỗi cuốn sách là 5000.
        - Trong 3 ngày tiếp theo, mỗi ngày giá tiền mỗi cuốn sách là 6000.
        - Những ngày sau đó mỗi ngày giá tiền mỗi cuốn sách là 10000.
         */
        int days = Day.calculateDays(borrowDay, returnDay);
        double money = 0.0;
        if (days - 3 < 0)
            money += days * 5000 * numOfBooks;
        else {
            money += 3 * 5000 * numOfBooks;
            days -= 3;
            if (days - 3 < 0)
                money += days * 6000 * numOfBooks;
            else {
                money += 3 * 6000 * numOfBooks;
                days -= 3;
                money += days * 10000 * numOfBooks;
            }
        }
        return money;
    }

    @Override
    public String toString() {
        return super.toString() +
                school + ", " +
                faculty + ", " +
                major + ", " +
                classroom;
    }
}
