package com.library.component;

import java.util.Scanner;

public class Student extends Person {
    public static String school;
    public String faculty;
    public String major;
    public String classic;
    public Student(){

    }
    public Student(String faculty,String major,String classic){
        this.faculty=faculty;
        this.major=major;
        this.classic=classic;
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

    public String getClassic() {
        return classic;
    }

    public void setClassic(String classic) {
        this.classic = classic;
    }
//    @Override
//    public void input(){
//        Scanner sc = new Scanner(System.in);
//        super.input();
//        String faculty,major,classic;
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
//            System.out.println("Enter classic: ");
//            classic = sc.nextLine();
//        }while(major.isEmpty());
//        setClassic(classic);
//    }
//    @Override
//    public void output(){
//        super.output();
//        System.out.println("Faculty: "+faculty+"\t Major: "+major+"\t Classic: "+classic);
//    }
//        public calculatePrice(Day borrowDay,Day returnDay){
//        double
//    }
}
