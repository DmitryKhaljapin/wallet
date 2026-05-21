package com.dmitrix.wallet.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class WalletEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private BigDecimal balance;

    public WalletEntity() {}

    public WalletEntity(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    UUID getId() {
        return this.id;
    }
    void setId (UUID id) {
        this.id = id;
    }

    BigDecimal getBalance() {
        return this.balance;
    }

    void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
