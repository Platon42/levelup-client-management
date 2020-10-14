package ru.levelup.bank.clientmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.service.ClientManagementService;

@RestController
public class ClientManagerController {

    @Autowired
    private ClientManagementService clientManagementService;

    @PostMapping("/client/create")
    public String createClient (@RequestBody ClientDto clientDto) {
        return clientManagementService.createClient(clientDto);
    }
}
