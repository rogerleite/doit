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
package org.oneupfordev.doit;

import org.oneupfordev.doit.stuff.DoItSession;

/**
 * <p>It is the way to group {@link CallableExpression}s.<br>
 * With packs, is the only way you can {@link DoItSession#load(ExpressionPack)} new expressions.</p>
 * @author Roger Leite
 */
public interface ExpressionPack {

	/**
	 * Used by DoIt to know what {@link CallableExpression}s this pack is compose of.<br>
	 * If any item of the list is <code>null</code> or not implements {@link CallableExpression}, throws an {@link IllegalArgumentException}.
	 * @return an array of {@link CallableExpression}s Classes.
	 */
	Class<?>[] getExpressions();

	/**
	 * Used to load in the DoIt's Dictionary.<br>
	 * Name cannot be <code>null</code> or empty.
	 * @return name of this ExpressionPack.
	 */
	String getName();

}
