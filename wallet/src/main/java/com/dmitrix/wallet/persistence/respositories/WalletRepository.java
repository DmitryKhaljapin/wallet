package com.dmitrix.wallet.persistence.respositories;

import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.exceptions.RecordNotFoundException;
import com.dmitrix.wallet.domain.repositories.WalletRepositoryInterface;
import com.dmitrix.wallet.persistence.WalletJpaRepository;
import com.dmitrix.wallet.persistence.entities.WalletEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class WalletRepository implements WalletRepositoryInterface {
    private final WalletJpaRepository walletJpaRepository;

    public WalletRepository(WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    private WalletEntity toEntity(Wallet wallet) {
        return  new WalletEntity(wallet.getWalletId(), wallet.getBalance());
    }

    private Wallet toWallet(WalletEntity walletEntity) {
        return new Wallet(walletEntity.getId(), walletEntity.getBalance());
    }

    public Wallet getByIdForUpdate(UUID id) throws RecordNotFoundException {
        WalletEntity walletEntity = this.walletJpaRepository.findByIdForUpdate(id).orElseThrow(() -> new RecordNotFoundException("Wallet not found"));

        return this.toWallet(walletEntity);
    }

    public Wallet save(Wallet walletToSave) {
        WalletEntity walletEntityToSave = this.toEntity(walletToSave);

        WalletEntity savedWalletEntity = this.walletJpaRepository.save(walletEntityToSave);

        return this.toWallet(savedWalletEntity);
    }
}
