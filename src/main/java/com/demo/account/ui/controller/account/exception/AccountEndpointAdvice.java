package com.demo.account.ui.controller.account.exception;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Rest controller advice interceptor. <br>
 * Converts exceptions into valid responses.
 *
 * @author Hugo Costa
 * @since 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class AccountEndpointAdvice {

	/**
	 * Generic exception handler. Retrieves HttpStatus.INTERNAL_SERVER_ERROR.
	 *
	 * @param e {@link Exception}
	 * @return a {@link ResponseEntity} with {@link ErrorMessage}
	 */
	@ExceptionHandler( value = Exception.class )
	public ResponseEntity<ErrorMessage> handle( Exception e ) {
		return handleError( HttpStatus.INTERNAL_SERVER_ERROR, e );
	}

	/**
	 * Exception when the request is invalid. Retrieves HttpStatus.BAD_REQUEST.
	 *
	 * @param e {@link IllegalArgumentException}
	 * @return a {@link ResponseEntity} with {@link ErrorMessage}
	 */
	@ExceptionHandler( value = IllegalArgumentException.class )
	public ResponseEntity<ErrorMessage> handle( IllegalArgumentException e ) {
		return handleError( HttpStatus.BAD_REQUEST, e );
	}

	private ResponseEntity<ErrorMessage> handleError( HttpStatus httpStatus, Throwable e ) {
		log.error( e.getMessage() );
		return ResponseEntity.status( httpStatus ).body( new ErrorMessage( e.getMessage() ) );
	}

	@Value
	public static class ErrorMessage {

		String message;
	}
}
