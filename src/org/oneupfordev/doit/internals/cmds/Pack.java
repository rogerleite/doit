/**
 * 
 */
package org.oneupfordev.doit.internals.cmds;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Result;
import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.results.TextResult;

/**
 * Support command for DoIt Dictionary actions.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
@RootCmd(cmds={"show"})
class Pack implements CallableExpression {

	public Result doIt() {
		Result result = new TextResult("Show something here.");
		return result;
	}

	public String getAssign() {
		return null;
	}

	public void setAssign(String assign) {
	}

	public Pack show() {
		return this;
	}

}
