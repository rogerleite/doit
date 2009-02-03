/**
 * 
 */
package org.oneupfordev.doit.exceptions;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
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
