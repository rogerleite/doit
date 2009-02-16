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
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
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
	public void parseValidExpression() {
		/*
		 * ExpressionValid annotations:
		 * ExprDescription(cmds={"test"})
		 * InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
		 */
		DoItSessionMock sessionMock = getDoItSessionMock();
		CallableExpression ce = sessionMock.parse("expressionvalid");
		checkCallableExpression(ce, true, false, null, false, false,
				false, null, false, null);
		assertNotNull("Context field cannot be null.", ce.getContext());
		assertNotNull("\"Hidden\" Dictionary field cannot be null.", ((ExpressionValid) ce).getDictionary());

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
