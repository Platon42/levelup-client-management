package ru.levelup.bank.clientmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.levelup.bank.clientmanagement.dto.AccountDto;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.service.ClientManagementService;
import ru.levelup.bank.clientmanagement.utils.SchemaValidator;

import java.util.Set;

@RestController
public class ClientManagerController {

    @Autowired
    private ClientManagementService clientManagementService;

    @Autowired
    private SchemaValidator schemaValidator;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping("/client/create")
    public String createClient (@RequestBody String raw) throws JsonProcessingException {
        ValidationResult validationResult = schemaValidator.validateSchema(raw);
        Set<ValidationMessage> messages = validationResult.getValidationMessages();
        if (!messages.isEmpty()) {
             return objectMapper.writeValueAsString(messages);
        }
        ClientDto clientDto = objectMapper.readValue(raw, ClientDto.class);

        return clientManagementService.createClient(clientDto);
    }

    @GetMapping("/client/account_amount")
    public AccountDto getAccountInfo(@RequestParam(value = "account_no", defaultValue = "")
                                             String accountNo) {
        return clientManagementService.getAccountInfo(accountNo);
    }
}
