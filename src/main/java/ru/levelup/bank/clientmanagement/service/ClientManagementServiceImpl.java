package ru.levelup.bank.clientmanagement.service;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.bank.clientmanagement.dao.AccountEntity;
import ru.levelup.bank.clientmanagement.dao.BalanceEntity;
import ru.levelup.bank.clientmanagement.dao.ClientEntity;
import ru.levelup.bank.clientmanagement.dto.AccountDto;
import ru.levelup.bank.clientmanagement.dto.ClientDto;
import ru.levelup.bank.clientmanagement.dto.CurrencyCode;
import ru.levelup.bank.clientmanagement.repo.AccountRepo;
import ru.levelup.bank.clientmanagement.repo.BalanceRepo;
import ru.levelup.bank.clientmanagement.repo.ClientRepo;

import java.util.*;


@Service
public class ClientManagementServiceImpl implements ClientManagementService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    public String createClient(ClientDto clientDto) {

        if (isClientPresent(clientDto)) return "Cannot create client with name "
                + clientDto.toString() + " client present in database!";

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

        List<AccountEntity> accountEntityList = new ArrayList<>();
        List<BalanceEntity> balanceEntityList = new ArrayList<>();

        clientDto.getAccountDtoList().forEach(accountDto -> {
            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setAccNumber(accountDto.getAccNumber());
            accountEntity.setCurrencyCode(accountDto.getAccCurrency().name());
            accountEntity.setClientByClientId(clientEntity);
            accountEntityList.add(accountEntity);

            BalanceEntity balanceEntity = new BalanceEntity();
            balanceEntity.setCurrency(accountDto.getAccCurrency().name());
            balanceEntity.setBalanceAfter(0.0);
            balanceEntity.setAccountByAccountId(accountEntity);
            balanceEntityList.add(balanceEntity);

        });
        clientEntity.setAccountsByClientId(accountEntityList);
        accountRepo.saveAll(accountEntityList);
        balanceRepo.saveAll(balanceEntityList);

        return "Success creating client with name " + clientDto.toString();
    }

    private boolean isClientPresent(ClientDto clientDto) {

        String firstName = clientDto.getFirstName();
        String surName = clientDto.getSurName();
        String middleName = clientDto.getMiddleName();
        Date birthDate = clientDto.getBirthDate();

        var clientEntities =
                clientRepo.findByFirstNameAndSurNameAndMiddleNameAndBirthDate(firstName, surName, middleName, birthDate);
        return !clientEntities.isEmpty();

    }

    public AccountDto getAccountInfo(String accountNo) {
        AccountEntity accountEntityByAccNumber = accountRepo.findAccountEntityByAccNumber(accountNo);
        AccountDto accountDto = new AccountDto();

        if (accountEntityByAccNumber != null) {
            accountDto.setAccCurrency(CurrencyCode.valueOf(accountEntityByAccNumber.getCurrencyCode()));
        } else {
            accountDto.setAccNumber("Cannot find account with account number " + accountNo);
        }

        return accountDto;
    }
}