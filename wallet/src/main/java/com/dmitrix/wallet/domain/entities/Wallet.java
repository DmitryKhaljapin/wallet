package com.dmitrix.wallet.domain.entities;

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
    public void setBalance(BigDecimal balance) {
        //TODO add checks
        this.balance = balance;
    }
}
