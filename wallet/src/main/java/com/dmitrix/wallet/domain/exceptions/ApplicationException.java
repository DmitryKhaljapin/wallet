package com.dmitrix.wallet.domain.exceptions;

public class ApplicationException extends RuntimeException{
    public ApplicationException(String message) {
        super(message);
    }
}
