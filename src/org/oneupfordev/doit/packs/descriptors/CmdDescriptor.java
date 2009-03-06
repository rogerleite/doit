/**
 * 
 */
package org.oneupfordev.doit.packs.descriptors;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.vidageek.mirror.ClassController;

import org.oneupfordev.doit.CallableExpression;
import org.oneupfordev.doit.exceptions.ExpressionIllegalArgumentException;
import org.oneupfordev.doit.exceptions.ExpressionNotValidException;

/**
 * Describe an inner command of Expressions.
 * @author Roger Leite
 */
public class CmdDescriptor {

	String name = null;
	ArgumentType argumentType = null;
	List<CmdDescriptor> innerCmds = new ArrayList<CmdDescriptor>();

	protected CmdDescriptor() {}

	protected void addInnerCmd(CmdDescriptor cmdDescr) {
		innerCmds.add(cmdDescr);
	}

	public CmdDescriptor getInnerCmd(int index) {
		return innerCmds.get(index);
	}

	public List<CmdDescriptor> getInnerCmds() {
		return Collections.unmodifiableList(innerCmds);
	}

	protected void setName(String name) {
		if (name == null || "".equals(name.trim())) {
			throw new ExpressionIllegalArgumentException("Name of Command Descriptor cannot be null or empty.");
		}
		this.name = name.toLowerCase();
	}

	public String getName() {
		return name;
	}

	public ArgumentType getArgumentType() {
		return argumentType;
	}

	protected void discoverArgumentType(ClassController<CallableExpression> clController) {
		//TODO: refactor and extract a new method with ExprCmdDescriptor.discoverArgumentType
		List<Method> methods = findMethodByName(name, clController.reflectAll().methods());
		if (methods.size() == 1) {
			argumentType = discoverArgumentTypeByParameterTypes(methods.get(0).getParameterTypes());
		} else if (methods.size() == 2) {
			ArgumentType argConstructor1 = discoverArgumentTypeByParameterTypes(methods.get(0).getParameterTypes());
			ArgumentType argConstructor2 = discoverArgumentTypeByParameterTypes(methods.get(1).getParameterTypes());

			if ((argConstructor1 == ArgumentType.NO_ACCEPT || argConstructor1 == ArgumentType.REQUIRED)
					&& (argConstructor2 == ArgumentType.NO_ACCEPT || argConstructor2 == ArgumentType.REQUIRED)) {
				argumentType = ArgumentType.OPTIONAL;
			}

		} else if (methods.size() == 0) {
			throw new ExpressionNotValidException("Inner command '" + name + "' not found.");
		} else {
			throw new ExpressionNotValidException("Cannot have more than two methods with name: '" + name + "'.");
		}
	}

	public CmdDescriptor addInnerCmd(ClassController<CallableExpression> clController, String methodName) {
		CmdDescriptor cmdDescr = new CmdDescriptor();
		cmdDescr.setName(methodName);
		cmdDescr.discoverArgumentType(clController);
		addInnerCmd(cmdDescr);
		return cmdDescr;
	}

	private List<Method> findMethodByName(String methodName, List<Method> methods) {
		//TODO: find a better way than this
		List<Method> methodsFound = new ArrayList<Method>();
		for (Method method : methods) {
			if (method.getName().equalsIgnoreCase(methodName)) {
				methodsFound.add(method);
			}
		}
		return methodsFound;
	}

	protected ArgumentType discoverArgumentTypeByParameterTypes(Class<?>[] classParameters) {
		ArgumentType resultArgumentType;
		if (classParameters.length == 0) {
			resultArgumentType = ArgumentType.NO_ACCEPT;
		} else if (classParameters.length == 1) {

			if (!classParameters[0].getName().equals(String.class.getName())) {
				throw new ExpressionNotValidException("Constructor argument have to be " + String.class.getName() + " .");
			} else {
				resultArgumentType = ArgumentType.REQUIRED;
			}

		} else {
			throw new ExpressionNotValidException("Constructor cannot have more than one parameter.");
		}

		return resultArgumentType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("'" + this.name + "'");
		builder.append(" ").append(argumentType);
		if (innerCmds != null && !innerCmds.isEmpty()) {
			builder.append(" innerCmds: [ ");
			for (CmdDescriptor cmd : innerCmds) {
				builder.append(cmd);
			}
			builder.append(" ]");
		}
		return builder.toString() + " ";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((argumentType == null) ? 0 : argumentType.hashCode());
		result = prime * result
				+ ((innerCmds == null) ? 0 : innerCmds.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (obj instanceof CmdDescriptor) {
			CmdDescriptor other = (CmdDescriptor) obj;
			if (argumentType == null) {
				if (other.argumentType != null)
					return false;
			} else if (!argumentType.equals(other.argumentType))
				return false;
			if (innerCmds == null) {
				if (other.innerCmds != null)
					return false;
			} else if (!innerCmds.equals(other.innerCmds))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;

			return true;
		} else if (obj instanceof String) {
			if (!this.name.equalsIgnoreCase(obj.toString())) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

}
