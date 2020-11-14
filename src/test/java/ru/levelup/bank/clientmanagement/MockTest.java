package ru.levelup.bank.clientmanagement;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.levelup.bank.clientmanagement.dao.AccountEntity;
import ru.levelup.bank.clientmanagement.dto.AccountDto;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.service.ClientManagementService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//специальная аннотация для мок объектов
public class MockTest {

    @Autowired
    //инжект mockMvc
    private MockMvc mockMvc;

    @MockBean
    //вместо @Autowired теперь инжект заглушки с этим же типом
    private ClientManagementService clientManagementService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
        //теперь работаем с заглушкой, а не с реальным http вызовом
    }

    @Test
    public void clientManagementServiceMock() throws Exception {
        AccountDto accountDto = new AccountDto();
        ClientDto clientDto = new ClientDto();
        Mockito.when(clientManagementService.getAccountInfo("1")).thenReturn(accountDto);
        Mockito.when(clientManagementService.createClient(clientDto)).thenReturn("dsadsas");
        //теперь работаем с заглушкой, а не с реальным jdbc коннектом
    }


}
