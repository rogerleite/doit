/**
 * 
 */
package org.oneupfordev.doit.internals.cmds;

import java.util.ArrayList;
import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;

/**
 * Expression Pack of "support" commands of DoIt.
 * @author Roger Leite
 */
public class InternalExpressionPack implements ExpressionPack {

	public List<Class<? extends CallableExpression>> getExpressions() {
		List<Class<? extends CallableExpression>> expressions = 
			new ArrayList<Class<? extends CallableExpression>>();

		expressions.add(Pack.class);
		expressions.add(Context.class);

		return expressions;
	}

	public String getName() {
		return "DoIt.Internal";
	}

}
