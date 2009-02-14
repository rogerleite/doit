/**
 * 
 */
package org.oneupfordev.doit;

import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.stuff.Context;

/**
 * This interface is what makes an Expression Callable and just do it!
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public interface CallableExpression {

	void setContext(Context context);
	Context getContext();

	Result doIt();

	void setAssign(String assign);
	String getAssign();

}
