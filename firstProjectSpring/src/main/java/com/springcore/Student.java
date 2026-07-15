package com.springcore;

public class Student {

    private int id;
    private String name;

    public Student(int id, String name) {

        System.out.println("Constructor Called");

        this.id = id;
        this.name = name;
    }

    public void display() {
        System.out.println("Id : " + id);
        System.out.println("Name : " + name);
    }
}