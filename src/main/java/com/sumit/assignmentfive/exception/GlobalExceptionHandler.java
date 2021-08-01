package com.sumit.assignmentfive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(ArithmeticException.class)
public final ResponseEntity<Object> arithmeticException(ArithmeticException ex, WebRequest request) {
ErrorDetails errorDetails = new ErrorDetails(new Date(), "Arithmetic Exception", "Exception",400);
return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
}

@ExceptionHandler(NullPointerException.class)
public final ResponseEntity<Object> nullPointerException(NullPointerException ex, WebRequest request) {
ErrorDetails errorDetails = new ErrorDetails(new Date(), "Null Pointer Exception", "Exception",404);
return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
}

@ExceptionHandler(Exception.class)
public final ResponseEntity<Object> exception(Exception ex, WebRequest request) {
ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), ex.getCause().getMessage(),500);
return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
}


@ExceptionHandler( BadRequest.class)
public final ResponseEntity<Object> badRequestException(Exception ex, WebRequest request) {
ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "Bad Request",400);
return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
}




}
