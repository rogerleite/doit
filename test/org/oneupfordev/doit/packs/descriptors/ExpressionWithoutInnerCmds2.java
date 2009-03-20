package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.packs.annotations.Cmd;
import org.oneupfordev.doit.packs.annotations.RootCmd;

@RootCmd(cmds={"test"})
@Cmd(name="test", innerCmds={"testDeep2"})
public class ExpressionWithoutInnerCmds2 extends NakedExampleExpression {
	public ExpressionWithoutInnerCmds2 test() { return this; }
}
