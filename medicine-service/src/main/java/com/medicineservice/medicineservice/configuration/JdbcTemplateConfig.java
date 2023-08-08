package com.medicineservice.medicineservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver"); // Use appropriate driver class
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); // Replace with your DB URL
        dataSource.setUsername("CAMILO");
        dataSource.setPassword("1234");
        return dataSource;
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource()); // You also need to define the dataSource bean
    }
}
