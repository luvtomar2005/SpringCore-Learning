//package com.springcore.annotation;
package com.springcore.annotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class Employee {
    @Autowired
    @Qualifier("homeAddress")
    private Address address;

    @Override
    public String toString() {
        return "Employee{" +
                "address=" + address +
                '}';
    }



}