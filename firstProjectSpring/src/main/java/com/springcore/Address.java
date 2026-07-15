package com.springcore;

public class Address {

    private String city;
    private String state;

    public Address(String city, String state) {

        System.out.println("Address Constructor Called");

        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {

        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}