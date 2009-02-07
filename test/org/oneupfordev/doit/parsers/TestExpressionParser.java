/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.TestCase.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Dictionary;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;


/**
 * Tests of Expression Parser.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class TestExpressionParser {

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
	public void parseArguments() {
		Expressions expressions = new Expressions(getDictionary());
		List<Argument> args = new ArrayList<Argument>();
		String result;

		result = expressions.parseArguments("cmd", args);
		assertEquals(0, args.size());
		assertEquals("cmd", result);
		result = expressions.parseArguments("cmd 'arg1'", args);
		assertEquals(1, args.size());
		assertEquals("cmd $p1", result);
		result = expressions.parseArguments("cmd 'arg1' inner 'arg2'", args);
		assertEquals(2, args.size());
		assertEquals("cmd $p1 inner $p2", result);
	}

	@Test
	public void parseValidExpression() {
		/*
		 * ExpressionValid annotations:
		 * ExprDescription(cmds={"test"})
		 * InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
		 */
		Expressions exp = new Expressions(getDictionary());
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
