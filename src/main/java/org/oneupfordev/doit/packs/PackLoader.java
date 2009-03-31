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
package org.oneupfordev.doit.packs;

import java.util.ArrayList;
import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Validate and load an {@link ExprPackDescriptor} from an {@link ExpressionPack}.
 * @author Roger Leite
 */
public abstract class PackLoader {

	//TODO Describe with details, what pack loader do and expect from ExpressionPack parameter.
	@SuppressWarnings("unchecked")
	public ExprPackDescriptor load(final ExpressionPack exPack) {
		if (exPack == null) {
			throw new IllegalArgumentException("Parameter ExpressionPack cannot be null.");
		} else if (exPack.getExpressions() == null) {
			throw new IllegalArgumentException("getExpressions() from ExpressionPack cannot return null.");
		}

		Class<?>[] classExpressions = exPack.getExpressions();
		List<Class<? extends CallableExpression>> list = new ArrayList<Class<? extends CallableExpression>>();
		for (int i = 0; i < classExpressions.length; i++) {
			Class<?> classExpression = classExpressions[i];
			if (classImplementsCallableExpression(classExpression)) {
				list.add((Class<? extends CallableExpression>) classExpression);
			} else {
				throw new IllegalArgumentException("Class '" + classExpression.getName() + "' not implements CallableExpression.");
			}
		}

		if (list.size() == 0) {
			throw new IllegalArgumentException("getExpressions() from ExpressionPack cannot return zero class.");
		}

		ExprPackDescriptor packDescr = new ExprPackDescriptor(exPack.getName());
		for(Class<? extends CallableExpression> clazz : list) {
			RootCmdDescriptor descr = null;
			try {
				descr = validateAndLoad(clazz);
				packDescr.add(descr);
			} catch (Throwable t) {
				packDescr.add(t);
			}
		}

		return packDescr;
	}

	protected boolean classImplementsCallableExpression(final Class<?> classExpression) {
		boolean implementsCallableExpression = false;

		Class<?> classExpr = classExpression;
		Class<?>[] interfaces = classExpr.getInterfaces();
		do {
			for (int i = 0; i < interfaces.length; i++) {
				if (interfaces[i].equals(CallableExpression.class)) {
					implementsCallableExpression = true;
					break;
				}
			}
			if (implementsCallableExpression || classExpr.getSuperclass() == null) {
				break;
			}
			classExpr = classExpr.getSuperclass();
			interfaces = classExpr.getInterfaces();
		} while (true);

		return implementsCallableExpression;
	}

	abstract RootCmdDescriptor validateAndLoad(final Class<? extends CallableExpression> classExpression);

}
