/**
 * 
 */
package org.oneupfordev.doit.parsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.oneupfordev.doit.packs.descriptors.ArgumentType;
import org.oneupfordev.doit.packs.descriptors.CmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Validate and "compile" an expression, in a list of {@link CallableWord}s.
 * @author Roger Leite
 */
class WordWalker {

	private final String expression;
	private final RootCmdDescriptor rootCmd;
	private List<CallableWord> words = null;

	public WordWalker(final String expression, final RootCmdDescriptor rootCmd) {
		this.expression = expression;
		this.rootCmd = rootCmd;
	}

	public WordWalker parse() {
		if (words == null) {
			words = new ArrayList<CallableWord>();
			CmdDescriptor castOfRootCmd = (CmdDescriptor) rootCmd;
			populateWords(0, Arrays.asList(castOfRootCmd));
		}
		return this;
	}

	public List<CallableWord> getWords() {
		if (words == null) {
			throw new RuntimeException("Method parse not called.");
		}
		return words;
	}

	private void populateWords(int expressionIndex, List<CmdDescriptor> possibleCmds) {
		String word = extractWord(expressionIndex);
		if (word == null) {
			throw new RuntimeException("Command not found.");		//TODO create a InvalidExpressionException and replace this
		}
		int indexOfCmdDescriptor = indexOf(possibleCmds, word);
		if (indexOfCmdDescriptor < 0) {
			String msg = String.format("Don't know what you mean by %s.", word);
			throw new RuntimeException(msg);		//TODO create a InvalidExpressionException and replace this
		}
		expressionIndex += word.length();
		word = word.trim();

		CmdDescriptor selectCmdDescr = possibleCmds.get(indexOfCmdDescriptor);
		String argument = extractArgument(expressionIndex);
		if (selectCmdDescr.getArgumentType() == ArgumentType.NO_ACCEPT && argument != null) {
			String msg = String.format("Invalid parameter at index %d.", expressionIndex);
			throw new RuntimeException(msg);		//TODO create a InvalidExpressionException and replace this
		} else if (selectCmdDescr.getArgumentType() == ArgumentType.REQUIRED && argument == null) {
			String msg = String.format("Required parameter at index %d.", expressionIndex);
			throw new RuntimeException(msg);		//TODO create a InvalidExpressionException and replace this
		}
		if (argument != null) {
			expressionIndex += argument.length();
			argument = argument.trim();
		}

		words.add(new CallableWord(word, argument));
		if (!selectCmdDescr.getInnerCmds().isEmpty()) {
			populateWords(expressionIndex, selectCmdDescr.getInnerCmds());
		} else {
			//end of expression
			//TODO check for assign
		}

	}

	/**
	 * @param possibleCmds list of {@link CmdDescriptor}s.
	 * @param word simple one, cannot be "normalized".
	 * @return -1 if word not found in list or the index found in the list.
	 */
	private int indexOf(final List<CmdDescriptor> possibleCmds, final String word) {
		for (int i = 0; i < possibleCmds.size(); i++) {
			if (possibleCmds.get(i).equals(word)) {
				return i;
			}
		}
		return -1;
	}

	private String extractArgument(int expressionIndex) {
		return null;
	}

	private String extractWord(int expressionIndex) {
		return null;
	}

}
