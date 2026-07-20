
package com.springorm.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;
import com.springorm.dao.StudentDao;
import com.springorm.entities.Student;
public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        StudentDao studentDao = context.getBean(StudentDao.class);

//// Student 1
//        Student s1 = new Student();
//        s1.setStudentId(101);
//        s1.setStudentName("Luv");
//        s1.setStudentCity("Mathura");
//        studentDao.saveStudent(s1);
//
//// Student 2
//        Student s2 = new Student();
//        s2.setStudentId(102);
//        s2.setStudentName("Rahul");
//        s2.setStudentCity("Delhi");
//        studentDao.saveStudent(s2);

// Student 3
        Student s3 = new Student();
        s3.setStudentId(111);
        s3.setStudentName("Priya");
        s3.setStudentCity("Lucknow");
        studentDao.saveStudent(s3);

// Student 4
        Student s4 = new Student();
        s4.setStudentId(139);
        s4.setStudentName("Aman");
        s4.setStudentCity("Agra");
        studentDao.saveStudent(s4);

        System.out.println("All students inserted successfully!");
        List<Student> students = studentDao.getAllStudents();

        System.out.println("---------ALl Students------");
        for(Student student : students ){
            System.out.println(student);
        }

        ((ClassPathXmlApplicationContext) context).close();




    }
}

