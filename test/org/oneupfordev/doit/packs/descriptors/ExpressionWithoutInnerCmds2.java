package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.packs.annotations.RootCmd.Cmd;

@RootCmd(cmds={"test"})
@Cmd(name="test", innerCmds={"testDeep2"})
public class ExpressionWithoutInnerCmds2 extends NakedExampleExpression {
	public ExpressionWithoutInnerCmds2 test() { return this; }
}
