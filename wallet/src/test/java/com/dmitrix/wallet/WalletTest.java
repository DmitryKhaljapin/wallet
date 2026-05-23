package com.dmitrix.wallet;

import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.exceptions.NotEnoughMoneyException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTest {
    @Test
    void shouldDepositMoney() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));

        wallet.deposit(new BigDecimal("40.00"));

        assertEquals(new BigDecimal("140.00"), wallet.getBalance());
    }

    @Test
    void shouldWithdrawMoney() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));

        wallet.withdraw(new BigDecimal("30.00"));

        assertEquals(new BigDecimal("70.00"), wallet.getBalance());
    }

    @Test
    void shouldThrowExceptionWhenBalanceToLow() {
        UUID walletId = UUID.randomUUID();
        Wallet wallet = new Wallet(walletId, new BigDecimal("100.00"));

        assertThrows(NotEnoughMoneyException.class, () -> wallet.withdraw(new BigDecimal("150.00")));
    }
}
