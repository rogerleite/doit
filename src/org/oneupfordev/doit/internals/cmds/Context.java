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

import java.util.Map.Entry;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.packs.annotations.Cmd;
import org.oneupfordev.doit.packs.annotations.RootCmd;

import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.results.TextResult;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * Support command for DoIt {@link org.oneupfordev.doit.stuff.Context} actions.
 * @author Roger Leite
 */
@RootCmd(cmds={"show", "add"})
@Cmd(name="add", innerCmds={"value"})
class Context implements CallableExpression {

	private org.oneupfordev.doit.stuff.Context ctx = null;
	private DoItSession session;

	private enum Action {
		DEFAULT_SHOW,
		ADD
	}

	private Action selectedAction = null;
	private String valueToAdd = null;
	private String keyToAdd = null;

	public Result doIt() {
		Result result = null;
		if (selectedAction == Action.DEFAULT_SHOW) {
			StringBuilder ctxAttributes = new StringBuilder("Attributes:\n");
			for (Entry<String, Object> entry : ctx.getAttributes().entrySet()) {
				ctxAttributes.append("\t")
								.append(entry.getKey())
								.append("=")
								.append(entry.getValue())
								.append("\n");
			}
			result = new TextResult(ctxAttributes.toString());
		} else if (selectedAction == Action.ADD) {
			ctx.setAttribute(keyToAdd, valueToAdd);
			result = new TextResult(String.format("Set %s=%s done!", keyToAdd, valueToAdd));
		}

		if (result == null) {
			result = new TextResult("Action not defined.");
		}

		return result;
	}

	public Context show() {
		selectedAction = Action.DEFAULT_SHOW;
		return this;
	}

	public Context add(String key) {
		selectedAction = Action.ADD;
		keyToAdd = key;
		return this;
	}

	public Context value(String value) {
		valueToAdd = value;
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
		this.ctx = session.getContext();
	}

}
