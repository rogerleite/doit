/**
 * 
 */
package org.oneupfordev.doit.stuff;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;

/**
 * Tests for {@link DoIt}.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class DoItTest {

	@Test
	public void createInternalSession() {
		DoItSession session = new DoIt().createSession();
		assertEquals("Context contais pack path attribute.",
				session.context.getAttribute(Context.PACKPATH_KEY), "packs");
		assertEquals("Context contais only pack path attribute.",
				session.context.getAttributes().size(), 1);

		assertEquals("[Dic] Contains only one Pack loaded.", session.dictionary.getPackDescriptors().size(), 1);

		ExprPackDescriptor packDescr = session.dictionary.getPackDescriptors().iterator().next();
		assertEquals("[Dic] Name of only Descriptor is 'internal'.", packDescr.getName(), "DoIt.Internal");
	}

	@Test
	public void createExternalSession() {
		//TODO: create test here
	}

}
