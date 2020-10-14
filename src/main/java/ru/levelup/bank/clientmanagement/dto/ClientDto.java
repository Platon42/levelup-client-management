package ru.levelup.bank.clientmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ClientDto {
    private String firstName;
    private String surName;
    private String middleName;
    private Date birthDate;
    private String birthPlace;
    private String sex;
    private Integer passportNum;
    private String phoneNumber;
    @JsonProperty("accounts")
    private List<AccountDto> accountDtoList;
}
