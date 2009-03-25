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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;

/**
 * Responsible for managing Descriptors.
 * 
 * @author Roger Leite
 */
public class Dictionary {

	private final Set<ExprPackDescriptor> setPackDescriptors = new HashSet<ExprPackDescriptor>();

	/**
	 * Default access.<br>
	 * Generally {@link DoIt} creates this object.
	 */
	Dictionary() {
		// intentionally empty
	}

	public void add(final ExprPackDescriptor packDescriptor) {
		if (packDescriptor == null) {
			throw new RuntimeException("packDescriptor cannot be null.");
		}
		if (setPackDescriptors.contains(packDescriptor)) {
			throw new RuntimeException("packDescriptor " + packDescriptor.getName() + " already exists.");
		}
		setPackDescriptors.add(packDescriptor);
	}

	public RootCmdDescriptor find(final String expression) {
		if (expression == null || "".equals(expression.trim())) {
			throw new ExpressionIllegalArgumentException("expression cannot be null or empty.");
		}
		final String trimmedExpr = expression.trim();
		final String rootCmdName = trimmedExpr.split(" ")[0];
		for (final ExprPackDescriptor packDescriptor : setPackDescriptors) {
			for (final RootCmdDescriptor cmdDescr : packDescriptor.getDescriptors()) {
				if (cmdDescr.getName().equalsIgnoreCase(rootCmdName)) {
					return cmdDescr;
				}
			}
		}
		return null;
	}

	/**
	 * @return <b>Read Only</b> {@link Set} of loaded {@link ExprPackDescriptor}
	 *         s.
	 */
	public Set<ExprPackDescriptor> getPackDescriptors() {
		return Collections.unmodifiableSet(setPackDescriptors);
	}

}
