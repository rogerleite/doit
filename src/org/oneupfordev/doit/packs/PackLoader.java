/**
 * 
 */
package org.oneupfordev.doit.packs;

import java.util.ArrayList;
import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;

/**
 * Validate and load an {@link ExprPackDescriptor} from an {@link ExpressionPack}.
 * @author Roger Leite
 */
public abstract class PackLoader {

	//TODO Describe with details, what pack loader do and expect from ExpressionPack parameter.
	@SuppressWarnings("unchecked")
	public ExprPackDescriptor load(final ExpressionPack exPack) {
		if (exPack == null) {
			throw new ExpressionIllegalArgumentException("Parameter ExpressionPack cannot be null.");
		} else if (exPack.getExpressions() == null) {
			throw new ExpressionIllegalArgumentException("getExpressions() from ExpressionPack cannot return null.");
		}

		Class<?>[] classExpressions = exPack.getExpressions();
		List<Class<? extends CallableExpression>> list = new ArrayList<Class<? extends CallableExpression>>();
		for (int i = 0; i < classExpressions.length; i++) {
			Class<?> classExpression = classExpressions[i];
			if (classImplementsCallableExpression(classExpression)) {
				list.add((Class<? extends CallableExpression>) classExpression);
			} else {
				throw new ExpressionIllegalArgumentException("Class '" + classExpression.getName() + "' not implements CallableExpression.");
			}
		}

		if (list.size() == 0) {
			throw new ExpressionIllegalArgumentException("getExpressions() from ExpressionPack cannot return zero class.");
		}

		ExprPackDescriptor packDescr = new ExprPackDescriptor(exPack.getName());
		for(Class<? extends CallableExpression> clazz : list) {
			RootCmdDescriptor descr = null;
			try {
				descr = validateAndLoad(clazz);
				packDescr.add(descr);
			} catch (Throwable t) {
				packDescr.add(t);
			}
		}

		return packDescr;
	}

	protected boolean classImplementsCallableExpression(final Class<?> classExpression) {
		boolean implementsCallableExpression = false;

		Class<?> classExpr = classExpression;
		Class<?>[] interfaces = classExpr.getInterfaces();
		do {
			for (int i = 0; i < interfaces.length; i++) {
				if (interfaces[i].equals(CallableExpression.class)) {
					implementsCallableExpression = true;
					break;
				}
			}
			if (implementsCallableExpression || classExpr.getSuperclass() == null) {
				break;
			}
			classExpr = classExpr.getSuperclass();
			interfaces = classExpr.getInterfaces();
		} while (true);

		return implementsCallableExpression;
	}

	abstract RootCmdDescriptor validateAndLoad(final Class<? extends CallableExpression> classExpression);

}
