package com.dmitrix.wallet.domain.repositories;

import com.dmitrix.wallet.domain.entities.Wallet;

import java.util.UUID;

public interface WalletRepositoryInterface {
    Wallet update(Wallet wallet);
    Wallet getById(UUID id);
}
