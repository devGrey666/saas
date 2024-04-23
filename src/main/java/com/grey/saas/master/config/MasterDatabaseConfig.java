package com.grey.saas.master.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Setter
@Configuration
@EnableTransactionManagement

public class MasterDatabaseConfig {

    @Autowired
    private MasterDatabaseConfigProperties masterDatabaseConfigProperties;

    @Bean(name = "masterDataSource")
    @LiquibaseDataSource
    @ConfigurationProperties("spring.datasource.hikari")
    public DataSource masterDataSource() {


        HikariDataSource ds = new HikariDataSource();

        ds.setUsername(masterDatabaseConfigProperties.getUsername());
        ds.setPassword(masterDatabaseConfigProperties.getPassword());
        ds.setJdbcUrl(masterDatabaseConfigProperties.getUrl());
        ds.setDriverClassName(masterDatabaseConfigProperties.getDriverClassName());
        ds.setPoolName(masterDatabaseConfigProperties.getPoolName());

        // HikariCP settings
        // Maximum number of actual connection in the pool
        ds.setMaximumPoolSize(masterDatabaseConfigProperties.getMaxPoolSize());

        // Minimum number of idle connections in the pool
        ds.setMinimumIdle(masterDatabaseConfigProperties.getMinIdle());

        // Maximum waiting time for a connection from the pool
        ds.setConnectionTimeout(masterDatabaseConfigProperties.getConnectionTimeout());

        // Maximum time that a connection is allowed to sit idle in the pool
        ds.setIdleTimeout(masterDatabaseConfigProperties.getIdleTimeout());
//        LOG.info("Setup of masterDataSource succeeded.");
        return ds;
    }

}
