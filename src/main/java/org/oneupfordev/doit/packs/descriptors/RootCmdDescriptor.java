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
package org.oneupfordev.doit.packs.descriptors;

import java.lang.reflect.Constructor;
import java.util.List;

import net.vidageek.mirror.ClassController;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;


/**
 * Describe commands of Expression.
 * @author Roger Leite
 */
public class RootCmdDescriptor extends CmdDescriptor {

	private Class<? extends CallableExpression> classExpression;

	public RootCmdDescriptor(Class<? extends CallableExpression> classExpression) {
		this.classExpression = classExpression;
		setName(classExpression.getSimpleName());
	}

	public Class<? extends CallableExpression> getClassExpression() {
		return this.classExpression;
	}

	@Override
	public void discoverArgumentType(ClassController<CallableExpression> clController) {
		List<Constructor<CallableExpression>> constructors = 
			clController.reflectAll().constructors();

		if (constructors.size() == 1) {
			argumentType = discoverArgumentTypeByParameterTypes(constructors.get(0).getParameterTypes());
		} else if (constructors.size() == 2) {
			ArgumentType argConstructor1 = discoverArgumentTypeByParameterTypes(constructors.get(0).getParameterTypes());
			ArgumentType argConstructor2 = discoverArgumentTypeByParameterTypes(constructors.get(1).getParameterTypes());

			if ((argConstructor1 == ArgumentType.NO_ACCEPT || argConstructor1 == ArgumentType.REQUIRED)
					&& (argConstructor2 == ArgumentType.NO_ACCEPT || argConstructor2 == ArgumentType.REQUIRED)) {
				argumentType = ArgumentType.OPTIONAL;
			}

		} else {
			throw new ExpressionNotValidException("Cannot have more than two constructors.");
		}

	}

}
