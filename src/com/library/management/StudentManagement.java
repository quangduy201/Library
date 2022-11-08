package com.library.management;

import com.library.component.Student;

public class StudentManagement implements Management {
    private Student[] students;

    public StudentManagement() {

    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    public void input() {

    }

    public void output() {

    }

    public void add() {

    }

    public void edit() {

    }

    public void remove() {

    }

    public Student findStudent(int id) {
        return new Student();
    }

    public Student findStudent(String name) {
        return new Student();
    }
}
