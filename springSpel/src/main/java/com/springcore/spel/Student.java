package com.springcore.spel;

import java.util.List;
import java.util.Map;

public class Student {

    // String
    private String name;

    // Integer
    private int marks;

    // Boolean
    private boolean passed;

    // Double
    private double pi;

    // Double
    private double randomNumber;

    // String
    private String result;

    // List
    private List<Integer> numbers;

    // Map
    private Map<String, Integer> grades;

    // String
    private String osName;

    public Student() {
    }

    // ---------------- Name ----------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // ---------------- Marks ----------------

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // ---------------- Passed ----------------

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    // ---------------- PI ----------------

    public double getPi() {
        return pi;
    }

    public void setPi(double pi) {
        this.pi = pi;
    }

    // ---------------- Random ----------------

    public double getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(double randomNumber) {
        this.randomNumber = randomNumber;
    }

    // ---------------- Result ----------------

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    // ---------------- Numbers ----------------

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // ---------------- Grades ----------------

    public Map<String, Integer> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }

    // ---------------- OS ----------------

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "\nname='" + name + '\'' +
                ",\nmarks=" + marks +
                ",\npassed=" + passed +
                ",\npi=" + pi +
                ",\nrandomNumber=" + randomNumber +
                ",\nresult='" + result + '\'' +
                ",\nnumbers=" + numbers +
                ",\ngrades=" + grades +
                ",\nosName='" + osName + '\'' +
                "\n}";
    }
}