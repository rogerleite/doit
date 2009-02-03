/**
 * 
 */
package org.oneupfordev.doit.packs;

import java.util.List;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.ExpressionPack;
import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.packs.descriptors.RootCmdDescriptor;
import org.oneupfordev.doit.packs.descriptors.ExprPackDescriptor;

/**
 * Validate and load an {@link ExprPackDescriptor} from an {@link ExpressionPack}.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public abstract class PackLoader {

	public ExprPackDescriptor load(final ExpressionPack exPack) {
		if (exPack == null) {
			throw new ExpressionIllegalArgumentException("Parameter ExpressionPack cannot be null.");
		}
		List<Class<? extends CallableExpression>> list = exPack.getExpressions();
		if (list == null || list.size() == 0) {
			throw new ExpressionIllegalArgumentException("getExpressions() from ExpressionPack cannot return null or zero class.");
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

	abstract RootCmdDescriptor validateAndLoad(final Class<? extends CallableExpression> classExpression);

}
