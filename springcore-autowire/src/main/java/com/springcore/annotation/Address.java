package com.springcore.annotation;

public interface Address {

    String getLocation();

}


// Without component spring will ignore this -> java class -> normal object -> bean not created
// with component spring will create bean by itself...
