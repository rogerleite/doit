/**
 * 
 */
package org.oneupfordev.doit.parsers;

import static junit.framework.Assert.*;
import org.junit.Test;

/**
 * Tests of {@link ParserPointer}.
 * @author Roger Leite
 */
public class ParserPointerTest {

	@Test
	public void readWord() {
		ParserPointer pp = new ParserPointer("   bah   x");
		assertWord(pp, "bah", 9, false);

		pp = new ParserPointer("   bah   ");
		assertWord(pp, "bah", 9, true);

		pp = new ParserPointer("   bah");
		assertWord(pp, "bah", 6, true);

		pp = new ParserPointer("bah   ");
		assertWord(pp, "bah", 6, true);

		pp = new ParserPointer("bah");
		assertWord(pp, "bah", 3, true);

		pp = new ParserPointer(" bah '");
		assertWord(pp, "bah", 5, false);

		pp = new ParserPointer("bah ");
		pp.readWord();
		assertWord(pp, null, 4, true);
	}

	private void assertWord(ParserPointer pp, String expectedWord, int expectedIndex, boolean expectedEOE) {
		String word = pp.readWord();
		assertEquals(expectedWord, word);
		assertEquals("Current index at " + expectedIndex, expectedIndex, pp.getCurrentIndex());
		assertEquals(expectedEOE, pp.isEOE());
	}

}
