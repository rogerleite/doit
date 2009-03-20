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
