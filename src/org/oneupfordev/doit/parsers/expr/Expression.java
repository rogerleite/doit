/**
 * 
 */
package org.oneupfordev.doit.parsers.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class Expression {

	//private String originalExpression = null;
	protected String expression = null;

	//private RootCmdDescriptor rootCmdDescr = null;
	protected List<Argument> arguments = new ArrayList<Argument>();

	private List<Word> words = null;

	public Expression(final String originalExpression, final RootCmdDescriptor rootCmdDescr) {
		//this.originalExpression = originalExpression;
		//this.rootCmdDescr = rootCmdDescr;

		//TODO: verify if rootCmdDescr is really necessary here. Maybe not.

		expression = parseArguments(originalExpression);
		expression = removeInvalidChars(expression);
	}

	protected String parseArguments(final String expression) {
		final String simpleQuote = "'";

		StringBuilder parsedExpr = new StringBuilder(expression);
		int indexLeft = -1;
		int index = -1;
		while (true) {
			index = parsedExpr.indexOf(simpleQuote, index + 1);
			if (index < 0) break;
			if (indexLeft == -1) {
				indexLeft = index;
			} else {
				String value = parsedExpr.substring(indexLeft + 1, index);
				arguments.add(new Argument(value));

				parsedExpr.delete(indexLeft, index + 1);
				parsedExpr.insert(indexLeft, "$p" + arguments.size());

				indexLeft = -1;
				index = -1;
			}
		}
		if (indexLeft != -1) {
			throw new RuntimeException("A problem occurred during Arguments parsing.");
		}

		return parsedExpr.toString();
	}

	private String removeInvalidChars(final String expression) {
		String localToTrim = expression.replaceAll("\t|\n", " ").trim();
		StringBuilder newString = new StringBuilder("");
		
		int i = 0;
		while (i < localToTrim.length()) {
			char charAt = localToTrim.charAt(i);
			if (charAt == ' ') {
				while (charAt == ' ' && i < localToTrim.length()) {
					i++;
					charAt = localToTrim.charAt(i);
				}
				newString.append(' ');
			} else {
				newString.append(charAt);
				i++;
			}
		}
		return newString.toString().toLowerCase();
	}

	public List<Word> getWords() {
		if (words == null) {

			String[] arrayWords = expression.split(" ");
			words = new ArrayList<Word>(arrayWords.length);

			for(String w : arrayWords) {
				words.add(new Word(w, arguments));
			}
		}
		return Collections.unmodifiableList(words);
	}

	public Word getWord(int index) {
		getWords();  //load array if necessary
		return words.get(index);
	}

	@Override
	public String toString() {
		return expression;
	}

}
