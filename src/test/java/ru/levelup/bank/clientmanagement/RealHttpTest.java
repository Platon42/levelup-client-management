package ru.levelup.bank.clientmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//тест Spring Boot, Spring контекст будет загружен
public class RealHttpTest {

    @LocalServerPort
    private int port;
    //Инициализация порта

    @Autowired
    private TestRestTemplate restTemplate;
    //Специальный бин для HTTP запросов в тестовой среде


    @Test
    //Junit тест
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate
                .getForObject("http://localhost:" + port + "/hello",
                String.class)).isEqualTo("Hello, World");
        //проверка, что ожидаемый ответ от сервиса Hello, World совпадает с фактическим
    }
}
