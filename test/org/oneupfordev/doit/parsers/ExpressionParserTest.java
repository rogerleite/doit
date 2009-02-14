/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.stuff.Context;
import org.oneupfordev.doit.stuff.Dictionary;


/**
 * Tests of Expression Parser.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ExpressionParserTest {

	private Dictionary getDictionary() {
		List<Class<? extends CallableExpression>> validList = new ArrayList<Class<? extends CallableExpression>>();
		validList.add(ExpressionValid.class);
		ExampleExpressionPack validPack = new ExampleExpressionPack("example", validList);

		Dictionary dic = new Dictionary();
		ExprPackDescriptor descr = dic.load(validPack);
		dic.add(descr);
		return dic;
	}

	@Test
	public void parseValidExpression() {
		/*
		 * ExpressionValid annotations:
		 * ExprDescription(cmds={"test"})
		 * InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
		 */
		ExpressionParser expParser = new ExpressionParser(new Context(), getDictionary());
		CallableExpression ce = expParser.parse("expressionvalid");
		checkCallableExpression(ce, true, false, null, false, false,
				false, null, false, null);
		assertNotNull("Context field cannot be null.", ce.getContext());
		assertNotNull("\"Hidden\" Dictionary field cannot be null.", ((ExpressionValid) ce).getDictionary());

		ce = expParser.parse("expressionvalid 'arg_constructor'");
		checkCallableExpression(ce, false, true, "arg_constructor", false, false,
				false, null, false, null);

		ce = expParser.parse("expressionvalid test");
		checkCallableExpression(ce, true, false, null, true, false,
				false, null, false, null);

		ce = expParser.parse("expressionvalid 'arg_constructor' test");
		checkCallableExpression(ce, false, true, "arg_constructor", true, false,
				false, null, false, null);

		ce = expParser.parse("expressionvalid test testInner");
		checkCallableExpression(ce, true, false, null, true, true,
				false, null, false, null);

		ce = expParser.parse("expressionvalid 'arg_constructor' test testInner");
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
