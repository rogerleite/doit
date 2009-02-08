/**
 * 
 */
package org.oneupfordev.doit.parsers;

import java.util.ArrayList;
import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Dictionary;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Parse a String Expression to a {@link CallableExpression}.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class Expressions {

	private Dictionary dictionary = null;

	public Expressions(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public CallableExpression parse(final String expression) {
		RootCmdDescriptor cmdDescr = dictionary.find(expression);
		return parse(expression, cmdDescr);
	}

	protected CallableExpression parse(final String expression, RootCmdDescriptor cmdDescr) {

		List<Argument> arguments = new ArrayList<Argument>();
		String newExpression = parseArguments(expression, arguments);
		newExpression = removeInvalidChars(newExpression);

		return null;
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

	protected String parseArguments(final String expression, List<Argument> arguments) {
		final String simpleQuote = "'";
		arguments.clear();

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

}
