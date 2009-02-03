/**
 * 
 */
package org.oneupfordev.doit;

/**
 * This interface is what makes an Expression Callable and just do it!
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public interface CallableExpression {

	Result doIt();

	void setAssign(String assign);
	String getAssign();

}
