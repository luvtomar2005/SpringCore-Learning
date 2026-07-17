package com.springcore.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private Address address;

    @Autowired
    public Student(@Qualifier("homeAddress") Address address) {
        this.address = address;
    }

    public void study() {
        System.out.println(address.getAddress());
    }
}