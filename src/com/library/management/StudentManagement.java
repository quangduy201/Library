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

    @Override
    public void input() {

    }

    @Override
    public void output() {

    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void remove() {

    }

    public Student findStudent(int id) {
        return new Student();
    }

    public Student findStudent(String name) {
        return new Student();
    }

    @Override
    public void statistic() {

    }
}
