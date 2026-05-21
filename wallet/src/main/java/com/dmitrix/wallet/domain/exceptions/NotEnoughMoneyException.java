package com.dmitrix.wallet.domain.exceptions;

public class NotEnoughMoneyException extends ApplicationException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
