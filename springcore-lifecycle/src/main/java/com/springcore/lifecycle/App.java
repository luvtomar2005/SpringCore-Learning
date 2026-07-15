package com.springcore.lifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        AbstractApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");

        Person person = context.getBean("person", Person.class);

        person.display();

        context.registerShutdownHook();
    }
}