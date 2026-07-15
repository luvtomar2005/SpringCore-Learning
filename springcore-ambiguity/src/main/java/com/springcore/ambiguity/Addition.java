package com.springcore.ambiguity;

public class Addition {

    private int a;
    private int b;

    // Constructor 1
    public Addition(int a, int b) {
        this.a = a;
        this.b = b;

        System.out.println("Integer Constructor Called");
    }

    // Constructor 2
    public Addition(double a, double b) {

        System.out.println("Double Constructor Called");
    }

    public void doSum() {
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("Sum = " + (a + b));
    }
}