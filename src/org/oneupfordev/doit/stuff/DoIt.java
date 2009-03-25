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


/**
 * It is the object responsible for creating new {@link DoItSession}s.
 * @author Roger Leite
 */
public class DoIt {

	/** Default Constructor. */
	public DoIt() {}

	public DoItSession createSession() {
		return createSession(false);
	}

	public DoItSession createSession(boolean loadExternalPacks) {
		Context context = createContext();
		return getDoItSession(context, loadExternalPacks);
	}

	DoItSession getDoItSession(final Context context, boolean loadExternalPacks) {
		Dictionary dictionary = new Dictionary();
		DoItSession session = new DoItSession(context, dictionary);
		session.loadInternalPack();
		if (loadExternalPacks) {
			session.loadExternalPacks(context.getAttribute(Context.PACKPATH_KEY).toString());
		}
		return session;
	}

	Context createContext() {
		Context context = new Context();
		//TODO: look for a solution to this problem
		//URL packsUrl = DoIt.class.getResource("../../../../packs/");
		//context.setAttribute(Context.PACKPATH_KEY, packsUrl.getPath());
		context.setAttribute(Context.PACKPATH_KEY, "packs");
		return context;
	}

}
