/**
 * 
 */
package org.oneupfordev.doit.exceptions;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ExpressionIllegalArgumentException extends IllegalArgumentException {

	/** Default serial version. */
	private static final long serialVersionUID = 1L;

	public ExpressionIllegalArgumentException() {
		super();
	}

	public ExpressionIllegalArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExpressionIllegalArgumentException(String s) {
		super(s);
	}

	public ExpressionIllegalArgumentException(Throwable cause) {
		super(cause);
	}

}
