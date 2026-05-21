package com.dmitrix.wallet.domain.entities;

import com.dmitrix.wallet.persistence.respositories.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private UUID walletId;
    private BigDecimal balance;

    public Wallet(UUID walletId, BigDecimal balance) {
        this.walletId = walletId;
        this.balance = balance;
    }

    public UUID getWalletId() {
        return this.walletId;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) throws NotEnoughMoneyException {
        if (balance.compareTo(amount) < 0) throw new NotEnoughMoneyException("Insufficient funds");

        balance = balance.subtract(amount);
    }
}
