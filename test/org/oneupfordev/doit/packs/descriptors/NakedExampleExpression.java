package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.DoItSession;

public class NakedExampleExpression implements CallableExpression {

	protected DoItSession session;
	protected String assign = null;

	public Result doIt() { return null; }
	public String getAssign() { return assign; }
	public void setAssign(String assign) { this.assign = assign; }

	public DoItSession getSession() {
		return this.session;
	}
	public void setSession(DoItSession session) {
		this.session = session;
	}

}
