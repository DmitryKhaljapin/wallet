package com.dmitrix.wallet;

import com.dmitrix.wallet.domain.OperationType;
import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.repositories.WalletRepositoryInterface;
import com.dmitrix.wallet.domain.services.UpdateWalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateWalletServiceTest {

    @Mock
    private WalletRepositoryInterface walletRepository;

    @InjectMocks
    private UpdateWalletService service;

    @Test
    void ShouldDepositMoney() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));
        UpdateWalletCommand updateWalletCommand = new UpdateWalletCommand(walletId, new BigDecimal("50.00"), OperationType.DEPOSIT);

        when(walletRepository.getByIdForUpdate(walletId)).thenReturn(wallet);
        when(walletRepository.save(any())).thenAnswer(invocation  -> invocation.getArgument(0));

        Wallet result = service.handle(updateWalletCommand);

        assertEquals(new BigDecimal("150.00"), result.getBalance());
        verify(walletRepository).getByIdForUpdate(walletId);
        verify(walletRepository).save(wallet);
    }

    @Test
    void shouldWithdrawMoney() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));
        UpdateWalletCommand command = new UpdateWalletCommand(walletId, new BigDecimal("40.00"), OperationType.WITHDRAW);

        when(walletRepository.getByIdForUpdate(walletId)).thenReturn(wallet);
        when(walletRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Wallet result = service.handle(command);

        assertEquals(new BigDecimal("60.00"), result.getBalance());

        verify(walletRepository).getByIdForUpdate(walletId);
        verify(walletRepository).save(wallet);
    }
}