package com.dmitrix.wallet.application;

import com.dmitrix.wallet.api.WalletApi;
import com.dmitrix.wallet.api.model.WalletRequest;
import com.dmitrix.wallet.api.model.WalletResponse;
import com.dmitrix.wallet.domain.commands.UpdateWalletCommand;
import com.dmitrix.wallet.domain.entities.Wallet;
import com.dmitrix.wallet.domain.queries.GetWalletQuery;
import com.dmitrix.wallet.domain.useCases.GetWalletUseCase;
import com.dmitrix.wallet.domain.useCases.UpdateWalletUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WalletController implements WalletApi {
    private final WalletMapper mappper;

    private final UpdateWalletUseCase updateWalletService;
    private final GetWalletUseCase getWalletService;

    public WalletController(WalletMapper mapper, UpdateWalletUseCase updateWalletService, GetWalletUseCase getWalletService) {
        this.mappper = mapper;
        this.updateWalletService = updateWalletService;
        this.getWalletService = getWalletService;
    }

    public ResponseEntity<WalletResponse> getWalletById(@PathVariable() UUID walletId) {
        GetWalletQuery query = this.mappper.toQuery(walletId);

        Wallet wallet = this.getWalletService.handle(query);

        return ResponseEntity.ok(this.mappper.toResponse(wallet));
    }

    public ResponseEntity<WalletResponse> updateWallet(@RequestBody() WalletRequest request) {
        UpdateWalletCommand command = this.mappper.toCommand(request);

        Wallet wallet = this.updateWalletService.handle(command);

        return ResponseEntity.ok(this.mappper.toResponse(wallet));
    }
}
