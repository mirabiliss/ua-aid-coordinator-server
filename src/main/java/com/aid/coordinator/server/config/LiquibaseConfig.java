package com.aid.coordinator.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import liquibase.integration.spring.SpringLiquibase;

@Configuration
public class LiquibaseConfig {

  private final Environment env;
  private final DataSource dataSource;

  @Autowired
  public LiquibaseConfig(Environment env, DataSource dataSource) {
    this.env = env;
    this.dataSource = dataSource;
  }

  @Bean
  public SpringLiquibase liquibase() {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setDataSource(dataSource);
    liquibase.setChangeLog("classpath:db/changelog/db-changelog.xml");
    liquibase.setContexts(env.getProperty("spring.profiles.active"));
    return liquibase;
  }
}
