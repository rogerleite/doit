/**
 * 
 */
package org.oneupfordev.doit.packs.descriptors;

import java.lang.reflect.Constructor;
import java.util.List;

import net.vidageek.mirror.ClassController;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;


/**
 * Describe commands of Expression.
 * @author <a href="roger.leite@1up4dev.org">Roger Leite</a>
 */
public class ExprCmdDescriptor extends CmdDescriptor {

	//private Class<? extends CallableExpression> classExpression;

	public ExprCmdDescriptor(Class<? extends CallableExpression> classExpression) {
		//this.classExpression = classExpression;
		setName(classExpression.getSimpleName());
	}

	@Override
	public void discoverArgumentType(ClassController<CallableExpression> clController) {
		List<Constructor<CallableExpression>> constructors = 
			clController.reflectAll().constructors();

		if (constructors.size() == 1) {
			argumentType = discoverArgumentTypeByParameterTypes(constructors.get(0).getParameterTypes());
		} else if (constructors.size() == 2) {
			ArgumentType argConstructor1 = discoverArgumentTypeByParameterTypes(constructors.get(0).getParameterTypes());
			ArgumentType argConstructor2 = discoverArgumentTypeByParameterTypes(constructors.get(1).getParameterTypes());

			if ((argConstructor1 == ArgumentType.NO_ACCEPT || argConstructor1 == ArgumentType.REQUIRED)
					&& (argConstructor2 == ArgumentType.NO_ACCEPT || argConstructor2 == ArgumentType.REQUIRED)) {
				argumentType = ArgumentType.OPTIONAL;
			}

		} else {
			throw new ExpressionNotValidException("Cannot have more than two constructors.");
		}

	}

}
