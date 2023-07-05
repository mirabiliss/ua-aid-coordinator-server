package com.aid.coordinator.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@Slf4j
@Component
public class DatabaseConnectionTest {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DatabaseConnectionTest(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Test
  public void testConnection() {
    String query = "SELECT * from user";
    String result = jdbcTemplate.queryForObject(query, String.class);
    if (result != null) {
      log.debug("Database connection test passed!");
    } else {
      log.debug("Database connection test failed!");
    }
    assertNotNull(result);
  }
}
