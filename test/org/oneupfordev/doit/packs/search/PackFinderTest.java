/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.util.List;

import static junit.framework.TestCase.*;

import org.junit.Test;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.packs.search.PackFinder;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackFinderTest {

	/**
	 * @return 'bin' directory.
	 */
	private String getRootPath() {
		return PackFinderTest.class.getResource("../../../../../").getPath();
	}

	@Test
	public void should_find_one_pack() {
		PackFinder pf = new PackFinder();
		List<FolderPack> packs = pf.lookForPacks(getRootPath() + "testPackFinder");
		assertEquals(1, packs.size());
	}

	@Test
	public void should_instantiate_one_pack() {
		PackFinder pf = new PackFinder();
		List<FolderPack> packs = pf.lookForPacks(getRootPath() + "testPackFinder");
		List<ExpressionPack> exprPacks = pf.instantiatePacks(packs);
		assertEquals(1, exprPacks.size());
		assertNotNull("First ExpressionPack cannot be null.", exprPacks.get(0));
		assertEquals("gtranslate", exprPacks.get(0).getName());
	}

}
