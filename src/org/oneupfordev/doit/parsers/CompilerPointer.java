/**
 * 
 */
package org.oneupfordev.doit.parsers;

import org.oneupfordev.doit.exceptions.ParseExpressionException;


/**
 * <p>Important part of {@link Compiler}, responsible to read Words, Argument and Assign of an expression.<br>
 * Do some parse validations and contains the current index to continue reading.</p>
 * @author Roger Leite
 */
class CompilerPointer {

	private static final char ARGUMENT_CHAR = '\'';
	private static final char ASSIGN_CHAR = ':';

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
		if (isEOE() || charExpression[endIndex] != ARGUMENT_CHAR) {
			return null;
		}
		endIndex++;
		int possibleEndIndex = expression.indexOf(ARGUMENT_CHAR, endIndex);
		if (possibleEndIndex < 0) {
			String msg = String.format("Closing character argument \"%s\" not found!", ARGUMENT_CHAR);
			throw new ParseExpressionException(msg, endIndex);
		}
		endIndex = expressionIterator.iterate(possibleEndIndex + 1, iterateWhiteSpaces);
		String arg = expression.substring(currentIndex, endIndex);
		arg = arg.trim();

		setCurrentIndex(endIndex);
		return arg;
	}

	public String readAssign() throws ParseExpressionException {
		if (isEOE()) {
			return null;
		}
		int endIndex = getCurrentIndex();
		if (charExpression[endIndex] != ASSIGN_CHAR) {
			String msg = String.format("Expected assign char \"%s\" at index %d.", ASSIGN_CHAR, endIndex);
			throw new ParseExpressionException(msg, endIndex);
		}
		endIndex++;
		String assign = expression.substring(endIndex, size);
		if ("".equals(assign)) {
			assign = null;
		}

		setCurrentIndex(size);
		return assign;
	}

}
