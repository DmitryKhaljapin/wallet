package com.dmitrix.wallet.application;

import com.dmitrix.wallet.api.model.WalletRequest;
import com.dmitrix.wallet.api.model.WalletResponse;
import com.dmitrix.wallet.domain.OperationType;
import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.queries.GetWalletQuery;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WalletMapper {

    private OperationType toOperationType(WalletRequest.OperationTypeEnum operationType) {
        return switch (operationType) {
            case DEPOSIT -> OperationType.DEPOSIT;
            case WITHDRAW -> OperationType.WITHDRAW;
        };
    }

    public UpdateWalletCommand toCommand(WalletRequest request) {
        return new UpdateWalletCommand(
                request.getWalletId(),
                request.getAmount(),
                toOperationType(request.getOperationType())
        );
    }

    public GetWalletQuery toQuery(UUID walletId) {
        return new GetWalletQuery(walletId);
    }

    public WalletResponse toResponse(Wallet wallet) {
        return new WalletResponse().walletId(wallet.getWalletId()).balance(wallet.getBalance());
    }
}
