package com.library.management;

import com.library.component.*;
import com.library.util.Day;

public class BorrowAndReturn {
    private static Bill bill[];

    public BorrowAndReturn() {
    }

    public void borrowBook(String category, String person){
        if (category.equalsIgnoreCase("Education")){
            EducationBook eduBook = new EducationBook();
            boolean check = false;
            System.out.println("Enter information Education Book:");
            eduBook.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.EducationBook.length; i++){
                if (eduBook.getId() == management.EducationBook[i].getId()){
                    management.EducationBook[i].update("borrow");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
        if (category.equalsIgnoreCase("Reference")){
            ReferenceBook refBook = new ReferenceBook();
            boolean check = false;
            System.out.println("Enter information ReferenceBook:");
            refBook.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.ReferenceBook.length; i++){
                if (refBook.getId() == management.ReferenceBook[i].getId()){
                    management.ReferenceBook[i].update("borrow");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
        if (category.equalsIgnoreCase("Dictionmary")){
            Dictionary dictionary = new Dictionary();
            boolean check = false;
            System.out.println("Enter information Dictionary:");
            dictionary.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.Dictionary.length; i++){
                if (dictionary.getId() == management.Dictionary[i].getId()){
                    management.Dictionary[i].update("borrow");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
        if (person.equalsIgnoreCase("Student")){
            Student student = new Student();
            System.out.println("Enter information student:");
            student.input();
            //em chua hieu se lam gi them phan chon nguoi muon nen se update sau
        }
        if (person.equalsIgnoreCase("Employee")){
            Employee employee = new Employee();
            System.out.println("Enter information employee:");
            employee.input();
            //em chua hieu se lam gi them phan chon nguoi muon nen se update sau
        }
    }

    public void returnBook(String category){
        if (category.equalsIgnoreCase("Education")){
            EducationBook eduBook = new EducationBook();
            boolean check =false;
            System.out.println("Enter information Education Book:");
            eduBook.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.EducationBook.length; i++){
                if (eduBook.getId() == management.EducationBook[i].getId()){
                    management.EducationBook[i].update("return");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
        if (category.equalsIgnoreCase("Reference")){
            ReferenceBook refBook = new ReferenceBook();
            boolean check =false;
            System.out.println("Enter information ReferenceBook:");
            refBook.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.ReferenceBook.length; i++){
                if (refBook.getId() == management.ReferenceBook[i].getId()){
                    management.ReferenceBook[i].update("return");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
        if (category.equalsIgnoreCase("Dictionmary")){
            Dictionary dictionary = new Dictionary();
            boolean check =false;
            System.out.println("Enter information Dictionary:");
            dictionary.input();
            BookManagement management = new BookManagement();
            for(int i=0; i<management.Dictionary.length; i++){
                if (dictionary.getId() == management.Dictionary[i].getId()){
                    management.Dictionary[i].update("return");
                    check = true;
                }
            }
            if (!check){
                System.out.println("Don't find book!");
            }
        }
    }
    public Bill findBill(int studentID){
        for (int i=0; i<bill.length; i++){
            if (bill[i].getStudent().getId() == studentID){
                return bill[i];
            }
        }
        return null;
    }

    public Bill[] findBill(Day low, Day high){
        Bill listBill[];
        int count=0, j=0;
        int time = Day.calculateDays(low, high);
        /*
        * nếu khoảng cách ngày mượn đên ngày low <= khoảng cách low đến high thì nhận
        */
        for (int i=0; i<bill.length; i++){
            if (time >= Day.calculateDays(low, bill[i].getBorrowDay())){
                count++;
            }
        }
        listBill=new Bill[count];
        for (int i=0; i<bill.length; i++){
            if (time >= Day.calculateDays(low, bill[i].getBorrowDay())){
                listBill[j] =  new Bill();
                listBill[j++] = bill[i];
            }
        }
        return listBill;
    }

    public void statistic(){

    }
}
