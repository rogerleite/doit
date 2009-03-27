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
package org.oneupfordev.doit.parsers;

/**
 * <p>
 * Represents the smaller part of an Expression, a "word-argument" part.<br>
 * When {@link ExpressionParser} compiles an expression, a list of
 * {@link CallableWord}s are build.
 * </p>
 * 
 * @author Roger Leite
 */
class CallableWord {

	protected String word;
	protected String argument;

	/**
	 * Default constructor. Throws an {@link IllegalArgumentException} if word
	 * parameter is <code>null</code>.
	 * 
	 * @param word
	 *            know as command or inner command too.
	 * @param argument
	 *            is optional.
	 * @throws IllegalArgumentException
	 *             if word is <code>null</code>.
	 */
	public CallableWord(final String word, final String argument) {
		if (word == null) {
			throw new IllegalArgumentException("Word cannot be null.");
		}
		this.word = word;
		if (argument != null) {
			this.argument = normalizeArgument(argument);
		}
	}

	/**
	 * Remove first and last characters of argument.
	 * 
	 * @param argument
	 *            received from constructor.
	 * @return argument without first and last characters.
	 */
	private String normalizeArgument(final String argument) {
		final StringBuilder arg = new StringBuilder(argument);
		arg.deleteCharAt(0);
		arg.deleteCharAt(arg.length() - 1);
		return arg.toString();
	}

	public String getWord() {
		return word;
	}

	public String getArgument() {
		return argument;
	}

}
