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
 * Tests of {@link ExpressionReader}.
 * @author Roger Leite
 */
public class ExpressionReaderTest {

	private DoItSessionMock getValidDoItSessionMock() {
		DoItMock doItMock = new DoItMock();
		DoItSessionMock sessionMock = (DoItSessionMock) doItMock.createSession();

		ExampleExpressionPack validPack = new ExampleExpressionPack("example", new Class<?>[] {ExpressionValid.class});
		sessionMock.load(validPack);

		return sessionMock;
	}

	@Test
	public void parseValidExpressions() {
		String expression = "ExpressionValid test testinner";
		DoItSessionMock validSession = getValidDoItSessionMock();
		RootCmdDescriptor rootCmd = validSession.getDictionary().find(expression);

		ExpressionReader ww = new ExpressionReader(expression, rootCmd);
		ww.read();
		assertEquals(3, ww.getWords().size());
	}

}
