/**
 * 
 */
package org.oneupfordev.doit;

import org.oneupfordev.doit.results.Result;
import org.oneupfordev.doit.results.TextResult;
import org.oneupfordev.doit.stuff.DoItSession;

/**
 * This interface is what makes an Expression Callable.<br>
 * TODO: I am pending to put more detailed information here.
 * @author Roger Leite
 */
public interface CallableExpression {

	/**
	 * It is the "execute" point of Expression.<br>
	 * @return anything that implements {@link Result} interface. Usually {@link TextResult} is used.
	 */
	Result doIt();

	/**
	 * Assign is the "right side" of an expression, after colon character.<br>
	 * If assign not found in expression, <code>null</code> is set.
	 * @param assign injected by Parser.
	 */
	void setAssign(String assign);
	/**
	 * Not used by DoIt library. Getter is here, just as convention.
	 * @return <code>null</code> or assign injected previously.
	 */
	String getAssign();

	/**
	 * Session is the one of main objects of DoIt.<br>
	 * You can do a lot of things like:<br>
	 * <ul>
	 * <li>{@link DoItSession#parse(String)} others expressions</li>
	 * <li>access to {@link DoItSession#getContext()} variables.</li>
	 * <li>access to {@link DoItSession#getDictionary()} of loaded expressions.</li>
	 * </ul>
	 * @param session injected by Parser.
	 */
	void setSession(DoItSession session);
	/**
	 * Not used by DoIt library. Getter is here, just as convention.
	 * @return <code>null</code> or session injected previously.
	 */
	DoItSession getSession();

}
