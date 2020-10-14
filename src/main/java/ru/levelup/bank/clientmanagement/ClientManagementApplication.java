package ru.levelup.bank.clientmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ClientManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientManagementApplication.class, args);
    }

}
