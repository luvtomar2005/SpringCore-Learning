package com.springcore.lifecycle;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Person {
    private String name;
    public Person() {
        System.out.println("1. Constructor Called ; ");
    }
    public void setName(String name) {
        System.out.println("2 . Setter Injection : ");
        this.name = name;
    }
    @PostConstruct
    public void init() {
        System.out.println("3 . @PostConstruct Method Called : ");
    }
    public void display() {
        System.out.println("4 . Bean ready to use ");
        System.out.println("Person Name : " + name);
    }
    @PreDestroy
    public void destroy() {
        System.out.println("5 . @PreDestroy Method Called : ");
    }
}