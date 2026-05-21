package com.dmitrix.wallet.domain.useCases;

import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.queries.GetWalletQuery;

public interface GetWalletUseCase {
    Wallet handle(GetWalletQuery query);
}
