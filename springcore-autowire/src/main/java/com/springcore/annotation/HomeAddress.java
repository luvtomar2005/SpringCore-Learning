package com.springcore.annotation;

import org.springframework.stereotype.Component;

@Component
public class HomeAddress implements Address {

    @Override
    public String getLocation() {
        return "Mathura";
    }

    @Override
    public String toString() {
        return getLocation();
    }
}