/*
* This file is part of DoIt.
* 
* DoIt is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.

* DoIt is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU Lesser General Public License for more details.

* You should have received a copy of the GNU Lesser General Public License
* along with DoIt.  If not, see <http://www.gnu.org/licenses/>.
* 
* Copyright 2009 Roger Leite
 */

package org.oneupfordev.doit.parsers.exceptions;

import java.util.Arrays;

import org.oneupfordev.doit.parsers.ExpressionParser;

/**
 * <p>Used to indicate problems can occurs when <b>validating</b> expression.<br>
 * This validation is done at compile phrase, by {@link ExpressionParser}.</p>
 * @author Roger Leite
 */
public class InvalidExpressionException extends RuntimeException {

	/** Default serial version. */
	private static final long serialVersionUID = 1L;

	/** Message exception. */
	private String message = null;

	public InvalidExpressionException(final String expression,
													final int indexError,
													final String message) {
		super(message);
		customizeMessage(expression, indexError, message);
	}

	public InvalidExpressionException(final String expression,
													final int indexError,
													final String message,
													final Throwable cause) {
		super(message, cause);
		customizeMessage(expression, indexError, message);
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	private void customizeMessage(final String expression, int indexError, final String message) {
		this.message = message;

		if (indexError > 0) {
			char[] pointer = new char[indexError];
			Arrays.fill(pointer, ' ');
			pointer[indexError - 1] = '^';
			this.message += "\n" + expression + "\n" + String.valueOf(pointer);
		}
	}

}
