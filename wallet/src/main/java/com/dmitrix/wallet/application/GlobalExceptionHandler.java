package com.dmitrix.wallet.application;

import com.dmitrix.wallet.api.model.ErrorResponse;
import com.dmitrix.wallet.domain.exceptions.NotEnoughMoneyException;
import com.dmitrix.wallet.domain.exceptions.RecordNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final ErrorMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler(ErrorMapper mapper) {
        this.mapper = mapper;
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(RecordNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapper.toResponse(exception.getMessage(), 404));
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughMoney(NotEnoughMoneyException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(mapper.toResponse(exception.getMessage(), 422));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBadJson(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(mapper.toResponse("Invalid request body", 400));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(mapper.toResponse( "Validation failed", 400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown( Exception exception, HttpServletRequest request) {
        log.error(
                "Unhandled exception on {}",
                request.getRequestURI(),
                exception
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mapper.toResponse( "Internal server error", 500));
    }

}
