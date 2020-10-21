package ru.levelup.bank.clientmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.bank.clientmanagement.dao.ClientEntity;

public interface ClientRepo extends JpaRepository<ClientEntity,Integer> {
}
