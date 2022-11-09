package com.library.component;

import com.library.util.Day;

import java.util.Scanner;

public class Student extends Person {
    public static String school = "Đại học Sài Gòn";
    public String faculty;
    public String major;
    public String classroom;

    public Student() {

    }

    public Student(String faculty, String major, String classroom) {
        this.faculty = faculty;
        this.major = major;
        this.classroom = classroom;
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

//    @Override
//    public void input(){
//        Scanner sc = new Scanner(System.in);
//        super.input();
//        String faculty,major,classroom;
//        do {
//            System.out.println("Enter faculty: ");
//            faculty = sc.nextLine();
//        }while(faculty.isEmpty());
//        setFaculty(faculty);
//        do {
//            System.out.println("Enter major: ");
//            major = sc.nextLine();
//        }while(major.isEmpty());
//        setMajor(major);
//        do {
//            System.out.println("Enter classroom: ");
//            classroom = sc.nextLine();
//        }while(major.isEmpty());
//        setClassroom(classroom);
//    }
//    @Override
//    public void output(){
//        super.output();
//        System.out.println("Faculty: "+faculty+"\t Major: "+major+"\t Classroom: "+classroom);
//    }
//        public calculatePrice(Day borrowDay,Day returnDay){
//        double
//    }

    @Override
    public double calculatePrice(Day borrowDay, Day returnDay) {
        return 0;
    }
}
