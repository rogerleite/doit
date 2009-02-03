package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.packs.annotations.ExprDescription;
import org.oneupfordev.doit.packs.annotations.ExprDescription.InnerCmdDescriptor;

@ExprDescription(cmds={"test"})
@InnerCmdDescriptor(name="test", innerCmds={"testInner", "testInner2"})
public class ExpressionValid extends NakedExampleExpression {
	public ExpressionValid() {}
	public ExpressionValid(String argExample) {}
	
	public ExpressionValid test() { return this; }
	public ExpressionValid testInner(String arg) { return this; }
	public ExpressionValid testInner() { return this; }
	public ExpressionValid testInner2(String arg) { return this; }
}
