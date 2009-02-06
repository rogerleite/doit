/**
 * 
 */
package org.oneupfordev.doit.parsers;

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
		return null;
	}

}
