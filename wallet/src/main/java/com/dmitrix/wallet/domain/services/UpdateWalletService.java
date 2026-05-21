package com.dmitrix.wallet.domain.services;

import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.repositories.WalletRepositoryInterface;
import com.dmitrix.wallet.domain.useCases.UpdateWalletUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateWalletService implements UpdateWalletUseCase {
    private WalletRepositoryInterface walletRepository;

    public UpdateWalletService(WalletRepositoryInterface walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Wallet handle(UpdateWalletCommand command) {
        Wallet wallet = this.walletRepository.getByIdForUpdate(command.walletId());

        switch (command.operationType()) {
            case DEPOSIT -> wallet.deposit(command.amount());
            case WITHDRAW -> wallet.withdraw(command.amount());
        }

        return this.walletRepository.save(wallet);
    }
}
