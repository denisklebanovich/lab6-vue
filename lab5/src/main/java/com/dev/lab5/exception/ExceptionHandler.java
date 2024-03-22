package com.dev.lab5.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {


	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionDto> handleException(Exception e) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setMessage(e.getMessage());
		return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ChangeSetPersister.NotFoundException.class)
	public ResponseEntity<ExceptionDto> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setMessage(e.getMessage());
		return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
	}
}
