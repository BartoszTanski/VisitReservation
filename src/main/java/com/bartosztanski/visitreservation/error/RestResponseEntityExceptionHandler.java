package com.bartosztanski.visitreservation.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bartosztanski.visitreservation.model.ErrorMessage;

	
@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(VisitNotAvailableException.class)
	public ResponseEntity<ErrorMessage> visitNotAvailableException(
			VisitNotAvailableException visitNotAvailableException, 	WebRequest webRequest) {
		
		ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT,visitNotAvailableException.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
	}
	
	@ExceptionHandler(ClientDetailsNotMatchesException.class)
	public ResponseEntity<ErrorMessage> clientDetailsNotMatchesException(
			ClientDetailsNotMatchesException clientDetailsNotMatchesException, WebRequest webRequest) {
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,clientDetailsNotMatchesException.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorMessage> noSuchElementException(
			NoSuchElementException noSuchElementException, WebRequest webRequest) {
		
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,noSuchElementException.getMessage());
		if (message.getMessage()==null) message.setMessage("Element with given id does not exist");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
		
}

