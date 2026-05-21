package com.dmitrix.wallet.domain.services;

import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.queries.GetWalletQuery;
import com.dmitrix.wallet.domain.repositories.WalletRepositoryInterface;
import com.dmitrix.wallet.domain.useCases.GetWalletUseCase;

public class GetWalletService implements GetWalletUseCase {
    private WalletRepositoryInterface walletRepository;

    public GetWalletService(WalletRepositoryInterface walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet handle(GetWalletQuery query) {
        return this.walletRepository.getById(query.walletId());
    }
}
