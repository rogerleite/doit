/**
 * 
 */
package org.oneupfordev.doit;

import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.Context;

/**
 * This interface is what makes an Expression Callable and just do it!
 * @author Roger Leite
 */
public interface CallableExpression {

	void setContext(Context context);
	Context getContext();

	Result doIt();

	void setAssign(String assign);
	String getAssign();

}
