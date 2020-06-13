package com.assessment.exceptions;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.zip.DataFormatException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Prerna Garg
 *
 * This is the custom exception handler class. All the exceptions from the application are handled here.
 */
@ControllerAdvice
public class CustomExceptionHandler extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method handles the duplicate image save request.
	 * 
	 * @param exception
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(FileAlreadyExistsException.class)
	public ResponseEntity<String> handleFileAlreadyExistsException(FileAlreadyExistsException exception) {
		return new ResponseEntity<>("An image file with the same name already exists.", HttpStatus.BAD_REQUEST);

	}

	/**
	 * This method informs the user that only images must be saved.
	 * 
	 * @param exception
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(DataFormatException.class)
	public ResponseEntity<String> handleDataFormatException(DataFormatException exception) {
		return new ResponseEntity<>("Only files of type image can be saved.", HttpStatus.BAD_REQUEST);

	}

	/**
	 * This method informs the user that the image he is trying to retrieve does not
	 * exist.
	 * 
	 * @param exception
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException exception) {
		return new ResponseEntity<>("No file exists with the requested name", HttpStatus.BAD_REQUEST);

	}

	/**
	 * This method handles all the exceptions apart from those handled above.
	 * 
	 * @param exception
	 * @return ResponseEntity<String>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
