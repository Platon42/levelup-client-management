package ru.levelup.bank.clientmanagement.dto;

import lombok.Data;

@Data
public class AccountDto {
    private String accNumber;
    private CurrencyCode accCurrency;
}
