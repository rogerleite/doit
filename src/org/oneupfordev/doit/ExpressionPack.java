/**
 * 
 */
package org.oneupfordev.doit;

import org.oneupfordev.doit.stuff.DoItSession;

/**
 * <p>It is the way to group {@link CallableExpression}s.<br>
 * With packs, is the only way you can {@link DoItSession#load(ExpressionPack)} new expressions.</p>
 * @author Roger Leite
 */
public interface ExpressionPack {

	/**
	 * Used by DoIt to know what {@link CallableExpression}s this pack is compose of.<br>
	 * If any item of the list is <code>null</code> or not implements {@link CallableExpression}, throws an {@link IllegalArgumentException}.
	 * @return an array of {@link CallableExpression}s Classes.
	 */
	Class<?>[] getExpressions();

	/**
	 * Used to load in the DoIt's Dictionary.<br>
	 * Name cannot be <code>null</code> or empty.
	 * @return name of this ExpressionPack.
	 */
	String getName();

}
