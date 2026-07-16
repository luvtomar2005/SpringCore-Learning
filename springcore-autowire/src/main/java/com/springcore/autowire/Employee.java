package com.springcore.autowire;

public class Employee {

    private Address address;

    public Employee(Address address) {
        System.out.println("Constructor Called");
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "address=" + address +
                '}';
    }
}