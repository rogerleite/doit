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
