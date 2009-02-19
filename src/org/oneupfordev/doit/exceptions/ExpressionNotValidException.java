/**
 * 
 */
package org.oneupfordev.doit.exceptions;

/**
 * @author Roger Leite
 */
public class ExpressionNotValidException extends RuntimeException {

	/** Default serial version. */
	private static final long serialVersionUID = 1L;

	public ExpressionNotValidException() {
		super();
	}

	public ExpressionNotValidException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpressionNotValidException(String message) {
		super(message);
	}

	public ExpressionNotValidException(Throwable cause) {
		super(cause);
	}

}
