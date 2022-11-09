package com.library.component;

import com.library.util.Day;

public class Student extends Person {
    private static String school;
    private String faculty;
    private String major;
    private String classroom;

    public Student() {

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
    }

    @Override
    public void output() {
        super.output();
    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay) {
        return 0;
    }
}
