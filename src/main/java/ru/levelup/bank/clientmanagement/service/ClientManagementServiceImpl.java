package ru.levelup.bank.clientmanagement.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.bank.clientmanagement.dto.ClientDto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
public class ClientManagementServiceImpl implements ClientManagementService {

    @Autowired
    private ObjectMapper objectMapper;
    private static final File clientsData = new File("C:\\Users\\Platon\\IdeaProjects\\client-management\\data\\clients.dat");

    public String createClient(ClientDto clientDto) {
        try {
            //objectMapper.writeValue(clientsData, clientDto);
            //File clientsData = new File("C:\\Users\\Platon\\IdeaProjects\\client-management\\data\\clients.dat");
            //FileWriter fileWriter = new FileWriter(clientsData, Boolean.TRUE);
            String client = objectMapper.writeValueAsString(clientDto);
            Files.writeString(Path.of("C:\\Users\\Platon\\IdeaProjects\\client-management\\data\\clients.dat"),client, StandardOpenOption.APPEND);
            //fileWriter.write(client);
            return "Success creating client with name" + clientDto.toString();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return "Unable to create client with name" + clientDto.toString();
        }
    }
}
