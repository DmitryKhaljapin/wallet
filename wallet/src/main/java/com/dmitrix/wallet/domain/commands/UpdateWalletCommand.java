package com.dmitrix.wallet.domain.commands;

import com.dmitrix.wallet.domain.OperationType;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateWalletCommand (
    UUID walletId,
    BigDecimal amount,
    OperationType operationType
) {}
