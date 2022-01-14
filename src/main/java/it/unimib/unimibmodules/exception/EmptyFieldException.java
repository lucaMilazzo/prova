package it.unimib.unimibmodules.exception;

/**
 * Exception for empty fields.
 * @author Luca milazzo
 * @version 0.1.0
 */
public class EmptyFieldException extends Exception {
	/**
	 * Constructs an EmptyFieldException with the specified message and root exception.
	 * @param	message	the exception message
	 * @param err the root exception object
	 */
	public EmptyFieldException(String message, Throwable err) {
		super(message, err);
	}
	
	public EmptyFieldException(String message) {

		super(message);
	}
}