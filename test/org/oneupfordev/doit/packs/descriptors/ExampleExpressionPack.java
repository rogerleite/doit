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

package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.ExpressionPack;

public class ExampleExpressionPack implements ExpressionPack {

	String name;
	Class<?>[] classes;

	public ExampleExpressionPack(String name, Class<?>[] classes) {
		this.name = name;
		this.classes = classes;
	}

	public ExampleExpressionPack(String name) {
		this.name = name;
		classes = null;
	}

	public Class<?>[] getExpressions() {
		return classes;
	}

	public String getName() {
		return name;
	}

}
