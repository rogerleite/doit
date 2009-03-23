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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.stuff.DoItMock;
import org.oneupfordev.doit.stuff.DoItSessionMock;


/**
 * Tests of Expression Parser.
 * @author Roger Leite
 */
public class ExpressionParserTest {

	private DoItSessionMock getDoItSessionMock() {
		DoItMock doItMock = new DoItMock();
		DoItSessionMock sessionMock = (DoItSessionMock) doItMock.createSession();

		ExampleExpressionPack validPack = new ExampleExpressionPack("example", new Class<?>[] {ExpressionValid.class});
		sessionMock.load(validPack);

		return sessionMock;
	}

	@Test
	public void shouldParseValidExpressions() {
		/*
		 * ExpressionValid annotations:
		 * ExprDescription(cmds={"test"})
		 * InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
		 */
		DoItSessionMock sessionMock = getDoItSessionMock();
		CallableExpression ce = sessionMock.parse("expressionvalid");
		checkCallableExpression(ce, true, false, null, false, false,
				false, null, false, null);
		assertNotNull("Context field cannot be null.", ce.getSession());

		ce = sessionMock.parse("expressionvalid 'arg_constructor'");
		checkCallableExpression(ce, false, true, "arg_constructor", false, false,
				false, null, false, null);

		ce = sessionMock.parse("expressionvalid test");
		checkCallableExpression(ce, true, false, null, true, false,
				false, null, false, null);

		ce = sessionMock.parse("expressionvalid 'arg_constructor' test");
		checkCallableExpression(ce, false, true, "arg_constructor", true, false,
				false, null, false, null);

		ce = sessionMock.parse("expressionvalid test testInner");
		checkCallableExpression(ce, true, false, null, true, true,
				false, null, false, null);

		ce = sessionMock.parse("expressionvalid 'arg_constructor' test testInner");
		checkCallableExpression(ce, false, true, "arg_constructor", true, true,
				false, null, false, null);

		ce = sessionMock.parse("expressionvalid 'arg_constructor' test testInner2 'testInner2'");
		checkCallableExpression(ce, false, true, "arg_constructor", true, false,
				false, null, true, "testInner2");

		ce = sessionMock.parse("expressionvalid 'arg_constructor' test testInner2 'testInner2 with char : like separator' : this is an assign.");
		checkCallableExpression(ce, false, true, "arg_constructor", true, false,
				false, null, true, "testInner2 with char : like separator");
		assertEquals(" this is an assign.", ce.getAssign());
	}

	private void checkCallableExpression(CallableExpression ce,
			boolean expectedConstructorExecuted,
			boolean expectedConstructorWithArgsExecuted,
			String expectedArgConstructor,
			boolean expectedTestExecuted,
			boolean expectedTestInnerExecuted,
			boolean expectedTestInnerWithArgsExecuted,
			String expectedArgInner,
			boolean expectedTestInner2WithArgsExecuted,
			String expectedArgInner2) {

		assertTrue("CallableExpression have to be an instanceof ExpressionValid",
				ce instanceof ExpressionValid);
		ExpressionValid expValid = (ExpressionValid) ce;

		assertEquals(expectedConstructorExecuted, expValid.constructorExecuted);
		assertEquals(expectedConstructorWithArgsExecuted, expValid.constructorWithArgsExecuted);
		assertEquals(expectedArgConstructor, expValid.argConstructor);
		assertEquals(expectedTestExecuted, expValid.testExecuted);
		assertEquals(expectedTestInnerExecuted, expValid.testInnerExecuted);
		assertEquals(expectedTestInnerWithArgsExecuted, expValid.testInnerWithArgsExecuted);
		assertEquals(expectedArgInner, expValid.argInner);
		assertEquals(expectedTestInner2WithArgsExecuted, expValid.testInner2WithArgsExecuted);
		assertEquals(expectedArgInner2, expValid.argInner2);
	}

}
