package com.springcore.lifecycle;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Employee implements InitializingBean, DisposableBean {
    private String name;

    public Employee() {
        System.out.println("1. Constructor Called ");
    }

    public void setName(String name){
        System.out.println("2. Setter Injection ");
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("3. afterPropertiesSet() Called ");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("5. destroy() Called ");
    }

    public void display() {
        System.out.println("4. Bean Ready ");
        System.out.println("Employee Name: " + name);
    }
}