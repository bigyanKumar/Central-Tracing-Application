package com.user.demo.globalexceptionhandler;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(GenericException.class)
	public ResponseEntity<?> custumerNotFound(GenericException genEx, WebRequest wq){
		log.info("Exception trapped in globalException Handler#User-Service :: {} ", ExceptionUtils.getStackTrace(genEx));
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),genEx.getLocalizedMessage(),
				wq.getDescription(false)), HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		log.info("Exception trapped in globalException Handler#User-Service  :: {} ", ExceptionUtils.getStackTrace(me));
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage()),
				HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?> mynotFoundHandler(NoHandlerFoundException nfe,WebRequest wq) {
		log.info("Exception trapped in globalException Handler#User-Service  :: {} ", ExceptionUtils.getStackTrace(nfe));
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), nfe.getMessage(), 
				wq.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> myExceptionHandler(Exception e, WebRequest wq) throws IOException {
		log.info("Exception trapped in globalException Handler#User-Service  :: {} ", ExceptionUtils.getStackTrace(e));
		return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(),e.getMessage(),
				wq.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}
