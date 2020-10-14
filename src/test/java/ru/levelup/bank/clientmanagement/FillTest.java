package ru.levelup.bank.clientmanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.levelup.bank.clientmanagement.dto.AccountDto;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.dto.CurrencyCode;

import java.util.ArrayList;

public class FillTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName("John");
        clientDto.setSurName("Doe6");
        ArrayList<AccountDto> accountDtos = new ArrayList<>();
        AccountDto accountDto1 = new AccountDto();
        accountDto1.setAccNumber("01");
        accountDto1.setAccBalance(10.0);
        accountDto1.setAccCurrency(CurrencyCode.BRL);
        AccountDto accountDto2 = new AccountDto();
        accountDto2.setAccNumber("02");
        accountDto2.setAccBalance(20.0);
        accountDto2.setAccCurrency(CurrencyCode.UAH);
        accountDtos.add(accountDto1);
        accountDtos.add(accountDto2);
        clientDto.setAccountDtoList(accountDtos);
        String s = objectMapper.writeValueAsString(clientDto);
        System.out.println(s);
    }
}
