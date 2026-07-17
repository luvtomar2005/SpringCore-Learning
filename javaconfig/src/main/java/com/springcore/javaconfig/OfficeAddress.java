package com.springcore.javaconfig;

import org.springframework.stereotype.Component;

@Component
public class OfficeAddress implements Address {

    @Override
    public String getAddress() {
        return "Noida";
    }
}