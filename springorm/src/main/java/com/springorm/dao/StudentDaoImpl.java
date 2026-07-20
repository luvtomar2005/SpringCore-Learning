package com.springorm.dao;

import com.springorm.entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Student student) {

        entityManager.persist(student);

    }
    // updating the table
    @Override
    @Transactional
    public void updateStudent(Student student){
        entityManager.merge(student);
    }
    @Override
    @Transactional(readOnly = true)
    public Student getStudentById(int id) {

        return entityManager.find(Student.class, id);

    }

    @Override
    public void deleteStudent(int id ){
        Student student = entityManager.find(Student.class , id);

        if(student != null){
            entityManager.remove(student);
        }
    }
    @Override
    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {

        String jpql = "FROM Student";

        return entityManager
                .createQuery(jpql, Student.class)
                .getResultList();
    }

}
