package com.dmitrix.wallet.domain.queries;

import java.util.UUID;

public record GetWalletQuery (
    UUID walletId
) {}
