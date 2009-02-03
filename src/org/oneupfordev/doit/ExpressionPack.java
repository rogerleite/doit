/**
 * 
 */
package org.oneupfordev.doit;

import java.util.List;

/**
 * It is the main point to get or receive new Expressions.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public interface ExpressionPack {

	/**
	 * The point where Module shows the Expressions available.<br>
	 * If any item of the list is <code>null</code> or not implements {@link CallableExpression},
	 * throws an {@link IllegalArgumentException}.
	 * @return an list of {@link CallableExpression}s Classes.
	 */
	List<Class<? extends CallableExpression>> getExpressions();

	/**
	 * @return Name of this ExpressionPack. Used to load in the DoIt's Dictionary.
	 */
	String getName();

}
