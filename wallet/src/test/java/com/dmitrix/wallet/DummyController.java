package com.dmitrix.wallet;

import com.dmitrix.wallet.api.model.WalletRequest;
import com.dmitrix.wallet.domain.exceptions.NotEnoughMoneyException;
import com.dmitrix.wallet.domain.exceptions.RecordNotFoundException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
class DummyController {

    @GetMapping("/not-found")
    public void notFound() {
        throw new RecordNotFoundException("Wallet not found");
    }

    @GetMapping("/not-enough-money")
    public void notEnoughMoney() {
        throw new NotEnoughMoneyException("Not enough money");
    }

    @PostMapping("/bad-json")
    public void badJson(@Valid @RequestBody WalletRequest request) {
    }

    @GetMapping("/unknown")
    public void unknown() {
        throw new RuntimeException("boom");
    }
}
