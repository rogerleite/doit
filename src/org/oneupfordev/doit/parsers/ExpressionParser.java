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

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.ClassController;
import net.vidageek.mirror.Mirror;
import net.vidageek.mirror.ObjectController;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.parsers.exceptions.InvalidExpressionException;
import org.oneupfordev.doit.stuff.Dictionary;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * @author Roger Leite
 */
public class ExpressionParser {

	private DoItSession session = null;
	private Dictionary dictionary = null;

	public ExpressionParser(final DoItSession session) {
		this.session = session;
		this.dictionary = session.getDictionary();
	}

	public CallableExpression parse(final String expression) {
		RootCmdDescriptor rootCmdDescr = dictionary.find(expression);
		if (rootCmdDescr == null) {
			throw new InvalidExpressionException(expression, 0, "Unknow expression.");
		}

		Compiler compiler = new Compiler(expression, rootCmdDescr);
		compiler.compile();

		List<CallableWord> words = compiler.getWords();

		ClassController<? extends CallableExpression> callableController = Mirror.on(rootCmdDescr.getClassExpression());
		CallableExpression callable = createInstance(callableController, words.get(0));

		callable.setSession(session);  //inject session
		callable.setAssign(compiler.getAssign());  //set assign

		ObjectController objectController = Mirror.on(callable);

		for (int i = 1; i < words.size(); i++) {
			CallableWord word = words.get(i);
			Method methodToCall = findMethod(callableController, word);
			Object resultFromInvoke = null;
			if (word.getArgument() != null) {
				resultFromInvoke = objectController.invoke().method(methodToCall).withArgs(word.getArgument());
			} else {
				resultFromInvoke = objectController.invoke().method(methodToCall).withoutArgs();
			}

			if (resultFromInvoke instanceof CallableExpression) {
				callable = (CallableExpression) resultFromInvoke;	//attention on this!!!
			}
		}

		return callable;
	}

	private CallableExpression createInstance(
			final ClassController<? extends CallableExpression> callableController,
			final CallableWord callableWord) {

		CallableExpression callable = null;
		if (callableWord.getArgument() != null) {
			callable = (CallableExpression) callableController.invoke().constructor().withArgs(callableWord.getArgument());
		} else {
			callable = (CallableExpression) callableController.invoke().constructor().withoutArgs();
		}

		return callable;
	}

	private Method findMethod(final ClassController<? extends CallableExpression> classController,
											final CallableWord callableWord) {

		boolean withArgs = callableWord.getArgument() != null;
		String value = callableWord.getWord();

		//TO THINK: make a "hash-cache" of methods by name?
		for (Method m : classController.reflectAll().methods()) {
			if (m.getName().equalsIgnoreCase(value)) {
				if (!withArgs && m.getParameterTypes().length == 0) {
					return m;
				} else if (withArgs && m.getParameterTypes().length == 1) {
					return m;
				}
			}
		}
		String msg = String.format("Method '%s' cannot be found.", value);
		throw new InvalidExpressionException(null, -1, msg);
	}

}
