/**
 * 
 */
package org.oneupfordev.doit.exceptions;

import java.text.ParseException;

import org.oneupfordev.doit.parsers.ExpressionParser;

/**
 * <p>This exception is used during parse of Expression by {@link ExpressionParser#parse(String)}.<br>
 * In compile phrase, this exception can be thrown to indicate a problem in the <b>syntax</b> expression.</p>
 * @author Roger Leite
 */
public class ParseExpressionException extends ParseException {

	/** Default Serial version. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message
	 * @param offSet
	 */
	public ParseExpressionException(String message, int offSet) {
		super(message, offSet);
	}

}
