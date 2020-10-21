package ru.levelup.bank.clientmanagement.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.bank.clientmanagement.dao.BalanceEntity;

public interface BalanceRepo extends JpaRepository<BalanceEntity,Integer> {
}
