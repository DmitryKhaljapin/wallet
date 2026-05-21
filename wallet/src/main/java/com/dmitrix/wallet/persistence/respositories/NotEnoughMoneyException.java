package com.dmitrix.wallet.persistence.respositories;

import com.dmitrix.wallet.domain.exceptions.ApplicationException;

public class NotEnoughMoneyException extends ApplicationException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
