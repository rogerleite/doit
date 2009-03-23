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
package org.oneupfordev.doit.packs.search;

import java.util.List;

import static junit.framework.TestCase.*;

import org.junit.Test;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.packs.search.PackFinder;

/**
 * @author Roger Leite
 */
public class PackFinderTest {

	/**
	 * @return 'bin' directory.
	 */
	private String getRootPath() {
		return PackFinderTest.class.getResource("../../../../../").getPath();
	}

	@Test
	public void shouldFindOnePack() {
		PackFinder pf = new PackFinder();
		List<FolderPack> packs = pf.lookForPacks(getRootPath() + "testPackFinder");
		assertEquals(1, packs.size());
	}

	@Test
	public void shouldInstantiateOnePack() {
		PackFinder pf = new PackFinder();
		List<FolderPack> packs = pf.lookForPacks(getRootPath() + "testPackFinder");
		List<ExpressionPack> exprPacks = pf.instantiatePacks(packs);
		assertEquals(1, exprPacks.size());
		assertNotNull("First ExpressionPack cannot be null.", exprPacks.get(0));
		assertEquals("googlepack", exprPacks.get(0).getName());
	}

}
