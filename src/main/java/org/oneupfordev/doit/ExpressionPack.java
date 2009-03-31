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

import org.oneupfordev.doit.stuff.Dictionary;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * An Expression Pack is a interface to represent the group of {@link CallableExpression}s.<br>
 * It is used to {@link DoItSession#load(ExpressionPack) load} your expressions.
 * @author Roger Leite
 */
public interface ExpressionPack {

	/**
	 * Used by DoIt to know what {@link CallableExpression}s this pack is compose of.<br>
	 * If any item of the list is <code>null</code> or <b>not</b> implements CallableExpression, throws an {@link IllegalArgumentException}.
	 * @return an array of CallableExpressions Classes.
	 */
	Class<?>[] getExpressions();

	/**
	 * Used to load in the DoIt's {@link Dictionary}.<br>
	 * If name is <code>null</code> or empty, throws an {@link IllegalArgumentException}.
	 * @return name of this ExpressionPack.
	 */
	String getName();

}
