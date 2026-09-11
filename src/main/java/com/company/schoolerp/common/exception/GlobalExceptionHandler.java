package com.company.schoolerp.common.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.company.schoolerp.common.RestApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetail> handleAllException(Exception ex, WebRequest request) throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<ErrorDetail>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//for spring validation
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<RestApiResponse<ErrorDetail>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
	    FieldError inputError = ex.getBindingResult().getFieldError();
	    String errorMessage = inputError.getDefaultMessage();
	    ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false), errorMessage, LocalDate.now());
	    return new ResponseEntity<>(new RestApiResponse<>(false,errorDetails),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<RestApiResponse<ErrorDetail>> handleResourceNotFoundException(Exception ex, WebRequest request) 
			throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<>(new RestApiResponse<>(false,errorDetails),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateResourceException.class)
	public final ResponseEntity<RestApiResponse<ErrorDetail>> handleDuplicateResourceException(Exception ex, WebRequest request) 
			throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<>(new RestApiResponse<>(false,errorDetails),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public final ResponseEntity<RestApiResponse<ErrorDetail>> handleInvalidInputException(Exception ex, WebRequest request) 
			throws Exception{
		ErrorDetail errorDetails = new ErrorDetail(request.getDescription(false),ex.getMessage(),LocalDate.now());
		return new ResponseEntity<>(new RestApiResponse<>(false,errorDetails),HttpStatus.BAD_REQUEST);
	}
	
}
