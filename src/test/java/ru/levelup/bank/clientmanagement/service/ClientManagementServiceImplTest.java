package ru.levelup.bank.clientmanagement.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.bank.clientmanagement.dao.AccountEntity;
import ru.levelup.bank.clientmanagement.dao.BalanceEntity;
import ru.levelup.bank.clientmanagement.dao.ClientEntity;
import ru.levelup.bank.clientmanagement.dto.CurrencyCode;
import ru.levelup.bank.clientmanagement.repo.AccountRepo;
import ru.levelup.bank.clientmanagement.repo.BalanceRepo;
import ru.levelup.bank.clientmanagement.repo.ClientRepo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@Transactional
//тестирование с использованием контроля транзакций
class ClientManagementServiceImplTest {

    @Autowired
    private AccountRepo accountRepo;
    //инжект реальных объектов
    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private BalanceRepo balanceRepo;

    //подготовка данных
    private static final ClientEntity clientEntity = new ClientEntity();
    private static final ArrayList<AccountEntity> accountEntities = new ArrayList<>();
    private static final ArrayList<BalanceEntity> balanceEntities = new ArrayList<>();
    private static final Date birthDate = new Date();

    private static String expectedAccountNumber = "01";

    @BeforeAll
    //подготовка данных
    static void fillClientAccountAndBalance(){

        clientEntity.setFirstName("John");
        clientEntity.setSurName("Test");
        clientEntity.setMiddleName("Test");
        clientEntity.setPassportNum("4511447878");
        clientEntity.setBirthDate(birthDate);

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAccNumber(expectedAccountNumber);
        accountEntity.setCurrencyCode(CurrencyCode.BRL.name());
        accountEntities.add(accountEntity);

        BalanceEntity balanceEntity = new BalanceEntity();
        balanceEntity.setBalanceAfter(-1.0);
        balanceEntity.setBalanceBefore(-1.0);

        balanceEntities.add(balanceEntity);
    }
//Ivan,Venus,W,2001-08-01
    @Test
    @Rollback
        //откатить транзакцию после теста
    void createClient() {

        clientRepo.save(clientEntity);
        clientEntity.setAccountsByClientId(accountEntities);
        accountRepo.saveAll(accountEntities);
        List<BalanceEntity> actualBalanceEntities =
                balanceRepo.saveAll(ClientManagementServiceImplTest.balanceEntities);

        ClientEntity actualClientEntity = clientRepo
            .findByFirstNameAndSurNameAndMiddleNameAndBirthDate
                    ("John",
                    "Test",
                    "Test",
                            birthDate);
        assertThat(actualClientEntity, equalTo(clientEntity));

        //проверяем счет
        AccountEntity actualAccountEntityByAccNumber = accountRepo.
                findAccountEntityByAccNumber(expectedAccountNumber);
        assertThat(actualAccountEntityByAccNumber.getAccNumber(), equalTo(expectedAccountNumber));
        //проверяем баланс
        assertThat(actualBalanceEntities, equalTo(balanceEntities));
        //проверка ожидаемого значения от полученного
    }

    @Test
    void getAccountInfo() {

    }
}