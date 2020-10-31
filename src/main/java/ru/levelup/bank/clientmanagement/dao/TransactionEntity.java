package ru.levelup.bank.clientmanagement.dao;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "transaction", schema = "public", catalog = "bank_db")
public class TransactionEntity {
    private int transactionId;
    private double amount;
    private String currency;
    private Timestamp transactionTime;
    private int accountReceiver;
    private Integer accountSender;
    private Collection<BalanceEntity> balancesByTransactionId;

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "transaction_time")
    public Timestamp getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Timestamp transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Basic
    @Column(name = "account_receiver")
    public int getAccountReceiver() {
        return accountReceiver;
    }

    public void setAccountReceiver(int accountReceiver) {
        this.accountReceiver = accountReceiver;
    }

    @Basic
    @Column(name = "account_sender")
    public Integer getAccountSender() {
        return accountSender;
    }

    public void setAccountSender(Integer accountSender) {
        this.accountSender = accountSender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return transactionId == that.transactionId &&
                Double.compare(that.amount, amount) == 0 &&
                accountReceiver == that.accountReceiver &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(transactionTime, that.transactionTime) &&
                Objects.equals(accountSender, that.accountSender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, currency, transactionTime, accountReceiver, accountSender);
    }

    @OneToMany(mappedBy = "transactionByTransactionId")
    public Collection<BalanceEntity> getBalancesByTransactionId() {
        return balancesByTransactionId;
    }

    public void setBalancesByTransactionId(Collection<BalanceEntity> balancesByTransactionId) {
        this.balancesByTransactionId = balancesByTransactionId;
    }
}
