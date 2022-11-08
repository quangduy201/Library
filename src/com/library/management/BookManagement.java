package com.library.management;

import com.library.component.Dictionary;
import com.library.component.EducationBook;
import com.library.component.ReferenceBook;

public class BookManagement implements Management {
    private EducationBook[] education;
    private ReferenceBook[] reference;
    private Dictionary[] dictionary;

    public BookManagement() {

    }

    public EducationBook[] getEducation() {
        return education;
    }

    public void setEducation(EducationBook[] education) {
        this.education = education;
    }

    public ReferenceBook[] getReference() {
        return reference;
    }

    public void setReference(ReferenceBook[] reference) {
        this.reference = reference;
    }

    public Dictionary[] getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary[] dictionary) {
        this.dictionary = dictionary;
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

    public EducationBook findEducationBook(int id) {
        return new EducationBook();
    }

    public EducationBook findEducationBook(String name) {
        return new EducationBook();
    }

    public EducationBook[] findEducationBook(double low, double high) {
        return new EducationBook[0];
    }

    public ReferenceBook findReferenceBook(int id) {
        return new ReferenceBook();
    }

    public ReferenceBook findReferenceBook(String name) {
        return new ReferenceBook();
    }

    public ReferenceBook[] findReferenceBook(double low, double high) {
        return new ReferenceBook[0];
    }

    public Dictionary findDictionary(int id) {
        return new Dictionary();
    }

    public Dictionary findDictionary(String name) {
        return new Dictionary();
    }

    public Dictionary[] findDictionary(double low, double high) {
        return new Dictionary[0];
    }

    public void statistic() {

    }
}
