package ru.levelup.bank.clientmanagement.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "balance", schema = "public", catalog = "bank_db")
public class BalanceEntity {
    private int balanceId;
    private double balanceAfter;
    private double balanceBefore;
    private String currency;
    //private Integer accountId;
    //private Integer transactionId;
    private AccountEntity accountByAccountId;
    private TransactionEntity transactionByTransactionId;

    @Id
    @Column(name = "balance_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    @Basic
    @Column(name = "balance_after")
    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    @Basic
    @Column(name = "balance_before")
    public double getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(double balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
/*
    @Basic
    @Column(name = "account_id")
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }


    @Basic
    @Column(name = "transaction_id")
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BalanceEntity that = (BalanceEntity) o;
        return balanceId == that.balanceId &&
                Double.compare(that.balanceAfter, balanceAfter) == 0 &&
                Double.compare(that.balanceBefore, balanceBefore) == 0 &&
                Objects.equals(currency, that.currency);
                //Objects.equals(accountId, that.accountId) &&
                //Objects.equals(transactionId, that.transactionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balanceId, balanceAfter, balanceBefore, currency);
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    public AccountEntity getAccountByAccountId() {
        return accountByAccountId;
    }

    public void setAccountByAccountId(AccountEntity accountByAccountId) {
        this.accountByAccountId = accountByAccountId;
    }

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    public TransactionEntity getTransactionByTransactionId() {
        return transactionByTransactionId;
    }

    public void setTransactionByTransactionId(TransactionEntity transactionByTransactionId) {
        this.transactionByTransactionId = transactionByTransactionId;
    }
}
