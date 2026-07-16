package com.springcore.annotation;

import org.springframework.stereotype.Component;

@Component
public class OfficeAddress implements Address {

    @Override
    public String getLocation() {
        return "Noida";
    }

    @Override
    public String toString() {
        return getLocation();
    }
}