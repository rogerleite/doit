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

import java.text.ParseException;

import org.oneupfordev.doit.parsers.ExpressionParser;

/**
 * <p>This exception is used during parse of Expression by {@link ExpressionParser#parse(String)}.<br>
 * In compile phrase, this exception can be thrown to indicate a problem in the <b>syntax</b> of the expression.</p>
 * @author Roger Leite
 */
public class ParseExpressionException extends ParseException {

	/** Default Serial version. */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 * @param message explaining the problem with syntax
	 * @param offSet index that occurred the problem
	 */
	public ParseExpressionException(final String message, int offSet) {
		super(message, offSet);
	}

}
