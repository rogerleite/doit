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

import org.oneupfordev.doit.packs.annotations.Cmd;
import org.oneupfordev.doit.packs.annotations.Cmds;
import org.oneupfordev.doit.packs.annotations.RootCmd;

@RootCmd(cmds={"test"})
@Cmds({
	@Cmd(name="test", innerCmds={"testInner", "testInner2"})
})
public class ExpressionValid extends NakedExampleExpression {
	
	public boolean constructorExecuted = false;
	public boolean constructorWithArgsExecuted = false;
	public String argConstructor = null;
	public boolean testExecuted = false;
	public boolean testInnerExecuted = false;
	public boolean testInnerWithArgsExecuted = false;
	public String argInner = null;
	public boolean testInner2WithArgsExecuted = false;
	public String argInner2 = null;
	
	public ExpressionValid() {
		constructorExecuted = true;
	}

	public ExpressionValid(String argConstructor) {
		constructorWithArgsExecuted = true;
		this.argConstructor = argConstructor;
	}
	
	public ExpressionValid test() {
		testExecuted = true;
		return this;
	}

	public ExpressionValid testInner(String argInner) {
		testInnerWithArgsExecuted = true;
		this.argInner = argInner;
		return this;
	}

	public ExpressionValid testInner() {
		testInnerExecuted = true;
		return this;
	}

	public ExpressionValid testInner2(String arg) {
		argInner2 = arg;
		testInner2WithArgsExecuted = true;
		return this;
	}
}
