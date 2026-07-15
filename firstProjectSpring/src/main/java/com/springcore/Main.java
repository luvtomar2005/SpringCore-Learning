package com.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");

        // This line triggers Spring to create the Student bean
        Student student = context.getBean("student", Student.class);

        // No need to call any method.
        // The constructor itself prints which constructor was called.
    }
}