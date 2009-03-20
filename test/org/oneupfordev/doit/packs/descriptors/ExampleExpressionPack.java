package org.oneupfordev.doit.packs.descriptors;

import org.oneupfordev.doit.ExpressionPack;

public class ExampleExpressionPack implements ExpressionPack {

	String name;
	Class<?>[] classes;

	public ExampleExpressionPack(String name, Class<?>[] classes) {
		this.name = name;
		this.classes = classes;
	}

	public ExampleExpressionPack(String name) {
		this.name = name;
		classes = null;
	}

	public Class<?>[] getExpressions() {
		return classes;
	}

	public String getName() {
		return name;
	}

}
