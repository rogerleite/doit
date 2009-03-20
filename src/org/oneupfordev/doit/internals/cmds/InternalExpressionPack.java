/**
 * 
 */
package org.oneupfordev.doit.internals.cmds;

import org.oneupfordev.doit.ExpressionPack;

/**
 * Expression Pack of "support" commands of DoIt.
 * @author Roger Leite
 */
public class InternalExpressionPack implements ExpressionPack {

	public Class<?>[] getExpressions() {
		return new Class<?>[] {Pack.class, Context.class};
	}

	public String getName() {
		return "DoIt.Internal";
	}

}
