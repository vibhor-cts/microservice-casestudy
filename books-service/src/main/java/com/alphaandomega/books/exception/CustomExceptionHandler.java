package com.alphaandomega.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	//private static final Logger WPT_LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Error> onUnauthorizedAccess(AccessDeniedException ex, WebRequest request) {
		Error error = new Error(String.valueOf(HttpStatus.FORBIDDEN.value()), "Access Denied");
		//WPT_LOGGER.error(ERROR_MSG, ex::getMessage);
		return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public final ResponseEntity<Error> onResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
//		Error error = new Error(String.valueOf(HttpStatus.NOT_FOUND.value()), RESOURCE_NOT_FOUND);
//		WPT_LOGGER.error(ERROR_MSG, ex::getMessage);
//		WpLoggerContext.clear();
//		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//	}
//	
//	@ExceptionHandler({ BadRequestException.class, ConversionFailedException.class,
//	MethodArgumentTypeMismatchException.class,IllegalArgumentException.class ,ConstraintViolationException.class})
//	public ResponseEntity<Error> onInvalidArgument(RuntimeException ex) {
//		Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()), ex.getMessage());
//		WPT_LOGGER.error(ERROR_MSG, ex);
//		WpLoggerContext.clear();
//		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Error> onAllUnhandledExceptions(Exception ex, WebRequest request) {
//		Error error = new Error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
//		WPT_LOGGER.error(ERROR_MSG, ex);
//		WpLoggerContext.clear();
//		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
