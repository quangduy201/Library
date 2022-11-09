package com.library.component;

import com.library.util.Day;

import java.net.IDN;
import java.util.Scanner;

public abstract class Person {
    private int ID;
    private String name;
    private Day dob;
    private String gender;
    private String phone;
    private String address;
    private String email;
    public Person(){

    }
    public Person(int ID,String name,Day dob,String gender,String phone,String address,String email){
        this.ID=ID;
        this.name=name;
        this.dob=dob;
        this.gender=gender;
        this.phone=phone;
        this.address=address;
        this.email=email;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

//    public void input(){
//        Scanner sc = new Scanner(System.in);
//        String ID,Name,Dob,gender,phone,address,email;
//        do {
//            System.out.println("Enter ID: ");
//            ID = sc.nextLine();
//        }while(ID.length() != 8);
//        setID(ID);
//
//        do {
//            System.out.println("Enter Name: ");
//            Name = sc.nextLine();
//        }while(Name.isEmpty());
//        setName(Name);
//
//        do {
//            System.out.println("Enter Dob: ");
//            Dob = sc.nextLine();
//        }while(Dob != "a");
//        setDob(Dob);
//
//        do {
//            System.out.println("Enter gender: ");
//            gender = sc.nextLine();
//        }while(gender != "Nam"&& gender != "nam" && gender != "Nữ" && gender != "nữ");
//        setGender(gender);
//
//        do {
//            System.out.println("Enter phone: ");
//            phone = sc.nextLine();
//        }while(phone.length() != 10);
//        setPhone(phone);
//
//        do {
//            System.out.println("Enter address: ");
//            address = sc.nextLine();
//        }while(address.isEmpty());
//        setAddress(address);
//
//        do {
//            System.out.println("Enter email: ");
//            email = sc.nextLine();
//        }while(email.isEmpty());
//        setEmail(email);
//    }
//    public  void output(){
//        System.out.println("ID: "+ID+"\t Name: "+name+"\t Dob: "+dob+"\t Gender: "+gender+"\t Phone: "+phone+"\t address: "+address+"\t Email: "+email);
//    }
//    public abstract calculatePrice(Day borrowDay,Day returnDay){
//
//    }
}
