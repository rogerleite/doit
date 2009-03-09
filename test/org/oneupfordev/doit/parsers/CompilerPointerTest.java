/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.oneupfordev.doit.exceptions.ParseExpressionException;

/**
 * Tests of {@link CompilerPointer}.
 * @author Roger Leite
 */
public class CompilerPointerTest {

	@Test
	public void pointerShouldReadWords() {
		CompilerPointer pp = new CompilerPointer("   bah   x");
		assertWord(pp, "bah", 9, false);

		pp = new CompilerPointer("   bah   ");
		assertWord(pp, "bah", 9, true);

		pp = new CompilerPointer("   bah");
		assertWord(pp, "bah", 6, true);

		pp = new CompilerPointer("bah   ");
		assertWord(pp, "bah", 6, true);

		pp = new CompilerPointer("bah");
		assertWord(pp, "bah", 3, true);

		pp = new CompilerPointer(" bah '");
		assertWord(pp, "bah", 5, false);

		pp = new CompilerPointer("bah ");
		pp.readWord();
		assertWord(pp, null, 4, true);

		pp = new CompilerPointer("");
		assertWord(pp, null, 0, true);
	}

	private void assertWord(CompilerPointer pp, String expectedWord, int expectedIndex, boolean expectedEOE) {
		String word = pp.readWord();
		assertEquals(expectedWord, word);
		assertEquals("Current index at " + expectedIndex, expectedIndex, pp.getCurrentIndex());
		assertEquals(expectedEOE, pp.isEOE());
	}

	@Test
	public void pointerShouldReadArgument() throws ParseExpressionException {
		CompilerPointer pp = new CompilerPointer("'x'");
		assertArgument(pp, "'x'", 3, true);

		pp = new CompilerPointer("   bah 'test test test'");
		pp.readWord();
		assertArgument(pp, "'test test test'", 23, true);

		pp = new CompilerPointer("   bah   'x' zzz");
		pp.readWord();
		assertArgument(pp, "'x'", 13, false);

		//TODO put an expression with "escaped" ' in the middle. This have to work?
	}

	@Test
	public void pointerShouldReadArgumentAndReturnNull() throws ParseExpressionException {
		CompilerPointer pp = new CompilerPointer("   bah   ");
		pp.readWord();
		assertArgument(pp, null, 9, true);

		pp = new CompilerPointer("bah");
		pp.readWord();
		assertArgument(pp, null, 3, true);

		pp = new CompilerPointer("bah cmd2 'test'");
		pp.readWord();
		assertArgument(pp, null, 4, false);
	}

	@Test
	public void pointerShouldReadArgumentAndReturnException() {
		try {
			CompilerPointer pp = new CompilerPointer("tst 'xxx");
			pp.readWord();
			pp.readArgument();
			fail("ExpressionParseException should be thrown.");
		} catch (ParseExpressionException e) {
			assertEquals("Exception have to be at index 5", 5, e.getErrorOffset());
		}

		try {
			CompilerPointer pp = new CompilerPointer("tst 'xxx bah cmd2");
			pp.readWord();
			pp.readArgument();
			fail("ExpressionParseException should be thrown.");
		} catch (ParseExpressionException e) {
			assertEquals("Exception have to be at index 5", 5, e.getErrorOffset());
		}
	}

	private void assertArgument(CompilerPointer pp, String expectedArg, int expectedIndex, boolean expectedEOE) throws ParseExpressionException {
		String arg = pp.readArgument();
		assertEquals(expectedArg, arg);
		assertEquals("Current index at " + expectedIndex, expectedIndex, pp.getCurrentIndex());
		assertEquals(expectedEOE, pp.isEOE());
	}

	@Test
	public void pointerShouldReadAssign() throws ParseExpressionException {
		CompilerPointer pp = new CompilerPointer("bah 'test' : test assign ");
		pp.readWord();
		pp.readArgument();
		assertAssign(pp, " test assign ", 25, true);

		pp = new CompilerPointer("bah 'test': test assign ");
		pp.readWord();
		pp.readArgument();
		assertAssign(pp, " test assign ", 24, true);

		pp = new CompilerPointer("bah :\t\nthis is a test with longer assign! Here i can use any character: tab, return, :, '");
		pp.readWord();
		assertAssign(pp, "\t\nthis is a test with longer assign! Here i can use any character: tab, return, :, '", 89, true);
	}

	@Test
	public void pointerShouldReadAssignAndReturnNullOrException() throws ParseExpressionException {
		CompilerPointer pp = new CompilerPointer("bah :");
		pp.readWord();
		assertAssign(pp, null, 5, true);

		pp = new CompilerPointer("bah ");
		pp.readWord();
		assertAssign(pp, null, 4, true);

		pp = new CompilerPointer("   bah   ");
		pp.readWord();
		assertAssign(pp, null, 9, true);

		try {
			CompilerPointer ppEx = new CompilerPointer("validcmd 'validarg' invalidcmd :");
			ppEx.readWord();
			ppEx.readArgument();
			ppEx.readAssign();
			fail("ExpressionParseException should be thrown.");
		} catch (ParseExpressionException e) {
			assertEquals("Exception have to be at index 20", 20, e.getErrorOffset());
		}
	}

	private void assertAssign(CompilerPointer pp, String expectedWord, int expectedIndex, boolean expectedEOE) throws ParseExpressionException {
		String word = pp.readAssign();
		assertEquals(expectedWord, word);
		assertEquals("Current index at " + expectedIndex, expectedIndex, pp.getCurrentIndex());
		assertEquals(expectedEOE, pp.isEOE());
	}

}