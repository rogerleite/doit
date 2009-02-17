/**
 * 
 */
package org.oneupfordev.doit.packs.search;

import java.util.List;

import static junit.framework.TestCase.*;

import org.junit.Test;
import org.oneupfordev.doit.packs.search.PackFinder;

/**
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class PackFinderTest {

	@Test
	public void should_find_one_pack() {
		//returns 'bin' directory
		String localPath = PackFinderTest.class.getResource("../../../../../").getPath();
		PackFinder pf = new PackFinder();
		List<FolderPack> packs = pf.lookForPacks(localPath + "testPackFinder");
		assertEquals(1, packs.size());
	}

}
