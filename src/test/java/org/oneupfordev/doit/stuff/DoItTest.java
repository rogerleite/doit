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
package org.oneupfordev.doit.stuff;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;

/**
 * Tests for {@link DoIt}.
 * @author Roger Leite
 */
public class DoItTest {

	@Test
	public void createSessionShouldContainContextAndDictionaryValids() {
		DoItSession session = new DoIt().createSession();
		assertEquals("Context contais pack path attribute.",
				session.context.getAttribute(Context.PACKPATH_KEY), "packs");
		assertEquals("Context contais only pack path attribute.",
				session.context.getAttributes().size(), 1);

		assertEquals("[Dic] Contains only one Pack loaded.", session.dictionary.getPackDescriptors().size(), 1);

		ExprPackDescriptor packDescr = session.dictionary.getPackDescriptors().iterator().next();
		assertEquals("[Dic] Name of only Descriptor is 'internal'.", packDescr.getName(), "DoIt.Internal");
	}

}
