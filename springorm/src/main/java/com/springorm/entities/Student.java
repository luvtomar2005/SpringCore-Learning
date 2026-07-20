
package com.springorm.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private int studentId;
    @Column(name = "student_name")
    private String studentName;

    @Column(name = "student_city")
    private String studentCity;

    public Student() {

    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentCity='" + studentCity + '\'' +
                '}';
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Student(int studentId , String studentName , String studentCity){
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCity = studentCity;


    }
}
