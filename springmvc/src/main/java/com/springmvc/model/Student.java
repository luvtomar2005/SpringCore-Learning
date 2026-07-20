package com.springmvc.model;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class Student {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    @Min(value = 18, message = "Age must be at least 18")
    private int age;
    @NotBlank(message = "Course cannot be empty")
    private String course;

    public Student () {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email ) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
}

