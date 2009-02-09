/**
 * 
 */
package org.oneupfordev.doit.internals.cmds;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.Dictionary;
import org.oneupfordev.doit.Result;
import org.oneupfordev.doit.packs.annotations.RootCmd;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.results.TextResult;
import org.oneupfordev.doit.stuff.Context;

/**
 * Support command for DoIt Dictionary actions.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
@RootCmd(cmds={"show"})
class Pack implements CallableExpression {

	private Dictionary dictionary = null;

	private enum Action {
		SHOW
	}

	private Action selectedAction = null;

	public Result doIt() {
		Result result = new TextResult("Action not defined.");

		if (selectedAction == Action.SHOW) {
			StringBuilder packs = new StringBuilder("Packs:\n");
			for (ExprPackDescriptor packDescr : dictionary.getPackDescriptors()) {
				packs.append("\t").append(packDescr.getName()).append(":\n");
				for (RootCmdDescriptor rootCmdDescr : packDescr.getDescriptors()) {
					packs.append("\t\t").append(rootCmdDescr.toString()).append("\n");
				}
			}
			result = new TextResult(packs.toString());
		}

		return result;
	}

	public String getAssign() {
		return null;
	}

	public void setAssign(String assign) {
	}

	public Pack show() {
		selectedAction = Action.SHOW;
		return this;
	}

	public Context getContext() {
		return null;
	}

	public void setContext(Context context) {
	}

	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

}
