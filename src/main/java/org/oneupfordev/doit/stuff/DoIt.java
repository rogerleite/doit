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

import org.oneupfordev.doit.packs.search.PackFinder;

/**
 * Creates {@link DoItSession}s.
 * @author Roger Leite
 */
public class DoIt {

	/** Default constructor. */
	public DoIt() {
		// intentionally empty
	}

	/**
	 * Creates new session, with only internal expressions loaded.
	 * @return session with only internal expressions loaded.
	 */
	public DoItSession createSession() {
		return createSession(false);
	}

	/**
	 * Creates new session, and do some additional steps to load external packs.
	 * <p>
	 * These additional steps, you can see with details at {@link PackFinder} java doc.
	 * @param loadExternalPacks <code>true</code> if you want to load "external" expression.
	 * @return session with "external" expressions loaded if parameter above is <code>true</code>.
	 */
	public DoItSession createSession(final boolean loadExternalPacks) {
		final Context context = createContext();
		return getDoItSession(context, loadExternalPacks);
	}

	DoItSession getDoItSession(final Context context, final boolean loadExternalPacks) {
		final Dictionary dictionary = new Dictionary();
		final DoItSession session = new DoItSession(context, dictionary);
		session.loadInternalPack();
		if (loadExternalPacks) {
			session.loadExternalPacks(context.getAttribute(Context.PACKPATH_KEY).toString());
		}
		return session;
	}

	Context createContext() {
		final Context context = new Context();
		// TODO: look for a solution to this problem
		// URL packsUrl = DoIt.class.getResource("../../../../packs/");
		// context.setAttribute(Context.PACKPATH_KEY, packsUrl.getPath());
		context.setAttribute(Context.PACKPATH_KEY, "packs");
		return context;
	}

}
