/**
 * 
 */
package org.oneupfordev.doit.parsers.expr;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.stuff.Dictionary;
import org.oneupfordev.doit.stuff.DoItMock;
import org.oneupfordev.doit.stuff.DoItSessionMock;

/**
 * @author Roger Leite
 */
public class ExpressionTest {

	private Dictionary getDictionary() {
		DoItMock doItMock = new DoItMock();
		DoItSessionMock sessionMock = (DoItSessionMock) doItMock.createSession();

		ExampleExpressionPack validPack = new ExampleExpressionPack("example", new Class<?>[] {ExpressionValid.class});
		sessionMock.load(validPack);

		return sessionMock.getDictionary();
	}

	@Test
	public void should_parse_arguments() {
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
