package com.springjdbc;
import java.util.List;
import com.springjdbc.config.SpringConfig;
import com.springjdbc.dao.StudentDao;
import com.springjdbc.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);

        StudentDao studentDao = context.getBean(StudentDao.class);

        List<Student> students = studentDao.getAllStudents();

        for (Student student : students) {
            System.out.println(student);
        }
    }
}