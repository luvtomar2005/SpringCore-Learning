package com.springjdbc.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.springjdbc.dao.StudentDao;
import com.springjdbc.dao.StudentDaoImpl;
import java.beans.BeanProperty;

@Configuration
public class SpringConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/springjdbc");
        ds.setUsername("root");
        ds.setPassword("123456789"); // Replace with your MySQL password

        return ds;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }
    @Bean
    public StudentDao studentDao() {
        StudentDaoImpl dao = new StudentDaoImpl();
        dao.setJdbcTemplate(jdbcTemplate());
        return dao;
    }

}