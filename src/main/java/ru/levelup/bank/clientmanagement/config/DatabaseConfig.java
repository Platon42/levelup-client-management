package ru.levelup.bank.clientmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://95.163.214.27:5432/bank_db");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("NdWRFmEV9pNhbgwQseZ3jKAPfXf4pMjwPD6f");
        return dataSourceBuilder.build();
    }
}
