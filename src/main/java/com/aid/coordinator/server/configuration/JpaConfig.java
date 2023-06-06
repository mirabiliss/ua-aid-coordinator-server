//package com.aid.coordinator.server.configuration;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//@Configuration
//public class JpaConfig {
//
//  @Value("${spring.jpa.database-platform}")
//  private String databasePlatform;
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//    em.setDataSource(dataSource);
//    em.setPackagesToScan("com.aid.coordinator.server.entity");
//
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setDatabasePlatform(databasePlatform);
//    em.setJpaVendorAdapter(vendorAdapter);
//
//    return em;
//  }
//
//  @Bean
//  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager.setEntityManagerFactory(entityManagerFactory);
//    return transactionManager;
//  }
//}
