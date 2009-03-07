/**
 * 
 */
package org.oneupfordev.doit.parsers;

/**
 * @author Roger Leite
 */
class CallableWord {

	String word = null;
	String argument = null;

	public CallableWord(final String word, final String argument) {
		this.word = word;
		if (argument != null) {
			this.argument = normalizeArgument(argument);
		}
	}

	private String normalizeArgument(final String argument) {
		StringBuilder arg = new StringBuilder(argument);
		arg.deleteCharAt(0);
		arg.deleteCharAt(arg.length() -1);
		return arg.toString();
	}

}
