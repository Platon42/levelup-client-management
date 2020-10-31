package ru.levelup.bank.clientmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.bank.clientmanagement.dao.ClientEntity;

import java.util.Date;
import java.util.List;

public interface ClientRepo extends JpaRepository<ClientEntity,Integer> {
    List<ClientEntity> findByFirstNameAndSurNameAndMiddleNameAndBirthDate(String firstName, String surName, String middleName, Date birthDate);
}
