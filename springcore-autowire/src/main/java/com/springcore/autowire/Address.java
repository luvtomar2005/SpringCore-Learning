package com.springcore.autowire;

public class Address {

    private String city;
    private String state;

    public Address() {
    }

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        System.out.println("Setting city...");
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        System.out.println("Setting state...");
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