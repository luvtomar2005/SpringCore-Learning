package com.springorm.dao;

import com.springorm.entities.Student;
import java.util.List;
public interface StudentDao {

    void saveStudent(Student student);

    Student getStudentById(int id);

    void updateStudent(Student student);

    void deleteStudent(int id);
    List<Student> getAllStudents();

}