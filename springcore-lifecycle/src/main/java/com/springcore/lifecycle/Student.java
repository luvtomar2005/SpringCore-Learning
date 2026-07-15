package com.springcore.lifecycle;

public class Student {

    private String name;

    public Student() {
        System.out.println("1. Constructor Called");
    }

    public void setName(String name) {
        System.out.println("2. Setter Injection");
        this.name = name;
    }

    public void init() {
        System.out.println("3. Initialization Method Called");
    }

    public void destroy() {
        System.out.println("5. Destroy Method Called");
    }

    public void display() {
        System.out.println("4. Bean is Ready to Use");
        System.out.println("Student Name : " + name);
    }
}