/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.oneupfordev.doit.packs.descriptors.ExampleExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExpressionValid;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.stuff.DoItMock;
import org.oneupfordev.doit.stuff.DoItSessionMock;

/**
 * Tests of {@link Compiler}.
 * @author Roger Leite
 */
public class CompilerTest {

	private DoItSessionMock getValidDoItSessionMock() {
		DoItMock doItMock = new DoItMock();
		DoItSessionMock sessionMock = (DoItSessionMock) doItMock.createSession();

		ExampleExpressionPack validPack = new ExampleExpressionPack("example", new Class<?>[] {ExpressionValid.class});
		sessionMock.load(validPack);

		return sessionMock;
	}

	@Test
	public void parseValidExpressionsWithoutArgs() {
		String expression = "ExpressionValid test testinner";
		DoItSessionMock validSession = getValidDoItSessionMock();
		RootCmdDescriptor rootCmd = validSession.getDictionary().find(expression);

		Compiler ww = new Compiler(expression, rootCmd);
		ww.compile();
		assertEquals(3, ww.getWords().size());
	}

	@Test
	public void parseValidExpressionsWithArgs() {
		String expression = "ExpressionValid 'test' test testinner";
		DoItSessionMock validSession = getValidDoItSessionMock();
		RootCmdDescriptor rootCmd = validSession.getDictionary().find(expression);

		Compiler ww = new Compiler(expression, rootCmd);
		ww.compile();
		assertEquals(3, ww.getWords().size());
		assertEquals("test", ww.getWords().get(0).argument);

		expression = "ExpressionValid 'test' test testinner 'testinner with space'";
		rootCmd = validSession.getDictionary().find(expression);

		ww = new Compiler(expression, rootCmd);
		ww.compile();
		assertEquals(3, ww.getWords().size());
		assertEquals("test", ww.getWords().get(0).argument);
		assertEquals("testinner with space", ww.getWords().get(2).argument);
	}

}
