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

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.results.TextResult;
import org.oneupfordev.doit.stuff.Dictionary;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * Support command for DoIt {@link Dictionary} actions.
 * @author Roger Leite
 */
@RootCmd(cmds={"show"})
class Pack implements CallableExpression {

	private enum Action {
		DEFAULT_SHOW
	}

	private Action selectedAction = null;
	private DoItSession session;

	public Result doIt() {
		Result result = new TextResult("Action not defined.");

		if (selectedAction == Action.DEFAULT_SHOW) {
			StringBuilder packs = new StringBuilder("Packs:\n");
			for (ExprPackDescriptor packDescr : session.getDictionary().getPackDescriptors()) {
				packs.append("\t").append(packDescr.getName()).append(":\n");
				for (RootCmdDescriptor rootCmdDescr : packDescr.getDescriptors()) {
					packs.append("\t\t").append(rootCmdDescr.toString()).append("\n");
				}
			}
			result = new TextResult(packs.toString());
		}

		return result;
	}

	public Pack show() {
		selectedAction = Action.DEFAULT_SHOW;
		return this;
	}

	public String getAssign() {
		return null;
	}

	public void setAssign(String assign) {
	}

	public DoItSession getSession() {
		return this.session;
	}

	public void setSession(DoItSession session) {
		this.session = session;
	}

}
