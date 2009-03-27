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

import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.results.TextResult;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * This interface is what makes an Expression Callable.<br>
 * TODO: I am pending to put more detailed information here.
 * @author Roger Leite
 */
public interface CallableExpression {

	/**
	 * It is the "execute" point of Expression.<br>
	 * @return anything that implements {@link Result} interface. Usually {@link TextResult} is used.
	 */
	Result doIt();

	/**
	 * Assign is the "right side" of an expression, after colon character.<br>
	 * If assign not found in expression, <code>null</code> is set.
	 * @param assign injected by Parser.
	 */
	void setAssign(String assign);
	/**
	 * Not used by DoIt library. Getter is here, just as convention.
	 * @return <code>null</code> or assign injected previously.
	 */
	String getAssign();

	/**
	 * Session is the one of main objects of DoIt.<br>
	 * You can do a lot of things like:<br>
	 * <ul>
	 * <li>{@link DoItSession#parse(String)} others expressions</li>
	 * <li>access to {@link DoItSession#getContext()} variables.</li>
	 * <li>access to {@link DoItSession#getDictionary()} of loaded expressions.</li>
	 * </ul>
	 * @param session injected by Parser.
	 */
	void setSession(DoItSession session);
	/**
	 * Not used by DoIt library. Getter is here, just as convention.
	 * @return <code>null</code> or session injected previously.
	 */
	DoItSession getSession();

}
