package com.example.demo.adapters.in.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.domain.exception.ChampionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ChampionNotFoundException.class)
	public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError) {
		return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
		String message = "ops! Ocorreu um erro inesperado!";
		logger.error(message, unexpectedError);
		return ResponseEntity.internalServerError().body(new ApiError(message));
	}

	public record ApiError(String message) {}
}