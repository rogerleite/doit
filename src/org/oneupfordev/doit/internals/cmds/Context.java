/**
 * 
 */
package org.oneupfordev.doit.internals.cmds;

import java.util.Map.Entry;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Result;
import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.packs.annotations.RootCmd.Cmd;
import org.oneupfordev.doit.results.TextResult;

/**
 * Support command for DoIt {@link org.oneupfordev.doit.stuff.Context} actions.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
@RootCmd(cmds={"show", "add"})
@Cmd(name="add", innerCmds={"value"})
public class Context implements CallableExpression {

	org.oneupfordev.doit.stuff.Context ctx = null;

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

	public org.oneupfordev.doit.stuff.Context getContext() {
		return ctx;
	}

	public void setAssign(String assign) {
	}

	public void setContext(org.oneupfordev.doit.stuff.Context context) {
		ctx = context;
	}

}
