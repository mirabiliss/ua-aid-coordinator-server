package com.aid.coordinator.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DatabaseConnectionTest(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void testConnection() {
    String query = "SELECT * from user";
    String result = jdbcTemplate.queryForObject(query, String.class);
    if (result != null) {
      System.out.println("Database connection test passed!");
    } else {
      System.out.println("Database connection test failed!");
    }
  }
}
