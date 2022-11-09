package com.library.component;

import com.library.main.Library;
import com.library.util.Day;

import javax.swing.*;

public abstract class Person {
    private int id;
    private String name;
    private Day dob;
    private String gender;
    private String phone;
    private String address;
    private String email;

    public Person() {

    }

    public Person(int id, String name, Day dob, String gender, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Day getDob() {
        return dob;
    }

    public void setDob(Day dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void input() {
    }

    public void output() {

    }

    public abstract double calculatePrice(Day borrowDay, Day returnDay);
}
