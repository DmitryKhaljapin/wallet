package com.dmitrix.wallet.application;

import com.dmitrix.wallet.api.model.ErrorResponse;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class ErrorMapper {
    public  ErrorResponse toResponse(String message, int status) {
        return new ErrorResponse().message(message).status(status).timestamp(OffsetDateTime.now());
    }
}
