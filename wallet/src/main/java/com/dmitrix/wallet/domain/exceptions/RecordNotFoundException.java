package com.dmitrix.wallet.domain.exceptions;

public class RecordNotFoundException extends ApplicationException{
    public RecordNotFoundException(String message) {
        super(message);
    }
}
