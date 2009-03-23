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
import java.util.HashMap;
import java.util.Map;

import org.oneupfordev.doit.CallableExpression;

/**
 * Shared Object between {@link CallableExpression}s.
 * @author Roger Leite
 */
public class Context {

	static final String PACKPATH_KEY = "doit.packpath";

	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * Default access.<br>
	 * Generally {@link DoIt} creates this object.
	 */
	Context() {
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public void removeAttribute(String key) {
		attributes.remove(key);
	}

	/**
	 * @return a <b>read-only</b> map of Attributes.
	 */
	public Map<String, Object> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

}
