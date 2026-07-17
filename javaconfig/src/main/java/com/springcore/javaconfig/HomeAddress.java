package com.springcore.javaconfig;

import org.springframework.stereotype.Component;

@Component
public class HomeAddress implements Address {

    @Override
    public String getAddress() {
        return "Mathura";
    }
}