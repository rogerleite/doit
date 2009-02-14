/**
 * 
 */
package org.oneupfordev.doit.parsers.expr;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.stuff.Dictionary;

import static junit.framework.TestCase.*;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ExpressionTest {

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
		Dictionary dic = getDictionary();
		RootCmdDescriptor cmd = dic.find("cmd");

		Expression expr = new Expression("cmd", cmd);
		assertEquals(0, expr.arguments.size());
		assertEquals("cmd", expr.expression);

		expr = new Expression("cmd 'arg1'", cmd);
		assertEquals(1, expr.arguments.size());
		assertEquals("cmd $p1", expr.expression);

		expr = new Expression("cmd 'arg1' inner 'arg2'", cmd);
		assertEquals(2, expr.arguments.size());
		assertEquals("cmd $p1 inner $p2", expr.expression);
	}

}
