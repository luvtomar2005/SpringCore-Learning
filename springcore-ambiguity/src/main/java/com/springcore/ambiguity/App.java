package com.springcore.ambiguity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");

        Addition addition =
                context.getBean("addition", Addition.class);

        addition.doSum();
    }
}