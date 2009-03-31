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
 * This interface is what makes an Expression "callable".<br>
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
	 * Assign is the "right side" of an expression, after ":" (colon) character.
	 * <p>
	 * If assign not found in expression, <code>null</code> is set.
	 * @param assign injected by Parser.
	 */
	void setAssign(String assign);

	/**
	 * @return <code>null</code> or assign injected previously by {@link #setAssign(String)}.
	 */
	String getAssign();

	/**
	 * Session who started the parsing of this expression.
	 * <p>
	 * You can do a lot of things like:<br>
	 * <ul>
	 * <li>{@link DoItSession#parse(String)} others expressions.</li>
	 * <li>Access to {@link DoItSession#getContext()} variables.</li>
	 * <li>Access to {@link DoItSession#getDictionary()} of loaded expressions.</li>
	 * </ul>
	 * @param session injected after creation of Expression instance.
	 */
	void setSession(DoItSession session);

	/**
	 * @return <code>null</code> or session injected previously by {@link #setSession(DoItSession)}.
	 */
	DoItSession getSession();

}
