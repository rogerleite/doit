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
