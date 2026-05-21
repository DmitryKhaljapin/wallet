package com.dmitrix.wallet.domain.useCases;

import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;

public interface
UpdateWalletUseCase {
    Wallet handle(UpdateWalletCommand command);
}
