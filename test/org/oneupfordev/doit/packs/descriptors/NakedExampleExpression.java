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

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.DoItSession;

public class NakedExampleExpression implements CallableExpression {

	protected DoItSession session;
	protected String assign = null;

	public Result doIt() { return null; }
	public String getAssign() { return assign; }
	public void setAssign(String assign) { this.assign = assign; }

	public DoItSession getSession() {
		return this.session;
	}
	public void setSession(DoItSession session) {
		this.session = session;
	}

}
