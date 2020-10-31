package ru.levelup.bank.clientmanagement.service;

import ru.levelup.bank.clientmanagement.dto.AccountDto;
import ru.levelup.bank.clientmanagement.dto.ClientDto;

public interface ClientManagementService {
    String createClient(ClientDto clientDto);
    AccountDto getAccountInfo(String accountNo);
}
