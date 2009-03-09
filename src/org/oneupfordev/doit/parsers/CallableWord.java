/**
 * 
 */
package org.oneupfordev.doit.parsers;

/**
 * <p>Represents the smaller part of an Expression, a "word-argument" part.<br>
 * When {@link ExpressionParser} compiles an expression, a list of {@link CallableWord}s are build.
 * </p>
 * @author Roger Leite
 */
class CallableWord {

	protected String word = null;
	protected String argument = null;

	/**
	 * Default constructor.
	 * Throws an {@link IllegalArgumentException} if word parameter is <code>null</code>.
	 * @param word know as command or inner command too.
	 * @param argument is optional.
	 * @throws IllegalArgumentException if word is <code>null</code>.
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
	 * @param argument received from constructor.
	 * @return argument without first and last characters.
	 */
	private String normalizeArgument(final String argument) {
		StringBuilder arg = new StringBuilder(argument);
		arg.deleteCharAt(0);
		arg.deleteCharAt(arg.length() -1);
		return arg.toString();
	}

	public String getWord() {
		return word;
	}
	public String getArgument() {
		return argument;
	}

}
