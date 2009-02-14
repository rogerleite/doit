package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.Context;
import org.oneupfordev.doit.stuff.Dictionary;

public class NakedExampleExpression implements CallableExpression {

	protected Dictionary dictionary = null;
	protected Context context = null;

	public Result doIt() { return null; }
	public String getAssign() { return null; }
	public void setAssign(String assign) {}

	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

}
