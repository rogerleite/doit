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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.oneupfordev.doit.packs.descriptors.ArgumentType;
import org.oneupfordev.doit.packs.descriptors.CmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.parsers.exceptions.InvalidExpressionException;
import org.oneupfordev.doit.parsers.exceptions.ParseExpressionException;

/**
 * Validate and "compile" an expression, in a list of {@link CallableWord}s.
 * @author Roger Leite
 */
class Compiler {

	private final String expression;
	private final RootCmdDescriptor rootCmd;

	private List<CallableWord> words = null;
	private String assign = null;

	public Compiler(final String expression, final RootCmdDescriptor rootCmd) {
		this.expression = expression;
		this.rootCmd = rootCmd;
	}

	/**
	 * <p>Do some validation, syntax and logic, in the expression,
	 * and build a list of {@link CallableWord}s.</p>
	 * TODO: put here example of validations. For now, you can see at CompilerTest, in sources.
	 * @throws InvalidExpressionException any logic or syntax problem during compilation.
	 */
	public void compile() throws InvalidExpressionException {
		if (words == null) {
			words = new ArrayList<CallableWord>();
			CompilerPointer compilerPointer = new CompilerPointer(this.expression);
			CmdDescriptor castOfRootCmd = (CmdDescriptor) rootCmd;

			try {
				extractWords(compilerPointer, Arrays.asList(castOfRootCmd));
			} catch (ParseExpressionException e) {
				String msg = String.format("Invalid syntax at index %d.\n%s", e.getErrorOffset(), e.getMessage());
				throw new InvalidExpressionException(expression, compilerPointer.getCurrentIndex(), msg, e);
			}
		}
	}

	public List<CallableWord> getWords() {
		if (words == null) {
			throw new RuntimeException("Method read not called.");
		}
		return words;
	}

	public String getAssign() {
		return this.assign;
	}

	private void extractWords(final CompilerPointer compilerPointer,
			final List<CmdDescriptor> possibleCmds)
			throws ParseExpressionException, InvalidExpressionException {

		String word = compilerPointer.readWord();
		if (word == null) {
			throw new InvalidExpressionException(expression, compilerPointer.getCurrentIndex(), "Unknow command.");
		}
		int indexOfCmdDescriptor = indexOf(possibleCmds, word);
		if (indexOfCmdDescriptor < 0) {
			String msg = String.format("Don't know what you mean by %s.", word);
			throw new InvalidExpressionException(expression, compilerPointer.getCurrentIndex(), msg);
		}

		CmdDescriptor selectedCmdDescr = possibleCmds.get(indexOfCmdDescriptor);
		String argument = compilerPointer.readArgument();
		if (selectedCmdDescr.getArgumentType() == ArgumentType.NO_ACCEPT && argument != null) {
			String msg = String.format("Invalid parameter at index %d.", compilerPointer.getCurrentIndex());
			throw new InvalidExpressionException(expression, compilerPointer.getCurrentIndex(), msg);
		} else if (selectedCmdDescr.getArgumentType() == ArgumentType.REQUIRED && argument == null) {
			String msg = String.format("Required parameter at index %d.", compilerPointer.getCurrentIndex());
			throw new InvalidExpressionException(expression, compilerPointer.getCurrentIndex(), msg);
		}

		words.add(new CallableWord(word, argument));
		if (compilerPointer.isEOE() || selectedCmdDescr.getInnerCmds().isEmpty()) {
			assign = compilerPointer.readAssign();
		} else {
			extractWords(compilerPointer, selectedCmdDescr.getInnerCmds());
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

}
