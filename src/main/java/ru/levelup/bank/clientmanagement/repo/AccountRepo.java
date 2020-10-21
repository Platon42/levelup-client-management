package ru.levelup.bank.clientmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.bank.clientmanagement.dao.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
}
