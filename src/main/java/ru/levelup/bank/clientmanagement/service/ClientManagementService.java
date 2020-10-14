package ru.levelup.bank.clientmanagement.service;

import ru.levelup.bank.clientmanagement.dto.ClientDto;

public interface ClientManagementService {
    String createClient(ClientDto clientDto);
}
