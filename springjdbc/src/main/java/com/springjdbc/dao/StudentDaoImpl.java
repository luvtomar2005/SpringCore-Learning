package com.springjdbc.dao;

import com.springjdbc.entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import com.springjdbc.rowmapper.StudentRowMapper;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public int insert(Student student) {

        String query = "INSERT INTO student(id, name, city) VALUES(?, ?, ?)";

        return this.jdbcTemplate.update(
                query,
                student.getId(),
                student.getName(),
                student.getCity()
        );
    }

    @Override
    public int update(Student student) {

        String query = "UPDATE student SET name = ?, city = ? WHERE id = ?";

        return this.jdbcTemplate.update(
                query,
                student.getName(),
                student.getCity(),
                student.getId()
        );
    }

    @Override
    public int delete(int studentId) {

        String query = "DELETE FROM student WHERE id = ?";

        return this.jdbcTemplate.update(query, studentId);
    }
    @Override
    public Student getStudent(int studentId) {

        String query = "SELECT * FROM student WHERE id = ?";

        return this.jdbcTemplate.queryForObject(
                query,
                new StudentRowMapper(),
                studentId
        );
    }
    @Override
    public List<Student> getAllStudents() {

        String query = "SELECT * FROM student";

        return this.jdbcTemplate.query(
                query,
                new StudentRowMapper()
        );

    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}