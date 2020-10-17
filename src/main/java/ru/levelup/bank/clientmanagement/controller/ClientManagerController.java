package ru.levelup.bank.clientmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
