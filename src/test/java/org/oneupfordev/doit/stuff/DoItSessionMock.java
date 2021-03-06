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
 * @author Roger Leite
 */
public class DoItSessionMock extends DoItSession {

	DoItSessionMock(Context context, Dictionary dictionary) {
		super(context, dictionary);
	}

	@Override
	void loadInternalPack() {
		// do nothing
	}

	@Override
	void loadExternalPacks(String packPath) {
		// do nothing
	}

	public Context getContext() {
		return this.context;
	}

	public Dictionary getDictionary() {
		return this.dictionary;
	}

}
