/**
 * 
 */
package org.oneupfordev.doit.exceptions;

import org.oneupfordev.doit.parsers.ExpressionParser;
import org.oneupfordev.doit.stuff.Dictionary;

/**
 * <p>Used to indicate problems can occurs when <b>validating</b> expression.<br>
 * This validation is done at compile phrase, by {@link ExpressionParser} with help of {@link Dictionary}.</p>
 * @author Roger Leite
 */
public class InvalidExpressionException extends RuntimeException {

	/** Default serial version. */
	private static final long serialVersionUID = 1L;

	public InvalidExpressionException(final String expression,
			final int indexProblem,
			final String messsage) {
		super(messsage);
	}

	public InvalidExpressionException(final String expression,
			final int indexProblem,
			final String messsage,
			final Throwable cause) {
		super(messsage, cause);
	}

}
