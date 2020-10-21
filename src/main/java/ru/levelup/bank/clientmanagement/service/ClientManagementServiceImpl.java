package ru.levelup.bank.clientmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.bank.clientmanagement.dao.AccountEntity;
import ru.levelup.bank.clientmanagement.dao.BalanceEntity;
import ru.levelup.bank.clientmanagement.dao.ClientEntity;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.repo.AccountRepo;
import ru.levelup.bank.clientmanagement.repo.BalanceRepo;
import ru.levelup.bank.clientmanagement.repo.ClientRepo;

import java.sql.Date;

@Service
public class ClientManagementServiceImpl implements ClientManagementService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    public String createClient(ClientDto clientDto) {

        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setAge(clientDto.getAge());
        clientEntity.setBirthDate(clientDto.getBirthDate());
        clientEntity.setFirstName(clientDto.getFirstName());
        clientEntity.setSurName(clientDto.getSurName());
        clientEntity.setMiddleName(clientDto.getMiddleName());
        clientEntity.setPassportNum(clientDto.getPassportNum());
        clientEntity.setSex(clientDto.getSex());
        clientEntity.setPhoneNumber(clientDto.getPhoneNumber());
        clientEntity.setBirthPlace(clientDto.getBirthPlace());
        clientRepo.save(clientEntity);

        clientDto.getAccountDtoList().forEach(accountDto -> {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setAccNumber(accountDto.getAccNumber());
            accountEntity.setCurrencyCode(accountDto.getAccCurrency().name());
            accountEntity.setClientByClientId(clientEntity);
            accountRepo.save(accountEntity);
        });
        clientDto.getAccountDtoList().forEach(accountDto -> {
            BalanceEntity balanceEntity = new BalanceEntity();
            balanceEntity.setBalanceBefore(0.0);
            balanceEntity.setBalanceAfter(0.0);
            balanceEntity.setCurrency(accountDto.getAccCurrency().name());
            //balanceEntity.set(accountEntity);
            balanceRepo.save(balanceEntity);
        });


        return "Success creating client with name" + clientDto.toString();
    }
}
