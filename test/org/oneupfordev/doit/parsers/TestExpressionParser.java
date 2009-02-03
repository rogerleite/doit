/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.TestCase.*;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;


/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class TestExpressionParser {

	@Test
	public void parseValidExpression() {
		/*
		 * ExpressionValid annotations:
		 * ExprDescription(cmds={"test"})
		 * InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
		 */
		Expressions exp = new Expressions();
		CallableExpression ce = exp.parse("expressionvalid");
		checkCallableExpression(ce, true, false, null, false, false,
				false, null, false, null);

		ce = exp.parse("expressionvalid 'arg_constructor'");
		checkCallableExpression(ce, false, true, "arg_constructor", false, false,
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
