package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.packs.annotations.ExprDescription;
import org.oneupfordev.doit.packs.annotations.ExprDescription.InnerCmdDescriptor;

@ExprDescription(cmds={"test"})
@InnerCmdDescriptor(name="test", innerCmds={"testDeep2"})
public class ExpressionWithoutInnerCmds2 extends NakedExampleExpression {
	public ExpressionWithoutInnerCmds2 test() { return this; }
}
