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
package org.oneupfordev.doit.internals.cmds;

import org.oneupfordev.doit.ExpressionPack;

/**
 * Expression Pack of "support" commands of DoIt.
 * @author Roger Leite
 */
public class InternalExpressionPack implements ExpressionPack {

	public Class<?>[] getExpressions() {
		return new Class<?>[] {Pack.class, Context.class};
	}

	public String getName() {
		return "DoIt.Internal";
	}

}
