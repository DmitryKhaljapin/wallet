package com.dmitrix.wallet.persistence;

import com.dmitrix.wallet.persistence.entities.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, UUID> {
}
