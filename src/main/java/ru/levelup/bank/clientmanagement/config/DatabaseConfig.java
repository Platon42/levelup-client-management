package ru.levelup.bank.clientmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${database.password}")
    private String password;

    @Value("${database.url}")
    private String databaseUrl;

    @Bean
    @Primary
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(databaseUrl);
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
