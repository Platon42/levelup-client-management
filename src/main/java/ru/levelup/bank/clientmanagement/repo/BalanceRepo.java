package ru.levelup.bank.clientmanagement.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.bank.clientmanagement.dao.BalanceEntity;

import java.util.List;

public interface BalanceRepo extends JpaRepository<BalanceEntity,Integer> {
    List<BalanceEntity> findBalanceEntitiesByAccountByAccountId_AccountId(
            int accountByAccountId_accountId);
    //BalanceEntity findByBalanceAfterAndBalanceBefore
      //      (double balanceAfter, double balanceBefore);
}
