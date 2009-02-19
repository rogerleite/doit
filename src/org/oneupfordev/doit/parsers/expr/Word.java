/**
 * 
 */
package org.oneupfordev.doit.parsers.expr;

import java.lang.reflect.Method;
import java.util.List;

import net.vidageek.mirror.Mirror;

import org.oneupfordev.doit.CallableExpression;


/**
 * @author Roger Leite
 */
public class Word {

	private String value = null;
	private boolean isArgumentType = false;
	private Argument argument = null;

	public Word(String value, final List<Argument> arguments) {
		this.value = value;
		this.isArgumentType = value.startsWith("$p");
		if (isArgumentType) {
			int idx = Integer.parseInt(value.replace("$p", ""));

			this.argument = arguments.get(idx -1);
			this.value = argument.getValue();
		}
	}

	public String capitalizeFirst() {
		String temp = value;
		temp = temp.substring(0, 1).toUpperCase()
				+ temp.substring(1).toLowerCase();
		return temp;
	}

	public boolean isParameterType() {
		return isArgumentType;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object anotherValue) {
		return value.equals(anotherValue);
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public Method getAsMethod(final CallableExpression callable, boolean withArgs) {
		//TODO: make a hash of "callable name" and methods?
		for (Method m : Mirror.on(callable.getClass()).reflectAll().methods()) {
			if (m.getName().equalsIgnoreCase(value)) {
				if (!withArgs && m.getParameterTypes().length == 0) {
					return m;
				} else if (withArgs && m.getParameterTypes().length == 1) {
					return m;
				}
			}
		}
		throw new RuntimeException("Method '" + value + "' cannot be found.");
	}

}
