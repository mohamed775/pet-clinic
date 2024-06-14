package com.java.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobelExceptionHandler extends ResponseEntityExceptionHandler {

	
	
	@ExceptionHandler({NotFoundException.class})
	public ResponseEntity<?> handleRecordNotFound(NotFoundException ex){
		
		errorResponse error = new errorResponse (ex.getLocalizedMessage() ,Arrays.asList(ex.getMessage()));
		
		return  ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(error);
		
	}
	
	
	
	// handle invalid parameter in request 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<String> errors = new ArrayList<String>();
		
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		
		for(ObjectError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		
		errorResponse error = new errorResponse(ex.toString(),errors);
		
		return  ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(error);
	}
	
}
