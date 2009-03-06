/**
 * 
 */
package org.oneupfordev.doit.parsers;

import org.oneupfordev.doit.exceptions.ParseExpressionException;


/**
 * @author Roger Leite
 */
class CompilerPointer {

	private final String expression;
	private final char[] charExpression;

	/** Index help to know from where start reading. */
	private int currentIndex;
	/** Size of Expression. Set at constructor. */
	private int size;

	private interface IteratorCondition {
		boolean shouldBreak(char c, boolean isWhiteSpace);
	}

	private class Iterator {
		int iterate(int initialIndex, IteratorCondition condition) {
			int index = 0;
			for (index = initialIndex; index < size; index++) {
				char c = charExpression[index];
				boolean isWhiteSpace = (c == ' ' || c == '\n' || c == '\t');
				if (condition.shouldBreak(c, isWhiteSpace)) {
					break;
				}
			}
			return index;
		}
	}

	private IteratorCondition iterateWhiteSpaces = new IteratorCondition() {
		public boolean shouldBreak(char c, boolean isWhiteSpace) {
			return !isWhiteSpace;
		}
	};
	private IteratorCondition iterateWord = new IteratorCondition() {
		public boolean shouldBreak(char c, boolean isWhiteSpace) {
			return isWhiteSpace;
		}
	};

	private Iterator expressionIterator = new Iterator();

	/**
	 * @param expression to read words, argument and assign.
	 */
	public CompilerPointer(final String expression) {
		this.expression = expression;
		this.charExpression = expression.toCharArray();
		this.currentIndex = 0;
		this.size = expression.length();
	}

	public int getCurrentIndex() {
		return currentIndex;
	}
	private void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * Is End Of Expression?
	 * @return <code>true</code> if currentIndex is equal or greater than size of expression.
	 */
	public boolean isEOE() {
		return currentIndex >= size;
	}

	public String readWord() {
		int endIndex = expressionIterator.iterate(currentIndex, iterateWhiteSpaces);
		endIndex = expressionIterator.iterate(endIndex, iterateWord);
		endIndex = expressionIterator.iterate(endIndex, iterateWhiteSpaces);

		String word = expression.substring(currentIndex, endIndex);
		word = word.trim();
		if ("".equals(word)) {
			word = null;
		}

		setCurrentIndex(endIndex);
		return word;
	}

	public String readArgument() throws ParseExpressionException {
		int endIndex = expressionIterator.iterate(currentIndex, iterateWhiteSpaces);
		if (isEOE() || charExpression[endIndex] != '\'') {
			return null;
		}
		endIndex++;
		int possibleEndIndex = expression.indexOf("'", endIndex);
		if (possibleEndIndex < 0) {
			throw new ParseExpressionException("Closing character argument \"'\" not found!) ", endIndex);
		}
		endIndex = expressionIterator.iterate(possibleEndIndex + 1, iterateWhiteSpaces);
		String arg = expression.substring(currentIndex, endIndex);
		arg = arg.trim();

		setCurrentIndex(endIndex);
		return arg;
	}

	public String readAssign() {
		//TODO implement this
		return null;
	}

}
